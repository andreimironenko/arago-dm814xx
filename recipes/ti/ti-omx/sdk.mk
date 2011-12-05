# 
#  Copyright (c) 2011, Texas Instruments Incorporated
#  All rights reserved.
# 
#  Redistribution and use in source and binary forms, with or without
#  modification, are permitted provided that the following conditions
#  are met:
# 
#  *  Redistributions of source code must retain the above copyright
#     notice, this list of conditions and the following disclaimer.
# 
#  *  Redistributions in binary form must reproduce the above copyright
#     notice, this list of conditions and the following disclaimer in the
#     documentation and/or other materials provided with the distribution.
# 
#  *  Neither the name of Texas Instruments Incorporated nor the names of
#     its contributors may be used to endorse or promote products derived
#     from this software without specific prior written permission.
# 
#  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
#  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
#  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
#  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
#  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
#  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
#  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
#  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
#  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
#  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
#  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
# 

#
# ==== Standard Variables ====
#


#
# Where to install/stage the packages
# Typically this would point to the devkit location
#

DESTDIR ?= $(HOME)/target-root
packagedir ?= /packages
libdir ?= /lib
includedir ?= /include
ifeq ($(docdir),)
    docdir := /share/ti/omx/doc
    xdcdocdir := /doc/omx
endif

ifeq ($(prefix),)
    prefix := /usr
    xdcprefix := /
endif

#
# Installation of XDCTools. This must be passed to the Makefile for the
# install-xdc target.
#

XDC_INSTALL_DIR="<UNDEFINED>"



# 
# ==== Tools ====
#

XS=$(XDC_INSTALL_DIR)/xs
XDC=$(XDC_INSTALL_DIR)/xdc
XDCPKG=$(XDC_INSTALL_DIR)/bin/xdcpkg

#
# == Helper variables ==
#

PACKAGES=`$(call all_packages) | grep -v wizards | grep -v examples | xargs`
LIBSINPACKAGES = $(shell cd src && find . -name '*.ae674' -or -name '*.av5T' -type f)
LIBSINBIN = $(shell cd bin && find . -name '*.ae674' -or -name '*.av5T' -type f)
LIBSINLIB = $(shell cd lib && find ti -name '*.ae674' -or -name '*.av5T' -type f)
HEADERS = $(shell cd src && find . -name '*.h' -type f -not \( -name "package.defs.h" -o -name "_*.h" \))
DOCS = $(wildcard *ReleaseNotes.PDF) $(wildcard *UserGuide.pdf)

LIBDIR = $(DESTDIR)/$(prefix)/$(libdir)
INCDIR = $(DESTDIR)/$(prefix)/$(includedir)
DOCDIR = $(DESTDIR)/$(prefix)/$(docdir)
PACKAGEDIR = $(DESTDIR)/$(xdcprefix)/$(packagedir)
XDCDOCDIR = $(DESTDIR)/$(xdcprefix)/$(xdcdocdir)

#
# ==== Helper functions ====
#

purge = @find $(1) -name $(2) -exec rm -rf {} \;

all_packages = $(XDCPKG) packages | sed -e 's:packages/::' -e 's:/:.:g'



# 
# ==== Targets ====
#

install-packages:
	@echo "Installing packages.."
	@mkdir -p $(PACKAGEDIR)
	@$(XS) xdc.tools.repoman -c -r $(PACKAGEDIR) -p packages $(PACKAGES)
	$(call purge,$(PACKAGEDIR),'*.c')
	$(call purge,$(PACKAGEDIR),'*.bld')
	@echo "Installing documentation.."
	@mkdir -p $(XDCDOCDIR)
	@cp $(DOCS) $(XDCDOCDIR)

install:
	@echo "Installing libraries.."
	@mkdir -p $(LIBDIR)
ifneq ($(LIBSINPACKAGES),)
	@cd src && cp -f --parents $(LIBSINPACKAGES) $(LIBDIR)
endif
ifneq ($(LIBSINBIN),)
	@cd bin && cp -f --parents $(LIBSINBIN) $(LIBDIR)
endif
ifneq ($(LIBSINLIB),)
	@cd lib && cp -f --parents $(LIBSINLIB) $(LIBDIR)
endif
	@echo "Installing header files.."
	@mkdir -p $(INCDIR)
	@cd src && cp -f --parents $(HEADERS) $(INCDIR)
	@echo "Installing documentation.."
	@mkdir -p $(DOCDIR)
	@cp -f $(DOCS) $(DOCDIR)

help:
	@echo
	@echo "Available build targets are:"
	@echo
	@echo "  install-packages"
	@echo "      Install the RTSC packages in the repository at DESTDIR"
	@echo
	@echo "  install"
	@echo "      Install the headers and libraries in the DESTDIR directory"
	@echo
