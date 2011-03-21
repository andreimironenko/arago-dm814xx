# Using relative path here causes bitbake to search in
# BBPATH for the first instance of x-load.inc rather
# than just within the current directory.
require recipes/x-load/x-load.inc

COMPATIBLE_MACHINE = "am37x-evm|dm37x-evm|am3517-evm"

SRCREV = "fc6d5be15c703d21aef0ae0b8c02177721f0445f"

PR = "r0"

# Added ECC patch
SRC_URI = "git://arago-project.org/git/projects/x-load-omap3.git;protocol=git \
           file://0001-Xloader-BCH-4b8b-Error-Correction.patch \
          "

XVER = "1.46"
PSPREL = "03.00.01.06.sdk"

S = "${WORKDIR}/git"

# Add FOUR_BIT_ERROR_CORRECTION flag to the flags passed to the compile
EXTRA_OEMAKE += "PLATFORM_RELFLAGS=-DFOUR_BIT_ERROR_CORRECT"

PACKAGE_ARCH = "${MACHINE_ARCH}"
