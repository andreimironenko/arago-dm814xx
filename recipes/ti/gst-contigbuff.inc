DESCRIPTION = "GST Contigious Buffer Plugin for TI C6A811x ARM/DSP processors"
HOMEPAGE = "https://gforge.ti.com/gf/project/gstreamer_ti/"
SECTION = "multimedia"
LICENSE = "LGPL"

inherit autotools
require ti-paths.inc
require ti-staging.inc

INC_PR = "r1"

DEPENDS = "gstreamer gst-plugins-base ti-xdctools ti-syslink"

SRC_URI = "http://install.source.dir.local/gst-contigbuff_${PV}.tar.gz\
"

S = ${WORKDIR}/gst-contigbuff_${PV}

COMPATIBLE_MACHINE = "ti811x"

EXTRA_OEMAKE = BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR} \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
         XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
         SYSLINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
         LINUXUTILS_INSTALL_DIR="${LINUXUTILS_INSTALL_DIR}" \
         GST_OMX_INSTALL_DIR="${S}" \
         LINUXKERNEL="${STAGING_KERNEL_DIR}" \
         KERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
         CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}"

FILES_${PN}     += "${libdir}/gstreamer-0.10/*.so ${sysconfdir} ${installdir}"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.a ${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"
