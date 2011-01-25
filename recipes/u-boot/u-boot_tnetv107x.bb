require recipes/u-boot/u-boot.inc

DESCRIPTION = "U-Boot Bootloader for TNETV107X processors"
COMPATIBLE_MACHINE = "tnetv107x-evm"

PN = "u-boot"
PR = "tnetv107x"

BRANCH = "master"
SRCREV = "2af780994c858e0a8f35400bfc36634ad8f89c11"
SRC_URI = "git://arago-project.org/git/people/cyril/u-boot-tnetv107x.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
