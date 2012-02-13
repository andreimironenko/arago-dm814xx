require ti-linuxutils.inc

PE = "1"
PV = "3_21_00_04"
PVExtra = ""

SRC_URI += "file://sdk.mk \
"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
