require recipes/linux/multi-kernel.inc

DESCRIPTION = "Linux kernel for TNETV107X processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "tnetv107x-evm"

DEFAULT_PREFERENCE = "1"

BRANCH = "staging"
SRCREV = "9e15dfae803a689a178b09ab02358866c0e34d14"
KVER = "2.6.37-rc2"

PV = "${KVER}-${PR}"

SRC_URI = "git://arago-project.org/git/people/cyril/linux-tnetv107x.git;protocol=git;branch=${BRANCH} \
	file://defconfig"

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${MACHINE}"
MODULES_IMAGE_BASE_NAME = "modules-${PV}-${MACHINE}"
FILESPATHPKG := "${@bb.data.getVar('FILESPATHPKG', d, 1).replace(':files:', ':linux-tnetv107x:files:', 1)}"
