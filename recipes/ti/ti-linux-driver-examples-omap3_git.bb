DEPENDS = "virtual/kernel alsa-lib"
LICENSE = "GPLv2"

require ti-paths.inc
require ti-staging.inc

SRCREV = "08e9bb16ba295f356da081e307cd0cf72fe6920b"

PV = "psp-03.00.01.06"
PR_append = "d"

# FIXME: I don't know why SRC_URI does not work with http:// protocol
# SRC_URI = "http://gitsvr01.india.ti.com/git/psp_examples/.git;protocol=git"
SRC_URI = "http://install.source.dir.local/psp_examples_${SRCREV}.tar.gz"

COMPATIBLE_MACHINE = "(omap3evm|dm37x-evm)"

S = "${WORKDIR}/psp_examples/omap"

SRC_URI[md5sum] = "ea22be4a6c9d90ee28518c4d965de89b"
SRC_URI[sha256sum] = "a77cc71c5d0e9fe650b9e1d6fb03fff0e00d801af9153bf5d0c6e8d0934d9895"

do_compile () {
    cd ${S}/audio
    make PLAT=omap3530 \
       KERNEL_DIR="${STAGING_KERNEL_DIR}" \
       LIB_INC="${STAGING_DIR_TARGET}/usr/include" \
       LIB_DIR="${STAGING_DIR_TARGET}/usr/lib" 

    cd ${S}/video
    make PLAT=omap3530 \
       KERNEL_DIR="${STAGING_KERNEL_DIR}" \
       LIB_INC="${STAGING_DIR_TARGET}/usr/include" \
       LIB_DIR="${STAGING_DIR_TARGET}/usr/lib" 
}

do_install () {
    install -d ${D}/${LINUXEXAMPLE_SOURCE_DIR}
    cp -ar ${S}/* ${D}/${LINUXEXAMPLE_SOURCE_DIR}

    mkdir -p ${D}/${installdir}/linux-driver-examples/audio
    cp ${S}/audio/bin/* ${D}/${installdir}/linux-driver-examples/audio/
    mkdir -p ${D}/${installdir}/linux-driver-examples/video
    cp ${S}/video/bin/* ${D}/${installdir}/linux-driver-examples/video/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/linux-driver-examples/*"
INSANE_SKIP_${PN} = True

