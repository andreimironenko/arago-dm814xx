require pinmux-utility.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/663/5070/PinMuxUtility_v2_02_00_00.zip;name=base \
    "

SRC_URI[base.md5sum] = "557063259a44677dcc32e3040d760934"
SRC_URI[base.sha256sum] = "452219762db3ab3f1e4ad4bfd2ba52b55e4523aaf5430f6e26e4fea6a6441092"

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
