DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_25"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "767e5a0ea246c9de096d76c3fab27716"
SRC_URI[omx.sha256sum] = "6aa131e8acf2ba94894ade912ad6a64fd3aa0936067a1b0ba80c860ce70c3af8"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
