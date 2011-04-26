require pinmux-utility.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/540/4568/PinMuxUtility_v1_01_04_00.zip;name=base \
    "

SRC_URI[base.md5sum] = "ef748e5af7ea5ecb30fb186ba9903cc8"
SRC_URI[base.sha256sum] = "7cec2fe0209d6c5a93f4a98cc00414ca0d96f00be24ba19546f02517ee57bcbf"

# Currently for omapl138 devices we are using a different pinmux utility
# program.  This requires a separate SRC_URI as well as a different
# do_install.
SRC_URI_omapl138 = "\
    https://gforge.ti.com/gf/download/frsrelease/461/4210/Pin_Setup_AM18xx_01_00_1076_03.zip;name=omapl138 \
    "

do_install_omapl138() {
    install -d ${D}/${installdir}
    cp -rf ${S}/bin ${D}/${installdir}/
    cp -rf ${S}/configurations ${D}/${installdir}/
}

SRC_URI[omapl138.md5sum] = "5a4eb834bde44c662aaf669882adbfe6"
SRC_URI[omapl138.sha256sum] = "6701ca0e91761b9eb80b0097fbb2e2ce2cd8e0bcdc0bd18c34cd0221d9d450d0"
