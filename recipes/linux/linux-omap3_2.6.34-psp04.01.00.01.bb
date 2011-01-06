SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI814X EVM from PSP, based on linux-omap3 kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

inherit kernel

SRC_URI = "ftp://ftp.india.ti.com/PSP/Releases/ODC/Centaurus_PSP/TI814X-LINUX-PSP-04.01.00.01.tgz;name=psptarball"
SRC_URI[psptarball.md5sum] = "7d57dfbf4b40feae6500b579a86381aa"
SRC_URI[psptarball.sha256sum] = "d85417337144f2a635273af0e92b7d69a24cbfdd368a97ebd2f805006cb801cb"

# Requires support for SOC_FAMILY matching in COMPATIBLE_MACHINE
COMPATIBLE_MACHINE = "ti814x"

S = "${WORKDIR}/TI814X-LINUX-PSP-04.01.00.01/src/kernel/linux-04.01.00.01"

addtask unpack_psp_kernel before do_patch after do_unpack
do_unpack_psp_kernel() {
    cd "${WORKDIR}/TI814X-LINUX-PSP-04.01.00.01/src/kernel"
    tar -zxf linux-04.01.00.01.tar.gz
}

# Do not include generic linux.inc, but copy defconfig in place
addtask setup_defconfig before do_configure after do_patch
do_setup_defconfig() {
	cp ${S}/arch/arm/configs/ti8148_evm_defconfig ${S}/.config
}
