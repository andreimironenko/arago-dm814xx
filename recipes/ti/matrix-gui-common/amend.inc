SRCREV = "295"
PR_append = "-arago1"

SRC_URI += "file://0001-ti814x-Add-Description-Pages-for-DSP-Menu.patch;striplevel=2 \
            file://0002-ti81xx-Remove-touchscreen-calibration.patch;striplevel=2 \
            file://0003-ti814x-Add-Video-Demos.patch;striplevel=2 \
            file://0004-ti816x-Update-runOMTB-not-to-pause.patch;striplevel=2 \
            file://0005-ti814x-Fix-the-text-for-getting-started.patch;striplevel=2 \
            file://0006-ti814x-Correct-CMEM-in-DSP-Benchmarks.patch;striplevel=2 \
            file://top_ti814x_evm.png \
"

do_copyimage() {
    cp ${WORKDIR}/top_ti814x_evm.png ${S}/images
}
addtask copyimage after do_unpack before do_patch
