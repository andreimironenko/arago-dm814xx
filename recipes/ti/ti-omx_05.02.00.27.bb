DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_27"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "fcd3756072c2b9df7baede6e63039f1d"
SRC_URI[omx.sha256sum] = "35a79ac4458e040a9be85c4f2cf78736389ea80cb6636735b79f95f735cad574"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
