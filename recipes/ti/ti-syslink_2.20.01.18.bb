require ti-syslink.inc

PV = "2_20_01_18"
PVExtra = ""

SRC_URI += "file://sdk.mk \
"

SRC_URI[syslinktarball.md5sum] = "50610e34caeaccede955fcd73600c83d"
SRC_URI[syslinktarball.sha256sum] = "588c7d2818dae00315d29b6ca40efa40baadbcb4aa5fe24e51c9fede7f11c533"


do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
