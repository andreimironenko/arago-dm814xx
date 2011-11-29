SRCREV = "316"
PR_append = "-arago2"

SRC_URI += "file://0001-ti814x-Add-GStreamer-Decode-Demo-to-Multimedia-Menu.patch;striplevel=2 \
            file://0002-ti816x-Add-GStreamer-Decode-and-Display-Demo-to-Mult.patch;striplevel=2 \
            file://desc_gst_1080p_decode_display.html \
            file://desc_gst_lcd_decode_display.html \
            file://runGSTDecodeDisplayHDMI \
            file://runGSTDecodeDisplayLCD \
"

do_copyimage() {
    cp ${WORKDIR}/desc_gst_1080p_decode_display.html ${S}/ti816x/html
    cp ${WORKDIR}/desc_gst_lcd_decode_display.html ${S}/ti814x/html
    cp ${WORKDIR}/runGSTDecodeDisplayHDMI ${S}/ti816x/bin
    cp ${WORKDIR}/runGSTDecodeDisplayLCD ${S}/ti814x/bin
}
addtask copyimage after do_unpack before do_patch
