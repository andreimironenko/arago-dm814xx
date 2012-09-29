DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_44"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "f79e36256296050df9c9918ff2657ca8"
SRC_URI[omx.sha256sum] = "18f58f9f8dece7ffd0771252454a9b4c8b9f64d44db2640368feff9dac99e536"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
