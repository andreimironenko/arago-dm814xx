SRCREV = "316"
PR_append = "-arago2"

SRC_URI += "file://0001-ti814x-Add-GStreamer-Decode-Demo-to-Multimedia-Menu.patch;striplevel=2 \
            file://0002-ti816x-Add-GStreamer-Decode-and-Display-Demo-to-Mult.patch;striplevel=2 \
            file://desc_gst_1080p_decode_display.html \
            file://desc_gst_lcd_decode_display.html \
            file://desc_aac_lc_decode_playback.html \
            file://desc_aac_lc_encode_store2file.html \
            file://desc_gst_video_loopback.html \
            file://desc_mp3_decode_playback.html \
            file://menu_main.html \
            file://rpe_audio.html \
            file://target.html \
            file://runGSTDecodeDisplayHDMI \
            file://runGSTDecodeDisplayLCD \
            file://runAACLC_decode \
            file://runAACLC_encode \
            file://runGSTVideoLoopback \
            file://runMP3_decode \
            file://aac-decode.png \
            file://aac-encode.png \
            file://mp3-decode.png \
            file://top_ti811x_evm.png \
            file://video-loopback-demo.png \
            file://j5eco.init \
            file://aacdectest.cfg \
            file://aacenctest.cfg \
            file://mp3dectest.cfg \
"

do_copyimage() {
    mkdir -p ${S}/ti811x/html ${S}/ti811x/bin ${S}/ti811x/etc
    cp ${WORKDIR}/desc_gst_1080p_decode_display.html ${S}/ti816x/html
    cp ${WORKDIR}/desc_gst_lcd_decode_display.html ${S}/ti814x/html
    cp ${WORKDIR}/runGSTDecodeDisplayHDMI ${S}/ti816x/bin
    cp ${WORKDIR}/runGSTDecodeDisplayLCD ${S}/ti814x/bin
    cp -r ${S}/ti814x/bin/* ${S}/ti811x/bin
    cp -r ${S}/ti814x/html/* ${S}/ti811x/html
    cp -r ${S}/ti814x/etc/* ${S}/ti811x/etc
    cp ${WORKDIR}/runAACLC_decode ${S}/ti811x/bin
    cp ${WORKDIR}/runAACLC_encode ${S}/ti811x/bin
    cp ${WORKDIR}/runGSTVideoLoopback ${S}/ti811x/bin
    cp ${WORKDIR}/runMP3_decode ${S}/ti811x/bin
    cp ${WORKDIR}/*.png ${S}/images
    cp ${WORKDIR}/j5eco.init ${S}/ti811x/etc/init
    cp ${WORKDIR}/desc_aac_lc_decode_playback.html ${S}/ti811x/html
    cp ${WORKDIR}/desc_aac_lc_encode_store2file.html ${S}/ti811x/html
    cp ${WORKDIR}/desc_gst_video_loopback.html ${S}/ti811x/html
    cp ${WORKDIR}/desc_mp3_decode_playback.html ${S}/ti811x/html
    cp ${WORKDIR}/menu_main.html ${S}/ti811x/html
    cp ${WORKDIR}/rpe_audio.html ${S}/ti811x/html
    cp ${WORKDIR}/target.html ${S}/ti811x/html
    cp ${WORKDIR}/aacdectest.cfg ${S}/ti811x/html
    cp ${WORKDIR}/aacenctest.cfg ${S}/ti811x/html
    cp ${WORKDIR}/mp3dectest.cfg ${S}/ti811x/html
}
addtask copyimage after do_unpack before do_patch
