require linux-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

KVER = "2.6.34"
PSPREL = "04.00.00.07"

BRANCH = "ti816x-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.34_TI816XPSP_04.00.00.07"
require recipes/ti/ti-paths.inc

DEPENDS += "ti-ipc"

SYSLINKPV = "02_00_00_56"
SYSLINKPVwithdots = "02.00.00.56"
SYSLINKPVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "2b2f097a63dabc066a42323bdf3cdb9e"
SRC_URI[syslinktarball.sha256sum] = "3a3ff92e3f2a181df1ff8392d3c1543ce802fb6467a375d0d9fd74cf04b69cda"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI += "http://bangsdowebsvr01.india.ti.com:8060/SysLink/${SYSLINKPV}${SYSLINKPVextra}/REL_SYSLINK_${SYLINKPVwithdots}/syslink_${SYSLINKPV}${SYSLINKPVextra}.tar.gz;name=syslinktarball \
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
SYSLINKPLATFORM          ?= "<UNDEFINED_SYSLINKPLATFORM>"

SYSLINKLOADER_omap3       = "COFF"
SYSLINKLOADER_omapl137    = "COFF"
SYSLINKLOADER_omapl138    = "COFF"
SYSLINKLOADER_ti816x      = "ELF"
SYSLINKLOADER            ?= "<UNDEFINED_SYSLINKLOADER>"

do_compilesyslinkkernelmodule() {

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    # Build the gpp (hlos) kernel space
    cd ${SYSLINK_ROOT}/ti/syslink/utils/hlos/knl/Linux && make \
        ARCH="${TARGET_ARCH}" \
        CROSS_COMPILE="${TARGET_PREFIX}" \
        SYSLINK_PLATFORM="${SYSLINKPLATFORM}" \
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
