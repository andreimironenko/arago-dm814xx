require u-boot-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/TI814X-LINUX-PSP-04.01.00.01.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "7d57dfbf4b40feae6500b579a86381aa"
SRC_URI[psptarball.sha256sum] = "d85417337144f2a635273af0e92b7d69a24cbfdd368a97ebd2f805006cb801cb"

S = "${WORKDIR}/TI814X-LINUX-PSP-04.01.00.01/src/u-boot/u-boot-04.01.00.01"

UVER = "2010.06"
PSPREL = "04.01.00.01"

SRC_URI =+ "file://${PSPREL}/0001-ti8168-Make-default-build-for-DDR3-400MHz.patch"

PR = "r2"

UBOOT_MACHINE = "ti8148_evm_config"

TI_UBOOT_BINARY = "u-boot.noxip.bin"

addtask unpack_psp_uboot before do_patch after do_unpack
do_unpack_psp_uboot() {
    cd "${WORKDIR}/TI814X-LINUX-PSP-04.01.00.01/src/u-boot"
    tar -zxf u-boot-04.01.00.01.tar.gz
}

# do_compile_append () {
#         unset LDFLAGS
#         unset CFLAGS
#         unset CPPFLAGS
#         oe_runmake u-boot.ti
# }
# 
# do_install_append () {
#         install ${S}/${TI_UBOOT_BINARY} ${D}/boot/${TI_UBOOT_BINARY}
# }
# 
# do_deploy_append () {
#         install ${S}/${TI_UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY}
#         package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY}
# 
#         ln -sf ${DEPLOY_DIR_IMAGE}/${TI_UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${UBOOT_SYMLINK}
# }
