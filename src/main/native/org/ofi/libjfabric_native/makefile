#
# Maven sets JAVA_HOME aka java.home to ${JAVA_HOME}/jre.  Need to add a funky include for this.
#

CFLAGS=-g -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin -I/Users/ngraham/libfabric-cray/include/rdma \
	-I/Users/ngraham/libfabric-cray/include

LIBFAB_LIB_LOCATION=-L/Users/ngraham/libfabric_install/lib

LDFLAGS= #-ldl

C_SOURCES = 				\
	av_type.c				\
	mr_mode.c				\
	progress.c				\
	threading.c				\
	resource_mgmt.c			\
	ep_type.c				\
	protocol.c				\
	fabric.c				\
	libfabric.c				\
	domain_attr.c			\
	ep_attr.c				\
	fabric_attr.c			\
	receive_attr.c			\
	tx_attr.c				\
	specified_domain_attr.c	\
	specified_fabric_attr.c	\
	info.c

O_FILES =					\
	av_type.o				\
	mr_mode.o				\
	progress.o				\
	threading.o				\
	resource_mgmt.o			\
	ep_type.o				\
	protocol.o				\
	fabric.o				\
	libfabric.o				\
	domain_attr.o			\
	ep_attr.o				\
	fabric_attr.o			\
	receive_attr.o			\
	tx_attr.o				\
	specified_domain_attr.o	\
	specified_fabric_attr.o	\
	info.o

all: libjfab_native.dylib

libjfab_native.dylib: ${O_FILES}
	gcc ${CFLAGS} -dynamiclib -install_name /Users/ngraham/libfabric_install/lib/libfabric.dylib ${O_FILES} -o libjfab_native.dylib ${LDFLAGS} ${LIBFAB_LIB_LOCATION} -lfabric

${O_FILES}: ${C_SOURCES}
	gcc -c ${CFLAGS} ${C_SOURCES}

clean:
	rm -f *.class *.o
	rm -f org_*.h
	rm -f libjfab_native.dylib

#test:
#	javac -g -Xlint:deprecation -classpath ${CLASSPATH} Test.java