CRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_40"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "8ac9c2e25850980e870afcbb9fbd9520"
SRC_URI[omx.sha256sum] = "94e4f00439ea97ca4c0a88f9c033dea1bc491ab796d91e79fd8abaf143afa6f0"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
