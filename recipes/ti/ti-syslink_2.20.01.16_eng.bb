require ti-syslink.inc

PV = "2_20_01_16"
PVExtra = "_eng"

SRC_URI += "file://sdk.mk \
"

SRC_URI[syslinktarball.md5sum] = "49ebf942bacbbf2ad69c4524ea47784c"
SRC_URI[syslinktarball.sha256sum] = "ace203df8e338a8b7175e03bce2ff5c0063f2a4c79df85d40ce48a09ceb9443b"


do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
