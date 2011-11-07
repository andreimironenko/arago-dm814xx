require ti-omx.inc

PV = "05_02_00_21"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM81xx-OMX/${PV}/exports/omx-ti81xx_${PV}.tar.gz;name=omx \
           file://sdk.mk \
"

SRC_URI[omx.md5sum] = "74fbd7b6dbb9815958d8a88eb4467a0b"
SRC_URI[omx.sha256sum] = "e560d07ef53b3454c59c08e55efbe776697c834ca083949dce1a8a51ad1468a1"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
