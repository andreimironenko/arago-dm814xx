require ti-syslink.inc

PV = "2_10_03_20"

SRC_URI += "file://sdk.mk \
"

SRC_URI[syslinktarball.md5sum] = "99078e974d63d7a75ca3a0b6a367cd36"
SRC_URI[syslinktarball.sha256sum] = "9676892ebcf4c49fc39ac99e6864677dba41843479b40f29432bc40179e3c47f"


do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
