require pinmux-utility.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/462/4219/PinMuxUtility_v1_01_03_00.zip;name=base \
    "

SRC_URI[base.md5sum] = "4ef882c2744c668b8a8c3cee74627e68"
SRC_URI[base.sha256sum] = "9c9e01433b6e058b6183dea1e645d1e6905573ebda109f0415f5a28c59a27272"

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
    cp -rf ${S}/src ${D}/${installdir}/
}

SRC_URI[omapl138.md5sum] = "5a4eb834bde44c662aaf669882adbfe6"
SRC_URI[omapl138.sha256sum] = "6701ca0e91761b9eb80b0097fbb2e2ce2cd8e0bcdc0bd18c34cd0221d9d450d0"
