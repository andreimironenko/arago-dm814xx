require ti-edma3lld.inc

PV = "02_11_05_02"

SRC_URI[edma3lldbin.md5sum] = "fd939d86b078392a23f71d0b5763f9ce"
SRC_URI[edma3lldbin.sha256sum] = "5f394897c6f8c8b87f992b2a3c8ddc53417d30db0bf8059f8c8f3d08c56bbbe6"

SRC_URI += "file://0001-edma_cleanall_fix.patch \
            file://0001-edma_drv_m3_lib_for_vpss_overlay.patch \
            file://sdk.mk \
"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch

