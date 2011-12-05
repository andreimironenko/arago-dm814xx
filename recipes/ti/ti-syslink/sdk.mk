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
prefix ?= /usr
libdir ?= /lib
includedir ?= /include
docdir ?= /share/ti/syslink/doc


#
# == Helper variables ==
#

LIBS = $(subst packages/,,$(wildcard packages/ti/syslink/lib/*.a*))
HEADERS  = $(subst packages/,,$(wildcard packages/ti/syslink/*.h))
HEADERS += $(subst packages/,,$(wildcard packages/ti/syslink/utils/*.h))
DOCS = $(wildcard *ReleaseNotes.html) $(wildcard docs/*)

LIBDIR = $(DESTDIR)/$(prefix)/$(libdir)
INCDIR = $(DESTDIR)/$(prefix)/$(includedir)
DOCDIR = $(DESTDIR)/$(prefix)/$(docdir)

# 
# ==== Targets ====
#

install:
	@echo "Installing libraries.."
	@mkdir -p $(LIBDIR)
	@cd packages && cp -f --parents $(LIBS) $(LIBDIR)
	@echo "Installing header files.."
	@mkdir -p $(INCDIR)
	@cd packages && cp -f --parents $(HEADERS) $(INCDIR)
	@echo "Installing documentation.."
	@mkdir -p $(DOCDIR)
	@cp -fR $(DOCS) $(DOCDIR)

help:
	@echo
	@echo "Available build targets are:"
	@echo
	@echo "  install"
	@echo "      Install the headers and libraries in the DESTDIR directory"
	@echo
