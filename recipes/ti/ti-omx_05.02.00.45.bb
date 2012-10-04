DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_45"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "9df1094cd7ba5fa35c7628c9902cdde0"
SRC_URI[omx.sha256sum] = "49948347c6b2b3b18905f324910bf1732b2bffdf403fd9f71c9fa1bb30573f5c"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
