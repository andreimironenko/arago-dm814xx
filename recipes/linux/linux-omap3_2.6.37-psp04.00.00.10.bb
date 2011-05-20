require linux-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

KVER = "2.6.37"
PSPREL = "04.00.00.10"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37_TI816XPSP_04.00.00.10"
require recipes/ti/ti-paths.inc

DEPENDS += "ti-ipc"

SYSLINKPV = "02_00_00_68"
SYSLINKPVwithdots = "02.00.00.68"
SYSLINKPVextra = "_beta1"

SRC_URI[syslinktarball.md5sum] = "468034372124f70f82b60cfb5f11c8e8"
SRC_URI[syslinktarball.sha256sum] = "ed574dcb3a5477cfbc69a1c9e768d5197291cb057d19fd791e16e1c89af3e8e1"

SRC_URI += "http://software-dl.ti.com/dsps/dsps_public_sw/SysLink/${SYSLINKPV}${SYSLINKPVextra}/exports/syslink_${SYSLINKPV}${SYSLINKPVextra}.tar.gz;name=syslinktarball \
            file://defconfig.vpss \
            file://fbdev_omx_int.patch \
"

SYSLINK_ROOT = "${WORKDIR}/syslink_${SYSLINKPV}${SYSLINKPVextra}"
export SYSLINK_ROOT

export IPCDIR = "${IPC_INSTALL_DIR}/packages"

export KBUILD_EXTRA_SYMBOLS=$SYSLINK_ROOT/ti/syslink/utils/hlos/knl/Linux/Module.symvers 

SYSLINKPLATFORM_omap     = "OMAP3530"
SYSLINKPLATFORM_omapl137  = "OMAPL1XX"
SYSLINKPLATFORM_omapl138  = "OMAPL1XX"
SYSLINKPLATFORM_ti816x    = "TI81XX"
SYSLINKPLATFORM_ti814x    = "TI81XX"
SYSLINKPLATFORM          ?= "<UNDEFINED_SYSLINKPLATFORM>"

SYSLINKLOADER_omap3       = "COFF"
SYSLINKLOADER_omapl137    = "COFF"
SYSLINKLOADER_omapl138    = "COFF"
SYSLINKLOADER_ti816x      = "ELF"
SYSLINKLOADER_ti814x      = "ELF"
SYSLINKLOADER            ?= "<UNDEFINED_SYSLINKLOADER>"

SYSLINKVARIANT_omap3     = "OMAP3530"
SYSLINKVARIANT_omapl137  = "OMAPL1XX"
SYSLINKVARIANT_omapl138  = "OMAPL1XX"
SYSLINKVARIANT_ti816x    = "TI816X"
SYSLINKVARIANT_ti814x    = "TI814X"
SYSLINKVARIANT          ?= "<UNDEFINED_SYSLINKVARIANT>"

do_compilesyslinkkernelmodule() {

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    # Build the gpp (hlos) kernel space
    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/knl/Linux && make \
        ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
        SYSLINK_VARIANT="${SYSLINKVARIANT}" \
        SYSLINK_LOADER="${SYSLINKLOADER}" \
        SYSLINK_PKGPATH="${IPC_INSTALL_DIR}/packages" \
        KDIR="${S}"
}
addtask compilesyslinkkernelmodule after do_compile before do_setup_defconfigvpss

do_setup_defconfigvpss() {
        cp ${WORKDIR}/defconfig.vpss ${S}/.config
}

addtask setup_defconfigvpss after do_compilesyslinkkernelmodule before do_compile_kernelmodules

do_compile_kernelmodules() {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
        oe_runmake modules  CC="${KERNEL_CC}" LD="${KERNEL_LD}" KBUILD_EXTRA_SYMBOLS="${KBUILD_EXTRA_SYMBOLS}"
}

addtask compile_kernelmodules after do_setup_defconfigvpss before do_sizecheck
