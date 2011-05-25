DESCRIPTION = "Qt embedded interface for choosing which configured network \
to connect to. It also provides a method for browsing 802.11 SSID scan \
results, an event history log of messages generated by wpa_supplicant, and \
a method to add or edit wpa_supplicant networks."
SECTION = "network"
LICENSE = "GPL BSD"
HOMEPAGE = "http://hostap.epitest.fi/wpa_supplicant/"
RDEPENDS_${PN} = "wpa-supplicant"
RRECOMMENDS_${PN} = "glibc-gconv-utf-16"

SRCREV = "b8fb017272ed4794339978c9fbc0e74571a44728"
PR = "r0"
PV = "0.7.3+0.8.0-rc"
PR_append = "+gitr${SRCPV}"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "git://w1.fi/srv/git/hostap.git;protocol=git"

S = "${WORKDIR}/git/wpa_supplicant/wpa_gui-qt4"

inherit qt4e
ARM_INSTRUCTION_SET = "arm"

do_install () {
       install -d ${D}${bindir}
       install -d ${D}${datadir}/wpa_gui
       install -d ${D}${datadir}/applications
       install -m 755 wpa_gui ${D}/${bindir}/wpa_gui-e
}


