DESCRIPTION = "Audio Head Unit Sample demo app"
LICENSE = "TI Proprietary"

require ti-paths.inc
#require ti-staging.inc

COMPATIBLE_MACHINE = "ti811x"

DEPENDS = "qwt"
DEPENDS += "virtual/kernel"

SRC_URI = "http://install.source.dir.local/ahudemo-1.00.00.02.tar.gz"

SRC_URI[md5sum] = "09d7773c1d9b1f85ed340076c6af3d56"
SRC_URI[sha256sum] = "b35cb99c2874971526200880b6aff02dbbe2383b31835966327417f64aecf756"

S = ${WORKDIR}/ahudemo-1.00.00.02/

inherit qt4e

do_prepsources() {
    rm -f ${S}/cpu_usage/cpu_usage
    cp -f ${S}/cpu_usage/* ${S}/
    rm -rf ${S}/cpu_usage
}

addtask prepsources after do_unpack before do_patch

do_compile() {
    cd ${S}
    make PLATFORM=${PLATFORM}

    cd ${WORKDIR}/ahudemo-1.00.00.02/fbsetup
    make PLATFORM=${PLATFORM} \
         CSTOOL_DIR=${TOOLCHAIN_PATH} \
         CSTOOL_PREFIX=${TARGET_PREFIX} \
         KERNEL_INSTALL_DIR=${WORKDIR}/../../../sysroots/c6a811x-evm-none-linux-gnueabi/kernel
}

do_install() {
    install -d ${D}/${installdir}/ahudemo
    install ${WORKDIR}/ahudemo-1.00.00.02/fbsetup/fbsetup ${D}/${installdir}/ahudemo
    install ${S}/cpu_usage ${D}/${installdir}/ahudemo/
    install ${WORKDIR}/ahudemo-1.00.00.02/matrix/matrix-gui-e ${D}/${installdir}/ahudemo/
    install ${WORKDIR}/ahudemo-1.00.00.02/matrix/*.html ${D}/${installdir}/ahudemo/
    install ${WORKDIR}/ahudemo-1.00.00.02/matrix/*.png ${D}/${installdir}/ahudemo/
}

PACKAGES += "ahudemo-test-app"
FILES_ahudemo-test-app = "${installdir}/ahudemo"
INSANE_SKIP_ahudemo-test-app = True

