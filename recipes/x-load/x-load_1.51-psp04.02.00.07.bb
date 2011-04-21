# Using relative path here causes bitbake to search in
# BBPATH for the first instance of x-load.inc rather
# than just within the current directory.
require recipes/x-load/x-load.inc

COMPATIBLE_MACHINE = "omap3"

SRCREV = "v1.51_OMAPPSP_04.02.00.07"

PR = "r2"

SRC_URI = "git://arago-project.org/git/projects/x-load-omap3.git;protocol=git"

XVER = "1.51"
PSPREL = "04.02.00.07"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
