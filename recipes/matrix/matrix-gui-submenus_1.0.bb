DESCRIPTION = "Submenu packages for Matrix GUI v2"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrixguiv2apps/"
LICENSE = "CC-BY-SA"
PRIORITY = "optional"

PR = "r5"

require matrix-gui-paths.inc

# These packages make submenus in matrix and are not architecture specific
PACKAGE_ARCH = "all"

# List of submenu tarballs to use.  Each tarball contains a desktop file
# and PNG graphic file for the submenu.
SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/589/5037/arm.tar.gz;name=armtarball \
           https://gforge.ti.com/gf/download/frsrelease/588/5038/3d.tar.gz;name=3dtarball \
           https://gforge.ti.com/gf/download/frsrelease/591/5039/cryptos.tar.gz;name=cryptostarball \
           https://gforge.ti.com/gf/download/frsrelease/675/5098/display_1.1.tar.gz;name=displaytarball \
           https://gforge.ti.com/gf/download/frsrelease/680/5103/ethernet_1.3.tar.gz;name=ethernettarball \
           https://gforge.ti.com/gf/download/frsrelease/594/5042/multimedia.tar.gz;name=multimediatarball \
           https://gforge.ti.com/gf/download/frsrelease/595/5043/power.tar.gz;name=powertarball \
           https://gforge.ti.com/gf/download/frsrelease/596/5044/pru.tar.gz;name=prutarball \
           https://gforge.ti.com/gf/download/frsrelease/597/5045/qt4.tar.gz;name=qt4tarball \
           https://gforge.ti.com/gf/download/frsrelease/682/5105/settings_1.1.tar.gz;name=settingstarball \
           https://gforge.ti.com/gf/download/frsrelease/681/5104/usb_1.1.tar.gz;name=usbtarball \
           https://gforge.ti.com/gf/download/frsrelease/679/5102/wifi_1.1.tar.gz;name=wifitarball \
           https://gforge.ti.com/gf/download/frsrelease/662/5068/oprofile.tar.gz;name=oprofiletarball \
"

S = ${WORKDIR}

# List of submenus to build packages for
SUBMENUS = "arm 3d cryptos display ethernet multimedia power pru qt4 settings usb wifi oprofile"

do_install(){
	install -d ${D}${MATRIX_APP_DIR}

    for x in ${SUBMENUS}
    do
        cp -rf ${S}/$x ${D}${MATRIX_APP_DIR}/
    done
}

PACKAGES += "${PN}-arm ${PN}-3d ${PN}-cryptos ${PN}-display ${PN}-ethernet ${PN}-multimedia ${PN}-power ${PN}-pru ${PN}-qt4 ${PN}-settings ${PN}-usb ${PN}-wifi ${PN}-oprofile"

# Add the files for each submenu package
FILES_${PN}-arm = "${MATRIX_APP_DIR}/arm/*"
FILES_${PN}-3d = "${MATRIX_APP_DIR}/3d/*"
FILES_${PN}-cryptos = "${MATRIX_APP_DIR}/cryptos/*"
FILES_${PN}-display = "${MATRIX_APP_DIR}/display/*"
FILES_${PN}-ethernet = "${MATRIX_APP_DIR}/ethernet/*"
FILES_${PN}-multimedia = "${MATRIX_APP_DIR}/multimedia/*"
FILES_${PN}-power = "${MATRIX_APP_DIR}/power/*"
FILES_${PN}-pru = "${MATRIX_APP_DIR}/pru/*"
FILES_${PN}-qt4 = "${MATRIX_APP_DIR}/qt4/*"
FILES_${PN}-settings = "${MATRIX_APP_DIR}/settings/*"
FILES_${PN}-usb = "${MATRIX_APP_DIR}/usb/*"
FILES_${PN}-wifi = "${MATRIX_APP_DIR}/wifi/*"
FILES_${PN}-oprofile = "${MATRIX_APP_DIR}/oprofile/*"

# checksums for the submenu tarballs
SRC_URI[armtarball.md5sum] = "ed7d73441e0d85e84aa6df06d86e9d72"
SRC_URI[armtarball.sha256um] = "99f9ccba0df1d8f1bfb78cf90eec8e64e961651ba6079e9c4bb21f8243d33ece"

SRC_URI[3dtarball.md5sum] = "8aa161e1836e0efcf3db657f94137a34"
SRC_URI[3dtarball.sha256um] = "b522065d288e49f50cbf1ee2c9deb76683161d82eb0429635bc66c4f26f235f1"

SRC_URI[cryptostarball.md5sum] = "d1fbc3b575775557e3e150818d86373a"
SRC_URI[cryptostarball.sha256um] = "f4c1645bf858dc157d4efae3ca66e437310294bfd564bdeb89c6609ae65cadf9"

SRC_URI[displaytarball.md5sum] = "44ad03e0b25a69886865625c14a27373"
SRC_URI[displaytarball.sha256sum] = "568e747e5fa9664a7dad6e1a497a2e8d38d813b0ea982204f9566001d7f1fd3f"

SRC_URI[ethernettarball.md5sum] = "3e8e9e38bf6a755ea4f839f45654d29f"
SRC_URI[ethernettarball.sha256sum] = "af78982d3c1bd745eef50728b00815eb602c642b99ade83f03e4f1b51e24c188"

SRC_URI[multimediatarball.md5sum] = "8ef64f7b252633d6b2f74561f047f874"
SRC_URI[multimediatarball.sha256sum] = "e093c06b7bb40e51ab42ef1d896008d3ba3463629532bd0dcc1417368b73054b"

SRC_URI[powertarball.md5sum] = "26ef7b4e83386a5a16a08fdb04e20047"
SRC_URI[powertarball.sha256sum] = "87b3381cffeb2951ad7ffdff8df2661ad845303ffc850fa10caf8a9b35c09da5"

SRC_URI[prutarball.md5sum] = "6f28ee2bc5de2bb9db035e4736f6e205"
SRC_URI[prutarball.sha256sum] = "872fc89905fab81007274aa86cd43d822c71cf799bf7de92ac9e0394ced158d1"

SRC_URI[qt4tarball.md5sum] = "f724ca0c58de81477583294078f8666e"
SRC_URI[qt4tarball.sha256sum] = "75758cf90981c73603e728fd835f91b250978fb8141634f16b6e7b4ec62c08b1"

SRC_URI[settingstarball.md5sum] = "299eddc1ad3ad35a76788b75f9bff3e7"
SRC_URI[settingstarball.sha256sum] = "2af546d14bf2a45a78cce2c571ac028f1e586a5118d79cbed510f0f8cf0b7a4d"

SRC_URI[usbtarball.md5sum] = "11c20f006c7944a1c40424392b9c13e7"
SRC_URI[usbtarball.sha256sum] = "38234d15e5f875bfce52a2f3ca6653dd8f33ba2ab4a25abd5cbd6ecb700f85cb"

SRC_URI[wifitarball.md5sum] = "eedc56d135e1a35b83a5643284cf9c3d"
SRC_URI[wifitarball.sha256sum] = "947e0288bc0c2977166bc72b7333242a2405aea1832eaacbba1dd00045175d09"

SRC_URI[oprofiletarball.md5sum] = "bc4f93018910817bab79eef4e0e20564"
SRC_URI[oprofiletarball.sha256sum] = "67803d1bee995496d4b632aaf2dc731d58f6826cc45b79f5545d1a8ffb3d2583"
