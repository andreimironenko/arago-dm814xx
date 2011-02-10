require linux-omap3.inc

COMPATIBLE_MACHINE = "ti814x"

KVER = "2.6.37"
PSPREL = "04.01.00.02"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37-rc3_TI814XPSP_04.01.00.02.patch2"
require recipes/ti/ti-paths.inc

DEPENDS += "ti-ipc"

SYSLINKPV = "02_00_00_67"
SYSLINKPVwithdots = "02.00.00.67"
SYSLINKPVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "375c975fad16cf75cb918b6a90073e68"
SRC_URI[syslinktarball.sha256sum] = "055f7c650fffd178b07a163c10699a6817425fd6d310299fbe1bcbde6ae27f23"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI += "http://bangsdowebsvr01.india.ti.com:8060/SysLink_int/${SYSLINKPV}${SYSLINKPVextra}/exports/syslink_${SYSLINKPV}${SYSLINKPVextra}.tar.gz;name=syslinktarball \
            file://defconfig.vpss \
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
