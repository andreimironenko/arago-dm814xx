DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_32"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "edc8a46ef0095b645ac9b9b5dc000bbc"
SRC_URI[omx.sha256sum] = "d1c7059cce55b034e87ad0521df30530afca263a33669a17227a1af292ae40d3"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
