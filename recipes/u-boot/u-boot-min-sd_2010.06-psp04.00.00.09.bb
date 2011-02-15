require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

BRANCH = "ti816x-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI816XPSP_04.00.00.09"

UVER = "2010.06"
PSPREL = "04.00.00.09"

SRC_URI =+ "file://${PSPREL}/0001-ti8168-Make-default-build-for-DDR3-400MHz.patch"

PR = "r1"

UBOOT_MACHINE = "ti8168_evm_min_sd"
TI_UBOOT_BINARY ?= "u-boot.min.sd"
UBOOT_MLO_SYMLINK ?= "MLO"
UBOOT_MLO_IMAGE ?= "MLO-${MACHINE}-${PV}-${PR}"

do_compile_append () {
        unset LDFLAGS
        unset CFLAGS
        unset CPPFLAGS
        oe_runmake u-boot.ti
}

do_install() {
    cp ${S}/${TI_UBOOT_BINARY} ${S}/${UBOOT_MLO_IMAGE}
    install -d ${D}/boot
    install ${S}/${UBOOT_MLO_IMAGE} ${D}/boot/${UBOOT_MLO_IMAGE}
    ln -sf ${UBOOT_MLO_IMAGE} ${D}/boot/${UBOOT_MLO_SYMLINK}
} 

do_deploy() {
    cp ${S}/${TI_UBOOT_BINARY} ${S}/${UBOOT_MLO_IMAGE}
    install -d ${DEPLOY_DIR_IMAGE}
    install ${S}/${UBOOT_MLO_IMAGE} ${DEPLOY_DIR_IMAGE}/${UBOOT_MLO_IMAGE}
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${UBOOT_MLO_IMAGE}

    cd ${DEPLOY_DIR_IMAGE}
    rm -f ${UBOOT_MLO_SYMLINK}
    ln -sf ${UBOOT_MLO_IMAGE} ${UBOOT_MLO_SYMLINK}
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${UBOOT_MLO_SYMLINK}
}
