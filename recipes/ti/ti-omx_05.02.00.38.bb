DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_38"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://0001-domx-Increase-buffers-so-that-multiple-channels-can-.patch \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "ff472c4552c2484cba0c668b9cfa65f4"
SRC_URI[omx.sha256sum] = "51e1060f6d6fcf7e9168b812b44e3e2e7699cd510c913af3fff69e1a85a3210f"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
