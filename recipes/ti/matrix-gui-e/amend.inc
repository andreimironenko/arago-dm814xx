SRCREV = "295"
PR_append = "-arago1"

SRC_URI += "file://0001-ti814x-Add-Description-Pages-for-DSP-Menu.patch;striplevel=2 \
            file://0002-ti81xx-Remove-touchscreen-calibration.patch;striplevel=2 \
            file://0003-ti814x-Set-Video-Demos-to-1080p30.patch;striplevel=2 \
            file://0004-ti814x-Add-Video-Demos.patch;striplevel=2 \
            file://0005-ti816x-Update-runOMTB-not-to-pause.patch;striplevel=2 \
            file://0006-ti814x-Fix-the-text-for-getting-started.patch;striplevel=2 \
            file://0007-ti814x-Correct-CMEM-in-DSP-Benchmarks.patch;striplevel=2 \
            file://0008-ti814x-Fix-Calibration.patch;striplevel=2 \
            file://0009-ti81xx-Fix-CMEM-location-for-DSP-Benchmarks.patch;striplevel=2 \
            file://0010-ti816x-Add-Decode-Display-and-Capture-Encode-OMX-IL-.patch;striplevel=2 \
            file://0011-ti814x-Change-start-stop-daemon-to-use-matrixgui.patch;striplevel=2 \
            file://0012-ti816x-Change-start-stop-daemon-to-use-matrixgui.patch;striplevel=2 \
            file://0013-ti814x-Remove-references-to-parts-other-than-DM814x.patch;striplevel=2 \
            file://0014-ti816x-Remove-references-to-parts-other-than-dm816x.patch;striplevel=2 \
            file://0015-ti814x-Fix-Incorrect-header-in-Decode-Display-Descri.patch;striplevel=2 \
            file://0016-ti816x-Add-description-pages-for-OMX-IL-Client-demos.patch;striplevel=2 \
            file://0017-ti816x-Update-runOMTB-for-new-version.patch;striplevel=2 \
"

INITSCRIPT_PARAMS = "defaults 99 05"
