require ti-syslink.inc

PV = "2_20_00_14"
PVExtra = ""

SRC_URI += "file://sdk.mk \
"

SRC_URI[syslinktarball.md5sum] = "fe2307401b3aaf0c00fd34083aa048c3"
SRC_URI[syslinktarball.sha256sum] = "f7848ac66fafb9e3595d68d804aa0c6aed83f02bf8cba9cda5c875080f170a97"


do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
