DESCRIPTION = "TI OMX Components"
SECTION = "devel"
LICENSE = "BSD"

require ti-omx.inc

PV = "05_02_00_43"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
           file://disable-tiler-for-syslink-dsp-for-omx.patch \
"

SRC_URI[omx.md5sum] = "bd80b01981e2961db53cc97057786a5e"
SRC_URI[omx.sha256sum] = "8947f92cd2c1f673e681ada51b4db3672fd0b9ac514b2e5d1deb4564d35f2ecb"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
