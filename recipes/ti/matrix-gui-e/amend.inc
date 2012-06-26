SRCREV = "316"
PR_append = "-arago2"

SRC_URI += "file://0001-ti814x-Add-GStreamer-Decode-Demo-to-Multimedia-Menu.patch;striplevel=2 \
            file://0002-ti816x-Add-GStreamer-Decode-and-Display-Demo-to-Mult.patch;striplevel=2 \
            file://j5eco.init \
"

do_copyimage() {
    mkdir -p ${S}/ti811x/html ${S}/ti811x/bin ${S}/ti811x/etc
    cp ${WORKDIR}/j5eco.init ${S}/ti811x/etc/init
}
addtask copyimage after do_unpack before do_patch

INITSCRIPT_PARAMS = "defaults 99 05"
