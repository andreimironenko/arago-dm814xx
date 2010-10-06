# Using relative path here causes bitbake to search in
# BBPATH for the first instance of x-load.inc rather
# than just within the current directory.
require recipes/x-load/x-load.inc

COMPATIBLE_MACHINE = "omap3evm|am37x-evm|dm37x-evm|am3517-evm"

SRCREV = "fc6d5be15c703d21aef0ae0b8c02177721f0445f"

PR = "r2"

SRC_URI = "git://arago-project.org/git/projects/x-load-omap3.git;protocol=git"

XVER = "1.46"
PSPREL = "03.00.01.06"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
