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

S = ${WORKDIR}/ahudemo-1.00.00.02/cpu_usage

inherit qt4e

do_compile() {
    make PLATFORM=${PLATFORM}

    cd ${WORKDIR}/ahudemo-1.00.00.02/fbsetup
    make PLATFORM=${PLATFORM} \
         CSTOOL_DIR=${TOOLCHAIN_PATH} \
         CSTOOL_PREFIX=${TARGET_PREFIX} \
         KERNEL_INSTALL_DIR=${WORKDIR}/../../../sysroots/c6a811x-evm-none-linux-gnueabi/kernel
}
