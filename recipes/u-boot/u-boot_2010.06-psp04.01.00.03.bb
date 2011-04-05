require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

BRANCH = "ti81xx-master"

# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2010.06_TI814XPSP_04.01.00.03"

UVER = "2010.06"
PSPREL = "04.01.00.03"

PR = "r1"

UBOOT_MACHINE = "ti8148_evm_config"

TI_UBOOT_BINARY = "u-boot.bin"

do_compile_append () {
        unset LDFLAGS
        unset CFLAGS
        unset CPPFLAGS
        oe_runmake u-boot.ti
}

do_install_append () {
        install ${S}/${TI_UBOOT_BINARY} ${D}/boot/${TI_UBOOT_BINARY}
}

do_deploy_append () {
        install ${S}/${TI_UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY}
        package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY}

        ln -sf ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${UBOOT_SYMLINK}
}
