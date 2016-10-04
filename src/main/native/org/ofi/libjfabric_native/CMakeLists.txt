#
# Copyright (c) 2015 Los Alamos Nat. Security, LLC. All rights reserved.
#
# This software is available to you under a choice of one of two
# licenses.  You may choose to be licensed under the terms of the GNU
# General Public License (GPL) Version 2, available from the file
# COPYING in the main directory of this source tree, or the
# BSD license below:
#
#     Redistribution and use in source and binary forms, with or
#     without modification, are permitted provided that the following
#     conditions are met:
#
#      - Redistributions of source code must retain the above
#         copyright notice, this list of conditions and the following
#         disclaimer.
#
#      - Redistributions in binary form must reproduce the above
#        copyright notice, this list of conditions and the following
#        disclaimer in the documentation and/or other materials
#        provided with the distribution.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
# EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
# MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
# NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
# BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
# ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
# CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
#

cmake_minimum_required(VERSION 2.8)

project(Criterion C)

set(MODULE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/.cmake/Modules")
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${MODULE_DIR})
set(CMAKE_INSTALL_RPATH "${CMAKE_INSTALL_PREFIX}/lib")

# helper functions

function(flatten_list INPUT SEPARATOR OUTPUT)
  string (REPLACE ";" "${SEPARATOR}" _TMPS "${INPUT}")
  set (${OUTPUT} "${_TMPS}" PARENT_SCOPE)
endfunction()

# Content options

# Initialization


# Find JNI related items

find_package(JNI REQUIRED)

# Where's libfabric installed?

find_package(PkgConfig REQUIRED)
pkg_check_modules(LIBFABRIC libfabric)
if(LIBFABRIC_FOUND)
    flatten_list("${LIBFABRIC_CFLAGS}" " " LIBFABRIC_CFLAGS)
    flatten_list("${LIBFABRIC_LDFLAGS}" " " LIBFABRIC_LDFLAGS)
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${LIBFABRIC_CFLAGS}")
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_LD_FLAGS} ${LIBFABRIC_LDFLAGS}")
    message(STATUS ${CMAKE_SHARED_LINKER_FLAGS})
    message(STATUS "Found libfabric, building LibJFabric native methods. ${LIBFABRIC_FLAGS}")
else()
    message(FATAL_ERROR "Failed to find libfabric library/include files.  Check PKG_CONFIG_PATH.")
endif(LIBFABRIC_FOUND)

include_directories(SYSTEM
  ${GENERATED_JAVAH}
  ${CMAKE_CURRENT_SOURCE_DIRS}
  ${JNI_INCLUDE_DIRS}
  ${LIBFABRIC_INCLUDE_DIRS}
)

# Project setup & environment variables

set(PROJECT_VERSION "0.0.0")
set(PROJECT_SOVERSION 0)
set(LOCALEDIR_REL "share/locale")
set(LOCALEDIR "${CMAKE_INSTALL_PREFIX}/${LOCALEDIR_REL}")
set(GettextTranslate_ALL 1)
set(GettextTranslate_GMO_BINARY 1)

#add_definitions(-DCRITERION_BUILDING_DLL=1)

set(CMAKE_C_FLAGS_DEFAULT "${CMAKE_C_FLAGS}")

#set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -Werror -g")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -g")

# Compilation options

# Find dependencies

#include(CheckLibraryExists)
#CHECK_LIBRARY_EXISTS(rt clock_gettime "time.h" HAVE_CLOCK_GETTIME)

# List sources and headers

set(SOURCE_FILES
  av_type.c
  context.c
  domain_attr.c
  ep_attr.c
  ep_type.c
  fabric.c
  fabric_attr.c
  fi_descriptor.c
  info.c
  libfabric.c
  mr_mode.c
  progress.c
  protocol.c
  receive_attr.c
  resource_mgmt.c
  specified_domain_attr.c
  specified_fabric_attr.c
  threading.c
  tx_attr.c
  wait_obj.c
  endpoint.c
  cq_format.c
  cq_wait_cond.c
  cq_attr.c
  constant.c
  passive_ep.c
  event_queue.c
  eq_event.c
  memory_region.c
  completion_queue.c
  message.c
)

set(INTERFACE_FILES
  libfabric.h
)

# Generate the configure file

#configure_file(
#  "${CMAKE_CURRENT_SOURCE_DIR}/src/config.h.in"
#  "${CMAKE_CURRENT_SOURCE_DIR}/src/config.h"
#)

include_directories(include src)
add_library(jfab_native SHARED ${SOURCE_FILES} ${INTERFACE_FILES})
set_target_properties(jfab_native PROPERTIES
   VERSION ${PROJECT_VERSION}
   SOVERSION ${PROJECT_SOVERSION}
   MACOSX_RPATH "${CMAKE_INSTALL_PREFIX}/lib"
)

target_link_libraries(jfab_native)

install(TARGETS jfab_native
  LIBRARY DESTINATION lib
  ARCHIVE DESTINATION lib COMPONENT dev
)

# Add toolchain patch number for incremental deb builds
set(PROJECT_VERSION "${PROJECT_VERSION}-2")

