DESCRIPTION = "GST OpenMax DM81xx Plugin for TI DM81xx ARM/DSP processors"
HOMEPAGE = "https://gforge.ti.com/gf/project/gstreamer_ti/"
SECTION = "multimedia"
LICENSE = "LGPL"

inherit autotools
require ti-paths.inc
require ti-staging.inc

INC_PR = "r1"

DEPENDS = "ti-omx gstreamer gst-plugins-base"

SRC_URI = "http://install.source.dir.local/gst-openmax-${PV}.tar.gz\
"

S = ${WORKDIR}/gst-openmax-${PV}

COMPATIBLE_MACHINE = "dm816x-evm|dm814x-evm"

GSTPLATFORM_ti814x = "Platform_dm814x"
GSTPLATFORM_ti816x = "Platform_dm816x"

OMXPLATFORM_ti814x = "ti814x-evm"
OMXPLATFORM_ti816x = "ti816x-evm"

EXTRA_OEMAKE = BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
         IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
         XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
         FC_INSTALL_DIR="${FC_INSTALL_DIR}" \
         CE_INSTALL_DIR="${CE_INSTALL_DIR}" \
         OSAL_INSTALL_DIR="${OSAL_INSTALL_DIR}" \
         UIA_INSTALL_DIR="${UIA_INSTALL_DIR}" \
         SYSLINK_INSTALL_DIR="${SYSLINK_INSTALL_DIR}" \
         LINUXUTILS_INSTALL_DIR="${LINUXUTILS_INSTALL_DIR}" \
         OMX_INSTALL_DIR="${OMX_INSTALL_DIR}" \
         LINUXKERNEL="${STAGING_KERNEL_DIR}" \
         KERNEL_INSTALL_DIR="${STAGING_KERNEL_DIR}" \
         OMX_PLATFORM="${OMXPLATFORM}" \
         CROSS_COMPILE="${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}"

do_install_append() {
    install ${S}/ext/lib/libOMX_Core.so ${D}/${libdir}
}

FILES_${PN}     += "${libdir} ${libdir}/gstreamer-0.10/*.so ${sysconfdir} ${installdir}"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.a ${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

pkg_postinst_${PN} () {
#!/bin/sh
	/sbin/ldconfig
}

INSANE_SKIP_${PN} = True
