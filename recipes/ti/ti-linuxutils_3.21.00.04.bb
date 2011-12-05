require ti-linuxutils.inc

PE = "1"
PV = "3_21_00_04"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Linux_Utils/${PV}/exports/linuxutils_${PV}${PVExtra}.tar.gz"

SRC_URI[md5sum] = "bf17113d117796ce7ebf75d674faf03f"
SRC_URI[sha256sum] = "5416aec6df40ac63210882d31e9fdef7a10548dec9a8a41d9042076a232c27ee"

SRC_URI += "file://sdk.mk \
"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
