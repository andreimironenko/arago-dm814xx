DESCRIPTION = "Qt embedded interface for choosing which configured network \
to connect to. It also provides a method for browsing 802.11 SSID scan \
results, an event history log of messages generated by wpa_supplicant, and \
a method to add or edit wpa_supplicant networks."
SECTION = "network"
LICENSE = "GPL BSD"
HOMEPAGE = "http://hostap.epitest.fi/wpa_supplicant/"
RDEPENDS_${PN} = "wpa-supplicant"

SRCREV = "653c4893be17d12ef7bc6e941b765c92c1e4a161"
PR = "r4"
PV = "0.7.3+0.8.0-rc"
PR_append = "+gitr${SRCPV}"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "git://w1.fi/srv/git/hostap.git;protocol=git \
          file://0001-wpa_gui-make-the-networkconfig-dialog-scrollable.patch \
          file://0002-wpa-gui-e-fix-display-issue-on-small-screen.patch \
          file://0003-wpa-gui-trigger-udhcpc-on-connection-to-new-access-p.patch \
          file://connection_script.sh"

S = "${WORKDIR}/git/wpa_supplicant/wpa_gui-qt4"

inherit qt4e
ARM_INSTRUCTION_SET = "arm"

do_install () {
       install -d ${D}${bindir}
       install -d ${D}${datadir}/wpa_gui
       install -d ${D}${datadir}/applications
       install -m 755 wpa_gui ${D}/${bindir}/wpa_gui-e
       install -m 0755 ${WORKDIR}/connection_script.sh ${D}${datadir}/wpa_gui
}

FILES_${PN} += "${datadir}/wpa_gui/connection_script.sh"
