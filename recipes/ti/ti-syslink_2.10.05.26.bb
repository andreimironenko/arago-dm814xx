require ti-syslink.inc

PV = "2_10_05_26"

SRC_URI += "file://sdk.mk \
"

SRC_URI[syslinktarball.md5sum] = "9490a40cd5ad6eb6cd8e7a2cbc769793"
SRC_URI[syslinktarball.sha256sum] = "ec9e3e03d488c66b60afadf0e582b0606550dadf0871ac2d9d4b690cade1f447"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
