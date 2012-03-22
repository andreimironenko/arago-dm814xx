require ti-linuxutils.inc

PE = "1"
PV = "3_22_00_02"

SRC_URI += "file://sdk.mk \
"

SRC_URI[md5sum] = "0b773e2814ed83389e65a097f17250e0"
SRC_URI[sha256sum] = "13ccae405823999a1b17d4a8dfeefaa993a0691acce835b7f490ecc93fd48bc5"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
