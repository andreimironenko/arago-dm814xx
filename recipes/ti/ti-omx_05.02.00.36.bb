DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_36"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"
SRC_URI[omx.md5sum] = "e66c8dca057e42a427e2255532185a0a"
SRC_URI[omx.sha256sum] = "67dded0b3270d22dc9b95f3af7b6d8ffc47a2f65dbc8ee440c16fd83c6b6cbcb"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
