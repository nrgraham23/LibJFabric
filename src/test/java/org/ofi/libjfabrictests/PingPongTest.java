/*
 * Copyright (c) 2013-2015 Intel Corporation.  All rights reserved.
 * Copyright (c) 2014-2016, Cisco Systems, Inc. All rights reserved.
 * Copyright (c) 2015-2016 Los Alamos Nat. Security, LLC. All rights reserved.
 * Copyright (c) 2016 Cray Inc.  All rights reserved.
 *
 * This software is available to you under the BSD license below:
 *
 *     Redistribution and use in source and binary forms, with or
 *     without modification, are permitted provided that the following
 *     conditions are met:
 *
 *      - Redistributions of source code must retain the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer.
 *
 *      - Redistributions in binary form must reproduce the above
 *        copyright notice, this list of conditions and the following
 *        disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AWV
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.ofi.libjfabrictests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import org.ofi.libjfabric.*;
import org.ofi.libjfabric.attributes.*;
import org.ofi.libjfabric.enums.*;

public class PingPongTest {
	private final Version version = new Version(1,3);
	private final int PP_OPT_ACTIVE = 1 << 0;
	private final int PP_OPT_ITER = 1 << 1;
	private final int PP_OPT_SIZE = 1 << 2;
	private final int PP_OPT_VERIFY_DATA = 1 << 3;

	private class PPOpts {
		String src_port;
		String dst_port;
		String dst_addr;
		int iterations;
		int transfer_size;
		int sizes_enabled;
		int options;
		boolean isServer = false;
	};
	final int PP_SIZE_MAX_POWER_TWO = 22;
	final int PP_MAX_DATA_MSG = ((1 << PP_SIZE_MAX_POWER_TWO) + (1 << (PP_SIZE_MAX_POWER_TWO - 1)));

	final int PP_STR_LEN = 32;
	final int PP_MAX_CTRL_MSG = 64;
	final int PP_CTRL_BUF_LEN = 64;
	final long PP_MR_KEY = 49374;

	final int INTEG_SEED = 7;
	final int PP_ENABLE_ALL = ~0;
	final int PP_DEFAULT_SIZE = (1 << 0);

	final static String PP_MSG_CHECK_PORT_OK = "port ok";
	final static int PP_MSG_LEN_PORT = 5;
	final static String PP_MSG_CHECK_CNT_OK = "cnt ok";
	final static int PP_MSG_LEN_CNT = 10;
	final static String PP_MSG_SYNC_Q = "q";
	final static String PP_MSG_SYNC_A = "a";
	static boolean PP_ACTIVATE_DEBUG = false;

	/*private void PP_PRINTERR(String call, int retv) { //not needed since ignoring possible errors atm
		System.err.printf("%s(): ret=%d \n", call,
				(int) retv, fi_strerror((int) -retv));
	}*/
	private void PP_ERR(String fmt, Object ... items) {
		System.err.printf("[" + fmt + "]: error\n");
		//can add something to handle variable arguments if its worth while
	}

	private static void PP_DEBUG(String fmt, String... items) {
		if (PP_ACTIVATE_DEBUG) {
			if(items.length == 0) {
				System.err.printf("[" + fmt + "]\n");
			} else if(items.length == 1) {
				System.err.printf("[" + fmt + "]\n", items[0]);
			} else if(items.length == 2) {
				System.err.printf("[" + fmt + "]\n", items[0], items[1]);
			} else if(items.length == 3) {
				System.err.printf("[" + fmt + "]\n", items[0], items[1], items[2]);
			} else if(items.length == 4) {
				System.err.printf("[" + fmt + "]\n", items[0], items[1], items[2], items[3]);
			} else {
				throw new RuntimeException("too many arguments to PP_DEBUG!");
			}
		}
		//can add something to handle variable arguments if its worth while
	}

	private void PP_CLOSE_FID(FIDescriptor fd) {
		if (fd != null) {	
			fd.close();
			fd = null;
		}
	}

	private class CTPingPong {
		Info fi_pep, fi, hints;
		Fabric fabric;
		Domain domain;
		PassiveEndPoint pep;
		EndPoint ep;
		CompletionQueue txcq, rxcq;
		MemoryRegion mr;
		AddressVector av;
		EventQueue eq;

		Context tx_ctx, rx_ctx;
		long remote_cq_data;

		long tx_seq, rx_seq, tx_cq_cntr, rx_cq_cntr;

		long remote_fi_addr;
		ByteBuffer buf, tx_buf, rx_buf;
		int buf_size, tx_size, rx_size;

		int timeout;
		long start, end;

		EventQueueAttr eq_attr;
		CQAttr cq_attr;
		PPOpts opts;

		long cnt_ack_msg;

		Socket ctrl_listenfd;
		Socket ctrl_connfd;
		byte[] ctrl_buf = new byte[PP_CTRL_BUF_LEN + 1];
		byte[] rem_name = new byte[PP_MAX_CTRL_MSG];
	};

	final static byte[] integ_alphabet = new byte[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i',
			'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','L','M',
			'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	/*******************************************************************************
	 *                                         Utils
	 ******************************************************************************/

	private int size_to_count(int size) {
		if (size >= (1 << 20))
			return 100;
		else if (size >= (1 << 16))
			return 1000;
		else
			return 10000;
	}

	private void pp_banner_fabric_info(CTPingPong ct) {
		System.err.println("info banner ct fi: " + ct.fi);
		System.err.println("info banner fabricAttr: " + ct.fi.getFabricAttr());
		System.err.println("info banner provider name: " + ct.fi.getFabricAttr().getProviderName());
		PP_DEBUG("Running pingpong test with the msg endpoint trough a %s provider ...", ct.fi.getFabricAttr().getProviderName());
		PP_DEBUG(" * Fabric Attributes:");
		PP_DEBUG("  - %20s : %s", "name", ct.fi.getFabricAttr().getName());
		PP_DEBUG("  - %20s : %s", "prov_name", ct.fi.getFabricAttr().getProviderName());
		PP_DEBUG("  - %20s : %d", "prov_version", ct.fi.getFabricAttr().getProviderVersion().toString());
		PP_DEBUG(" * Domain Attributes:\n");
		PP_DEBUG("  - %20s : %s", "name", ct.fi.getDomainAttr().getName());
		PP_DEBUG("  - %20s : %zu", "cq_cnt", "" + ct.fi.getDomainAttr().getCQCnt());
		PP_DEBUG("  - %20s : %zu", "cq_data_size", "" + ct.fi.getDomainAttr().getCQDataSize());
		PP_DEBUG("  - %20s : %zu", "ep_cnt", "" + ct.fi.getDomainAttr().getEndPointCnt());
		PP_DEBUG(" * Endpoint Attributes:\n");
		PP_DEBUG("  - %20s : msg", "type");
		PP_DEBUG("  - %20s : %d", "protocol", ct.fi.getEndPointAttr().getProtocol().toString());
		PP_DEBUG("  - %20s : %d", "protocol_version", "" + ct.fi.getEndPointAttr().getProtoVersion());
		PP_DEBUG("  - %20s : %zu", "max_msg_size", "" + ct.fi.getEndPointAttr().getMaxMsgSize());
		PP_DEBUG("  - %20s : %zu", "max_order_raw_size", "" + ct.fi.getEndPointAttr().getMaxOrderRawSize());
	}

	private void pp_banner_options(CTPingPong ct) {
		String size_msg = "";
		String iter_msg;
		PPOpts opts = ct.opts;

		if ((opts.dst_addr == null) || (opts.dst_addr.length() == 0))
			opts.dst_addr = "None";

		if (opts.sizes_enabled == PP_ENABLE_ALL)
			size_msg = "All sizes";
		else if ((opts.options & PP_OPT_SIZE) != 0)
			size_msg = "selected size = " + opts.transfer_size;

		if ((opts.options & PP_OPT_ITER) != 0)
			iter_msg = "selected iterations: " + opts.iterations;
		else {
			opts.iterations = size_to_count(opts.transfer_size);
			iter_msg = "default iterations: " + opts.iterations;
		}

		PP_DEBUG(" * PingPong options :\n");
		PP_DEBUG("  - %20s : [%s]", "src_port", opts.src_port);
		PP_DEBUG("  - %20s : [%s]", "dst_addr", opts.dst_addr);
		PP_DEBUG("  - %20s : [%s]", "dst_port", opts.dst_port);
		PP_DEBUG("  - %20s : %s", "sizes_enabled", size_msg);
		PP_DEBUG("  - %20s : %s", "iterations", iter_msg);
		if (ct.hints.getFabricAttr().getProviderName() != "")
			PP_DEBUG("  - %20s: %s", "provider",
					ct.hints.getFabricAttr().getProviderName());
		if (ct.hints.getDomainAttr().getName() != "")
			PP_DEBUG("  - %20s: %s", "domain",
					ct.hints.getDomainAttr().getName());
	}

	/*******************************************************************************
	 *                                         Control Messaging
	 ******************************************************************************/

	private static void ppCtrlInit(CTPingPong  ct) {
		PP_DEBUG("Initializing control messages ...");

		if (!ct.opts.isServer) { //client
			PP_DEBUG("CLIENT: connecting to <%s>", ct.opts.dst_addr);
			try {
				ct.ctrl_connfd = new Socket(ct.opts.dst_addr, Integer.parseInt(ct.opts.dst_port)); //creates and connects the socket
				ct.ctrl_connfd.setReceiveBufferSize(PP_MSG_LEN_PORT);
				ct.ctrl_connfd.setSoTimeout(5000);
			} catch (UnknownHostException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			} catch (SocketException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			} 

			PP_DEBUG("CLIENT: connected");
		} else { //server
			ServerSocket serverSock = null;
			try {
				serverSock = new ServerSocket(Integer.parseInt(ct.opts.src_port));
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			}

			//serverSock.bind(new InetSocketAddress(ct.opts.src_addr, ct.opts.src_port)); //should be able to not use this
			PP_DEBUG("SERVER: waiting for connection ...");
			try {
				ct.ctrl_listenfd = serverSock.accept();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			}

			PP_DEBUG("SERVER: connection acquired");
		}

		PP_DEBUG("Control messages initialized");
	}

	private static void pp_ctrl_send(CTPingPong ct, byte[] buf) {
		try {
			ct.ctrl_connfd.getOutputStream().write(buf);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		PP_DEBUG("----> sent (%d) : \"", "" + buf.length);
		if (PP_ACTIVATE_DEBUG) {
			int i;
			for (i = 0; i < buf.length; i++) {
				System.err.printf("%c.", buf[i]);
			}
			System.err.printf("\"\n");
		}
	}

	private static void pp_ctrl_recv(CTPingPong ct, byte[] buf) {
		try {
			ct.ctrl_connfd.getInputStream().read(buf);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		//ret = recv(ct->ctrl_connfd, buf, size, 0);

		PP_DEBUG("----> received (%d/%ld) : \"", "" + PP_MSG_LEN_PORT);
		if (PP_ACTIVATE_DEBUG) {
			int i;
			for (i = 0; i < PP_MSG_LEN_PORT; i++) {
				System.err.printf("%b.", buf[i]);
			}
			System.err.printf("\"\n");
		}
	}

	private void pp_send_name(CTPingPong ct, EndPointSharedOps endpoint) {
		String local_name;
		//long addrlen;
		//int len;

		PP_DEBUG("Fetching local address");

		local_name = endpoint.getName();

		//PP_DEBUG("Sending name length\n");

		//pp_ctrl_send(ct, local_name.length()); sending name length, think i can skip this in Java

		PP_DEBUG("Sending name");
		pp_ctrl_send(ct, local_name.getBytes());
		PP_DEBUG("Sent name");
	}

	private void pp_recv_name(CTPingPong ct) {

		//PP_DEBUG("Receiving name length\n"); //sending length not needed in java
		//pp_ctrl_recv(ct, (char *) &len, sizeof(len));

		//len = ntohl(len);

		//if (len > sizeof(ct->rem_name)) {
		//	PP_DEBUG("Address length exceeds address storage\n");
		//	return -EMSGSIZE;
		//}

		PP_DEBUG("Receiving name");
		pp_ctrl_recv(ct, ct.rem_name);

		PP_DEBUG("Received name");

		/* fi_freeinfo will free the dest_addr field. */
		ct.hints.setDestAddr(ct.rem_name.toString());

		ct.hints.setDestAddrLen(ct.rem_name.length);
	}

	private void pp_ctrl_finish(CTPingPong ct) {
		if (!ct.ctrl_connfd.isClosed()) {
			try {
				ct.ctrl_connfd.close();
			} catch (IOException e) { 
				System.err.println("Failed to close ctrl_connfd: " + e.getMessage());
			}
		}
	}

	private void pp_ctrl_sync(CTPingPong ct) {
		PP_DEBUG("Syncing nodes");

		if (ct.opts.isServer) {
			PP_DEBUG("SERVER: syncing");

			pp_ctrl_recv(ct, ct.ctrl_buf);

			PP_DEBUG("SERVER: after recv");

			if (ct.ctrl_buf.toString().equals(PP_MSG_SYNC_Q)) {
				ct.ctrl_buf[PP_CTRL_BUF_LEN] = '\0';
				PP_DEBUG("SERVER: sync error while acking Q: " + ct.ctrl_buf.toString() + " (len=" + ct.ctrl_buf.length + ")");
			}

			PP_DEBUG("SERVER: syncing now");
			ct.ctrl_buf = PP_MSG_SYNC_A.getBytes();

			pp_ctrl_send(ct, ct.ctrl_buf);
			PP_DEBUG("SERVER: after send");

			PP_DEBUG("SERVER: synced");
		} else {
			ct.ctrl_buf = PP_MSG_SYNC_Q.getBytes();

			PP_DEBUG("CLIENT: syncing");
			pp_ctrl_send(ct, ct.ctrl_buf);
			PP_DEBUG("CLIENT: after send");

			PP_DEBUG("CLIENT: syncing now");

			pp_ctrl_recv(ct, ct.ctrl_buf);
			PP_DEBUG("CLIENT: after recv");

			if (!ct.ctrl_buf.toString().equals(PP_MSG_SYNC_A)) {
				//ct.ctrl_buf[PP_CTRL_BUF_LEN] = '\0';
				PP_DEBUG("CLIENT: sync error while acking A: <%s> (len=%lu)",
						ct.ctrl_buf.toString(), "" + ct.ctrl_buf.length);
			}
			PP_DEBUG("CLIENT: synced");
		}

		PP_DEBUG("Nodes synced");
	}

	private void pp_ctrl_txrx_msg_count(CTPingPong ct) {
		PP_DEBUG("Exchanging ack count");

		if (ct.opts.isServer) {
			Arrays.fill(ct.ctrl_buf, (byte)'\0');

			PP_DEBUG("SERVER: receiving count");
			pp_ctrl_recv(ct, ct.ctrl_buf);

			ct.cnt_ack_msg = Long.parseLong(ct.ctrl_buf.toString());

			PP_DEBUG("SERVER: received count = <%ld> (len=%lu)",
					"" + ct.cnt_ack_msg, "" + ct.ctrl_buf.length);

			ct.ctrl_buf = PP_MSG_CHECK_CNT_OK.getBytes();

			pp_ctrl_send(ct, ct.ctrl_buf);

			PP_DEBUG("SERVER: acked count to client");
		} else {
			Arrays.fill(ct.ctrl_buf, (byte)'\0');
			ct.ctrl_buf = ByteBuffer.allocate(Long.BYTES).putLong(ct.cnt_ack_msg).array(); //convert long to byte[]

			PP_DEBUG("CLIENT: sending count = <%s> (len=%lu)",
					ct.ctrl_buf.toString(), "" + ct.ctrl_buf.length);
			pp_ctrl_send(ct, ct.ctrl_buf);

			PP_DEBUG("CLIENT: sent count");

			pp_ctrl_recv(ct, ct.ctrl_buf);

			if (!ct.ctrl_buf.equals(PP_MSG_CHECK_CNT_OK)); {
				PP_DEBUG("CLIENT: error while server acking the count: <%s> (len=%lu)",
						ct.ctrl_buf.toString(), "" + ct.ctrl_buf.length);
			}
			PP_DEBUG("CLIENT: count acked by server");
		}

		PP_DEBUG("Ack count exchanged");
	}

	/*******************************************************************************
	 *                                         Options
	 ******************************************************************************/

	private void pp_start(CTPingPong ct) {
		PP_DEBUG("Starting test chrono");
		ct.opts.options |= PP_OPT_ACTIVE;
		ct.start = System.nanoTime();
	}

	private void pp_stop(CTPingPong ct) {
		ct.end = System.nanoTime();
		ct.opts.options &= ~PP_OPT_ACTIVE;
		PP_DEBUG("Stopped test chrono");
	}

	private boolean pp_check_opts(CTPingPong ct, long flags) {
		return (ct.opts.options & flags) == flags;
	}

	/*******************************************************************************
	 *                                         Data Verification
	 ******************************************************************************/

	private void pp_fill_buf(ByteBuffer buf) {
		int msg_index;
		int iter = 0;

		msg_index = ((iter++) * INTEG_SEED) % integ_alphabet.length;

		for (int i = 0; i < buf.capacity(); i++) {
			PP_DEBUG("index=%d msg_index=%d", "" + i, "" + msg_index);
			buf.put(i, integ_alphabet[msg_index++]);
			if (msg_index >= integ_alphabet.length)
				msg_index = 0;
		}
	}

	private boolean pp_check_buf(ByteBuffer buf) {
		byte c;
		int iter = 0;
		int msg_index, i;

		PP_DEBUG("Verifying buffer content");

		msg_index = ((iter++) * INTEG_SEED) % integ_alphabet.length;

		for (i = 0; i < buf.capacity(); i++) {
			c = integ_alphabet[msg_index++];
			if (msg_index >= integ_alphabet.length)
				msg_index = 0;
			if (c != buf.get(i)) {
				PP_DEBUG("index=%d msg_index=%d expected=%d got=%d",
						"" + i, "" + msg_index, "" + (char)c, "" + (char)buf.get(i));
				break;
			}
		}
		if (i != buf.capacity()) {
			PP_DEBUG("Finished veryfing buffer: content is corrupted");
			System.err.printf("Error at iteration=%d size=%d byte=%d\n", iter, buf.capacity(), i);
			return false;
		}

		PP_DEBUG("Buffer verified");

		return true;
	}


	/*******************************************************************************
	 *                                         Error handling
	 ******************************************************************************

void eq_readerr(struct fid_eq *eq)
{
	struct fi_eq_err_entry eq_err;
	int rd;

	rd = fi_eq_readerr(eq, &eq_err, 0);
	if (rd != sizeof(eq_err)) {
		PP_PRINTERR("fi_eq_readerr", rd);
	} else {
		PP_ERR("eq_readerr: %s",
		       fi_eq_strerror(eq, eq_err.prov_errno, eq_err.err_data,
				      NULL, 0));
	}
}

void pp_process_eq_err(ssize_t rd, struct fid_eq *eq, const char *fn)
{
	if (rd == -FI_EAVAIL)
		eq_readerr(eq);
	else
		PP_PRINTERR(fn, rd);
}

	/*******************************************************************************
	 *                                         Test sizes
	 ******************************************************************************/

	private int[] generate_test_sizes(PPOpts opts, long tx_size) {
		int[] defaults = new int[] {64, 256, 1024, 4096, 65536, 1048576};
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		int power_of_two;
		int half_up;
		int n = 0;

		PP_DEBUG("Generating test sizes");

		if ((opts.options & PP_OPT_SIZE) != 0) {
			if (opts.transfer_size > tx_size)
				throw new RuntimeException("tx_size too small!");

			sizes.add(0, opts.transfer_size);
			n = 1;
		} else if (opts.sizes_enabled != PP_ENABLE_ALL) {
			for (int i = 0; i < defaults.length; i++) {
				if (defaults[i] > tx_size)
					break;

				sizes.add(i, defaults[i]);
				n++;
			}
		} else {
			for (int i = 0;; i++) {
				power_of_two = (i == 0) ? 0 : (1 << i);
				half_up =
						(i == 0) ? 1 : power_of_two + (power_of_two / 2);

				if (power_of_two > tx_size)
					break;

				sizes.add(i * 2, power_of_two);
				n++;

				if (half_up > tx_size)
					break;

				sizes.add(i * 2, half_up);
				n++;
			}
		}

		PP_DEBUG("Generated %d test sizes", "" + n);

		return sizes.stream().mapToInt(i -> i).toArray();
	}

	/*******************************************************************************
	 *                                    Performance output
	 ******************************************************************************/

	/* str must be an allocated buffer of PP_STR_LEN bytes */
	private String size_str(long size) {
		String str = "";
		long base, fraction = 0;
		char mag;

		if (size >= (1 << 30)) {
			base = 1 << 30;
			mag = 'g';
		} else if (size >= (1 << 20)) {
			base = 1 << 20;
			mag = 'm';
		} else if (size >= (1 << 10)) {
			base = 1 << 10;
			mag = 'k';
		} else {
			base = 1;
			mag = '\0';
		}

		if (size / base < 10)
			fraction = (size % base) * 10 / base;

		if (fraction != 0)
			str = "" + (size / base) + "." + fraction + mag;
		else
			str = "" + (size / base) + "." + mag;

		return str;
	}

	/* str must be an allocated buffer of PP_STR_LEN bytes */
	private String cnt_str(long cnt) {
		String str = "";
		if (cnt >= 1000000000)
			str += (cnt / 1000000000) + "b";
		else if (cnt >= 1000000)
			str += (cnt / 1000000) + "m";
		else if (cnt >= 1000)
			str += (cnt / 1000) + "k";
		else
			str += cnt;

		return str;
	}

	private void show_perf(int tsize, int sent, long acked, long start, long end, int xfers_per_iter) {
		long elapsed = end - start; //TODO: format better
		long bytes = sent * tsize * xfers_per_iter;
		float usec_per_xfer;

		if (sent == 0)
			return;

		System.out.printf("%8s%8s%9s%8s%8s %10s%13s%13s\n", "bytes",
				"#sent", "#ack", "total", "time", "MB/sec",
				"usec/xfer", "Mxfers/sec");

		System.out.printf("%8s", size_str(tsize));
		System.out.printf("%8s", cnt_str(sent));

		if (sent == acked)
			System.out.printf("=%8s", cnt_str(acked));
		else if (sent < acked)
			System.out.printf("-%8s", cnt_str(acked - sent));
		else
			System.out.printf("+%8s", cnt_str(sent - acked));

		System.out.printf("%8s", size_str(bytes));

		usec_per_xfer = ((float)elapsed / sent / xfers_per_iter);
		System.out.printf("%8.2fs%10.2f%11.2f%11.2f\n", elapsed / 1000000.0,
				bytes / (1.0 * elapsed), usec_per_xfer, 1.0 / usec_per_xfer);
	}

	/*******************************************************************************
	 *                                      Data Messaging
	 ******************************************************************************

int pp_cq_readerr(struct fid_cq *cq)
{
	struct fi_cq_err_entry cq_err;
	int ret;

	ret = fi_cq_readerr(cq, &cq_err, 0);
	if (ret < 0) {
		PP_PRINTERR("fi_cq_readerr", ret);
	} else {
		PP_ERR("cq_readerr: %s",
		       fi_cq_strerror(cq, cq_err.prov_errno, cq_err.err_data,
				      NULL, 0));
		ret = -cq_err.err;
	}
	return ret;
}
	 */
	private void pp_get_cq_comp(CTPingPong ct, CompletionQueue cq, int timeout) {
		//struct fi_cq_err_entry comp;
		//long a = 0, b;
		//int ret = 0;

		//if (timeout >= 0)
		//	a = System.nanoTime();

		while (ct.rx_seq - ct.tx_cq_cntr > 0) {
			try {
				cq.read(1);
			} catch(Exception e) {
				/*ret = fi_cq_read(cq, &comp, 1); //Error handing code, hope to be able to ignore.
			if (ret > 0) {
				if (timeout >= 0)
					a = System.nanoTime();

				ct.tx_cq_cntr++;
			} /*else if (ret < 0 && ret != -FI_EAGAIN) { 
				if (ret == -FI_EAVAIL) {
					ret = pp_cq_readerr(cq);
					ct.tx_cq_cntr++;
				} else {
					PP_PRINTERR("pp_get_cq_comp", ret);
				}

				return ret;
			}* else if (timeout >= 0) {
				b = System.nanoTime();
				if ((b - a) / 1000000000 > timeout) { //done in seconds
					System.err.printf("%ds timeout expired\n", timeout);
				}
			}*/
			}
		}
	}
	private void pp_get_rx_comp(CTPingPong ct) {
		if (ct.rxcq != null) {
			pp_get_cq_comp(ct, ct.rxcq, ct.timeout);
		} else {
			PP_ERR("Trying to get a RX completion when no RX CQ was opened");
		}
	}

	private void pp_get_tx_comp(CTPingPong ct, long total) {
		if (ct.txcq != null) {
			pp_get_cq_comp(ct, ct.txcq, -1);
		} else {
			PP_ERR("Trying to get a TX completion when no TX CQ was opened");
		}
	}

	private void pp_post_tx(CTPingPong ct, EndPoint ep, Context ctx) {
		//int timeout_save;
		//int ret, rc;

		//while (true) {
		try {
			ep.send(ct.tx_buf, ct.mr.getDesc(), ct.remote_fi_addr, ctx);
		} catch(Exception e) {
			//if (!ret)
			//break;

			/*if (ret != -FI_EAGAIN) {
					PP_PRINTERR(op_str, ret);
					return ret;
				}

				timeout_save = ct->timeout;
				ct->timeout = 0;
				rc = comp_fn(ct, seq);
				ct->timeout = timeout_save;
				if (rc && rc != -FI_EAGAIN) {
					PP_ERR("Failed to get " op_str " completion");
					return rc;
				}*/
		}
		//}
		ct.tx_seq++;
	}

	private void pp_tx(CTPingPong ct, EndPoint ep, long size) {
		if (pp_check_opts(ct, PP_OPT_VERIFY_DATA | PP_OPT_ACTIVE))
			pp_fill_buf(ct.tx_buf);

		pp_post_tx(ct, ep, ct.tx_ctx);

		pp_get_tx_comp(ct, ct.tx_seq);
	}

	private void pp_post_inject(CTPingPong ct, EndPoint ep, long size) {
		//int timeout_save;
		//int ret, rc;

		//while (true) {
		try {
			ep.inject(ct.tx_buf, ct.remote_fi_addr);
			//if (!ret)
			//break;
		} catch(Exception e) {
			/*if (ret != -FI_EAGAIN) {
			PP_PRINTERR(op_str, ret);
			return ret;
		}

			timeout_save = ct->timeout;
			ct->timeout = 0;
			rc = comp_fn(ct, seq);
			ct->timeout = timeout_save;
			if (rc && rc != -FI_EAGAIN) {
				PP_ERR("Failed to get " op_str " completion");
				return rc;
			}*/
		}



		//}
		ct.tx_seq++;
		ct.tx_cq_cntr++;
	}

	private void pp_inject(CTPingPong ct, EndPoint ep, long size) {
		if (pp_check_opts(ct, PP_OPT_VERIFY_DATA | PP_OPT_ACTIVE))
			pp_fill_buf(ct.tx_buf);

		pp_post_inject(ct, ep, size);
	}
	private void pp_post_rx(CTPingPong ct, EndPoint ep, Context ctx) {
		//int timeout_save;
		//int rc;

		//while (true) {
		try {
			ep.recv(ct.rx_buf, ct.mr.getDesc(), 0, ctx);
			//if (!ret)
			//break;
		} catch (Exception e) {


			/*if (ret != -FI_EAGAIN) {
				PP_PRINTERR(op_str, ret);
				return ret;
			}

			timeout_save = ct->timeout;
			ct->timeout = 0;
			rc = comp_fn(ct, seq);
			ct->timeout = timeout_save;
			if (rc && rc != -FI_EAGAIN) {
				PP_ERR("Failed to get " op_str " completion");
				return rc;
			}*/
			//}
		}
		ct.tx_seq++;
	}

	private void pp_rx(CTPingPong ct, EndPoint ep, long size) {

		pp_get_rx_comp(ct);

		if (pp_check_opts(ct, PP_OPT_VERIFY_DATA | PP_OPT_ACTIVE)) {
			pp_check_buf(ct.rx_buf);
		}

		/* Ignore the size arg. Post a buffer large enough to handle all message
		 * sizes. pp_sync() makes use of pp_rx() and gets called in tests just
		 * before message size is updated. The recvs posted are always for the
		 * next incoming message.
		 */
		pp_post_rx(ct, ct.ep, ct.rx_ctx);
		//if (!ret)
		ct.cnt_ack_msg++;
	}

	/*******************************************************************************
	 *                                Initialization and allocations
	 ******************************************************************************/

	private void init_test(CTPingPong ct, PPOpts opts) {
		String sstr;

		sstr = size_str(opts.transfer_size);
		if ((opts.options & PP_OPT_ITER) == 0)
			opts.iterations = size_to_count(opts.transfer_size);

		ct.cnt_ack_msg = 0;
	}

	private long pp_init_cq_data(Info info) {
		if (info.getDomainAttr().getCQDataSize() >= Long.MAX_VALUE) {
			return 81985529216486895L;
		} else {
			return 81985529216486895L & ((1 << (info.getDomainAttr().getCQDataSize() * 8)) - 1);
		}
	}

	private void pp_alloc_msgs(CTPingPong ct) { //removed memory alignment code here
		ct.tx_size = ((ct.opts.options & PP_OPT_SIZE) != 0) ? ct.opts.transfer_size : PP_MAX_DATA_MSG;
		if (ct.tx_size > ct.fi.getEndPointAttr().getMaxMsgSize())
			ct.tx_size = ct.fi.getEndPointAttr().getMaxMsgSize();

		ct.rx_size = ct.tx_size;
		ct.buf_size = Math.max(ct.tx_size, PP_MAX_CTRL_MSG) + Math.max(ct.rx_size, PP_MAX_CTRL_MSG);

		ct.buf = ByteBuffer.allocateDirect(ct.buf_size);

		ct.rx_buf = ct.buf;
		ct.buf.position(Math.max(ct.rx_size, PP_MAX_CTRL_MSG));
		ct.tx_buf = ct.buf.slice();
		ct.buf.position(0);

		ct.remote_cq_data = pp_init_cq_data(ct.fi);

		if ((ct.fi.getMode() & LibFabric.FI_LOCAL_MR) != 0) {
			ct.mr = ct.domain.mrRegister(ct.buf, (LibFabric.FI_SEND | LibFabric.FI_RECV), PP_MR_KEY);
		}
	}

	private void pp_open_fabric_res(CTPingPong ct) {
		PP_DEBUG("Opening fabric resources: fabric, eq & domain");

		ct.fabric = new Fabric(ct.fi.getFabricAttr());

		ct.eq = ct.fabric.eventQueueOpen(ct.eq_attr);

		ct.domain = ct.fabric.createDomain(ct.fi);

		PP_DEBUG("Fabric resources opened");
	}

	private void pp_alloc_active_res(CTPingPong ct, Info fi) {
		pp_alloc_msgs(ct);

		if (ct.cq_attr.getCQFormat() == null || ct.cq_attr.getCQFormat() == CQFormat.FI_CQ_FORMAT_UNSPEC)
			ct.cq_attr.setCQFormat(CQFormat.FI_CQ_FORMAT_CONTEXT);

		ct.cq_attr.setWaitObj(WaitObj.WAIT_NONE);

		ct.cq_attr.setSize(fi.getTransmitAttr().getSize());
		ct.txcq = ct.domain.cqOpen(ct.cq_attr, new Context(1000)); //this is messy, but i think it will work.  See line below for C version.
		//fi_cq_open(ct->domain, &(ct->cq_attr), &(ct->txcq), &(ct->txcq));

		ct.cq_attr.setSize(fi.getReceiveAttr().getSize());
		ct.rxcq = ct.domain.cqOpen(ct.cq_attr, new Context(2000)); //same as above
		//fi_cq_open(ct->domain, &(ct->cq_attr), &(ct->rxcq), &(ct->rxcq));

		ct.ep = ct.domain.epOpen(fi);
	}

	private Info pp_getinfo(CTPingPong ct) {
		long flags = 0;
		System.err.println(ct.hints.getCaps());
		System.err.println(ct.hints.getFabricAttr().getName());
		System.err.println(ct.hints.getFabricAttr().getProviderName());
		System.err.println(ct.hints.getFabricAttr().getProviderVersion());
		return LibFabric.getInfo(version, flags, ct.hints)[0];
	}

	private void pp_init_ep(CTPingPong ct) {
		PP_DEBUG("Initializing endpoint");

		if (ct.fi.getEndPointAttr().getEpType() == EPType.FI_EP_MSG)
			ct.ep.bind(ct.eq, 0);
		ct.ep.bind(ct.txcq, LibFabric.FI_TRANSMIT);
		ct.ep.bind(ct.rxcq, LibFabric.FI_RECV);

		ct.ep.enable();

		pp_post_rx(ct, ct.ep, ct.rx_ctx);

		PP_DEBUG("Endpoint initialzed");
	}

	private void pp_exchange_names_connected(CTPingPong ct) {
		PP_DEBUG("Connection-based endpoint: setting up connection");

		pp_ctrl_sync(ct);

		if (ct.opts.isServer) {
			pp_send_name(ct, ct.pep);

		} else {
			pp_recv_name(ct);
			ct.fi = pp_getinfo(ct);
		}
	}
	private void pp_start_server(CTPingPong ct) {

		PP_DEBUG("Connected endpoint: starting server");
		System.err.println("about to getinfo");
		ct.fi_pep = pp_getinfo(ct);
		System.err.println("about to create fabric");
		ct.fabric = new Fabric(ct.fi_pep.getFabricAttr());
		System.err.println("about to create eq");
		ct.eq = ct.fabric.eventQueueOpen(ct.eq_attr);
		System.err.println("about to create pep");
		ct.pep = ct.fabric.createPassiveEndPoint(ct.fi_pep);

		ct.eq.bind(ct.pep, 0);

		ct.pep.listen();

		PP_DEBUG("Connected endpoint: server started");
	}

	private void pp_server_connect(CTPingPong ct) {
		EventEntry eventEntry;
		EQCMEntry eqCMEntry = null;

		PP_DEBUG("Connected endpoint: connecting server");
		try {
			pp_exchange_names_connected(ct);

			pp_ctrl_sync(ct);

			/* Listen */
			eventEntry = ct.eq.sread(-1, 0);
			if(eventEntry.getClass().isAssignableFrom(EQCMEntry.class) &&eventEntry.getEQEvent() == EQEvent.FI_CONNREQ) {
				eqCMEntry = (EQCMEntry)eventEntry;
			} else {
				throw new Exception("Unexpected CM event " + eventEntry.getEQEvent() + "\n");
			}

			ct.fi = eqCMEntry.getInfo();

			ct.domain = ct.fabric.createDomain(ct.fi);

			pp_alloc_active_res(ct, ct.fi);

			pp_init_ep(ct);

			PP_DEBUG("accepting");

			ct.ep.accept();

			pp_ctrl_sync(ct);

			/* Accept */
			eventEntry = ct.eq.sread(-1, 0);

			if(eventEntry.getClass().isAssignableFrom(EQCMEntry.class) && eventEntry.getEQEvent() == EQEvent.FI_CONNECTED) {
				eqCMEntry = (EQCMEntry)eventEntry;
			} else {
				throw new Exception("Unexpected CM event " + eventEntry.getEQEvent() + "\n");
			}

			PP_DEBUG("Connected endpoint: server connected");
		} catch(Exception e) { //better exception handling should be implemented in a more complete version
			//fi_reject(ct->pep, ct->fi->handle, NULL, 0);
		}
	}

	private void pp_client_connect(CTPingPong ct) {
		EventEntry eventEntry;
		EQCMEntry entry;
		EQEvent event = null;

		pp_exchange_names_connected(ct);

		/* Check that the remote is still up */
		pp_ctrl_sync(ct);

		pp_open_fabric_res(ct);

		pp_alloc_active_res(ct, ct.fi);

		pp_init_ep(ct);

		ct.ep.connect(ct.rem_name.toString());

		pp_ctrl_sync(ct);

		/* Connect */
		try {
			eventEntry = ct.eq.sread(-1, 0);

			if(eventEntry.getClass().isAssignableFrom(EQCMEntry.class) && eventEntry.getEQEvent() == EQEvent.FI_CONNECTED) {
				entry = (EQCMEntry)eventEntry;
			} else {
				throw new Exception("Unexpected CM event " + eventEntry.getEQEvent() + "\n");
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	/*******************************************************************************
	 *                                Deallocations and Final
	 ******************************************************************************/

	void freeResources(CTPingPong ct)
	{
		PP_DEBUG("Freeing resources of test suite\n");

		if (ct.mr != null)
			PP_CLOSE_FID(ct.mr);
		PP_CLOSE_FID(ct.ep);
		PP_CLOSE_FID(ct.pep);
		PP_CLOSE_FID(ct.rxcq);
		PP_CLOSE_FID(ct.txcq);
		PP_CLOSE_FID(ct.av);
		PP_CLOSE_FID(ct.eq);
		PP_CLOSE_FID(ct.domain);
		PP_CLOSE_FID(ct.fabric);

		if (ct.fi_pep != null) {
			ct.fi_pep.free();
			ct.fi_pep = null;
		}
		if (ct.fi != null) {
			ct.fi.free();
			ct.fi = null;
		}
		if (ct.hints != null) {
			ct.hints.free();
			ct.hints = null;
		}

		PP_DEBUG("Resources of test suite freed");
	}

	private void pp_finalize(CTPingPong ct) {
		Context ctx = new Context(1);
		Message msg;

		PP_DEBUG("Terminating test");

		ct.tx_buf.put(0, (byte)'f').put(1, (byte)'i').put(2, (byte)'n');

		msg = new Message(ct.tx_buf, 1, ct.remote_fi_addr, ctx);

		ct.ep.sendMessage(msg, LibFabric.FI_INJECT | LibFabric.FI_TRANSMIT_COMPLETE);

		pp_get_tx_comp(ct, ++ct.tx_seq);

		pp_get_rx_comp(ct);

		pp_ctrl_finish(ct);

		PP_DEBUG("Test terminated");
	}

	/*******************************************************************************
	 *                                CLI: Usage and Options parsing
	 ******************************************************************************/

	private void pingPongUsage() {
		System.err.println("Usage: java PingPongTest [OPTIONS] <address:portnum>");
		System.err.println("\tOptions:");
		System.err.println("\t\t-I <number> (specify number of iterations)");
		System.err.println("\t\t-D (Debug mode)");
		System.err.println("\t\t-S (server - use this in the first call)");
		System.err.println("\t\t-L <length> (length of transfers or 'all')");

		System.exit(-1);
	}

	private void parseArgs(CTPingPong ct, String[] args) {
		for(int i = 0; i < args.length - 1; i+=2) { //last arg will be address
			char op = args[i].length() == 1 ? args[i].charAt(0) : args[i].charAt(1); //allows a minus
			switch (op) {
			case 'D':
				PP_ACTIVATE_DEBUG = true;
				i = i-1;
				break;
			case 'I':
				ct.opts.options |= PP_OPT_ITER;
				ct.opts.iterations = Integer.parseInt(args[i+1]);
				if (ct.opts.iterations < 0)
					ct.opts.iterations = 0;
				break;
			case 'S':
				ct.opts.isServer = true;
				i = i-1;
				break;
			case 'L':
				if(args[i+1].equalsIgnoreCase("all")) {
					ct.opts.sizes_enabled = ~0;
				} else {
					ct.opts.options |=PP_OPT_SIZE;
					try {
						ct.opts.transfer_size = Integer.parseInt(args[i+1]);
					} catch(NumberFormatException e) {
						System.err.println(e.getMessage());
						pingPongUsage();
					}
				}
				break;
			default:
				pingPongUsage();
			}
		}

		try {
			String[] splitAddr = args[args.length -1].split(":");
			if(!ct.opts.isServer) {
				ct.opts.dst_addr = splitAddr[0];
				ct.opts.dst_port = splitAddr[1];
			} else {
				//ct.opts.src_addr = splitAddr[0]; //no addr for server, just a port
				ct.opts.src_port = splitAddr[1];
			}
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			System.err.println(e.getMessage());
			pingPongUsage();
		}

	}

	/*******************************************************************************
	 *      PingPong core and implemenations for endpoints
	 ******************************************************************************/

	private void pingpong(CTPingPong ct) {
		pp_ctrl_sync(ct);

		pp_start(ct);
		if (ct.opts.isServer) {
			for (int i = 0; i < ct.opts.iterations; i++) {

				pp_rx(ct, ct.ep, ct.opts.transfer_size);

				if (ct.opts.transfer_size < ct.fi.getTransmitAttr().getInjectSize())
					pp_inject(ct, ct.ep, ct.opts.transfer_size);
				else
					pp_tx(ct, ct.ep, ct.opts.transfer_size);
			}
		} else {
			for (int i = 0; i < ct.opts.iterations; i++) {

				if (ct.opts.transfer_size < ct.fi.getTransmitAttr().getInjectSize())
					pp_inject(ct, ct.ep, ct.opts.transfer_size);
				else
					pp_tx(ct, ct.ep, ct.opts.transfer_size);

				pp_rx(ct, ct.ep, ct.opts.transfer_size);
			}
		}
		pp_stop(ct);

		pp_ctrl_txrx_msg_count(ct);

		PP_DEBUG("Results:");
		show_perf(ct.opts.transfer_size, ct.opts.iterations, ct.cnt_ack_msg, ct.start, ct.end, 2);
	}

	private void run_suite_pingpong(CTPingPong ct) {
		int[] sizes;

		pp_banner_fabric_info(ct);

		sizes = generate_test_sizes(ct.opts, ct.tx_size);

		PP_DEBUG("Count of sizes to test: %d", "" + sizes.length);

		for (int i = 0; i < sizes.length; i++) {
			ct.opts.transfer_size = sizes[i];
			init_test(ct, ct.opts);
			pingpong(ct);
		}

	}
	private void run_pingpong_msg(CTPingPong ct) {

		PP_DEBUG("Selected endpoint: MSG");

		ppCtrlInit(ct);

		if (ct.opts.isServer) {
			pp_start_server(ct);
		}

		if (ct.opts.isServer) {
			pp_server_connect(ct);
			PP_DEBUG("SERVER: server_connected");
		} else {
			pp_client_connect(ct);
			PP_DEBUG("CLIENT: client_connected");
		}

		run_suite_pingpong(ct);

		pp_finalize(ct);

		ct.ep.shutdown(0);
	}


	//D - debug | L - length | S - server | I - iterations | address last argument 
	public static void main(String[] args) { //allow iteration and message length arguments
		LibFabric.load();
		PingPongTest tester = new PingPongTest();
		CTPingPong ct = tester.new CTPingPong();
		ct.eq_attr = new EventQueueAttr();
		ct.cq_attr = new CQAttr();

		ct.timeout = -1;
		ct.opts = tester.new PPOpts();
		ct.opts.iterations = 1000;
		ct.opts.transfer_size = 1024;
		ct.opts.sizes_enabled = tester.PP_DEFAULT_SIZE;

		ct.eq_attr.setWaitObj(WaitObj.WAIT_UNSPEC);
		System.err.println(LibFabric.FI_MSG);
		ct.hints = new Info();
		ct.hints.getEndPointAttr().setEpType(EPType.FI_EP_MSG);
		ct.hints.setCaps(LibFabric.FI_MSG);
		ct.hints.setMode(LibFabric.FI_CONTEXT | LibFabric.FI_LOCAL_MR);

		tester.parseArgs(ct, args);

		tester.pp_banner_options(ct);

		ct.hints.setMode(LibFabric.FI_LOCAL_MR);
		tester.run_pingpong_msg(ct);

		tester.freeResources(ct);
	}
}

