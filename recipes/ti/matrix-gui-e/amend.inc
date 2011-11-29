SRCREV = "316"
PR_append = "-arago2"

SRC_URI += "file://0001-ti814x-Add-GStreamer-Decode-Demo-to-Multimedia-Menu.patch;striplevel=2 \
            file://0002-ti816x-Add-GStreamer-Decode-and-Display-Demo-to-Mult.patch;striplevel=2 \
"

INITSCRIPT_PARAMS = "defaults 99 05"
