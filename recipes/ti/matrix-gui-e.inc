DESCRIPTION = "Matrix GUI for Qt Embedded"
HOMEPAGE = "https://gforge.ti.com/gf/project/matrix_gui/"
LICENSE = "BSD"
SECTION = "multimedia"
PRIORITY = "optional"

INC_PR = "r21"

COMPATIBLE_MACHINE = "(dm365|omapl138|omap3|ti816x|ti814x)"

PLATFORM_dm365 = "dm365"
PLATFORM_dm368-evm = "dm365"
PLATFORM_omapl138 = "omapl138"
PLATFORM_omap3evm = "omap3530"
PLATFORM_dm37x-evm = "dm3730"
PLATFORM_am37x-evm = "am3715"
PLATFORM_beagleboard = "am3715"
PLATFORM_ti816x = "ti816x"
PLATFORM_ti814x = "ti814x"
PLATFORM_am180x-evm = "am180x"
PLATFORM_am181x-evm = "am181x"
PLATFORM_am3517-evm = "am3517"

SRC_URI = "svn://gforge.ti.com/svn/matrix_gui/;module=trunk;proto=https;user=anonymous;pswd='' \
           file://TI816X_Enable_Multi_Display.patch \
           file://TI81xx_Fix_dspbenchmarks_memory_map.patch \
           file://TI816X_Adjust_DSP_Menu.patch \
           file://video-conference-icon.png \
           file://menu_main.html \
           file://runOMTB \
           file://menu_multimedia.html \
           file://target.html \
           file://top_ti816x_evm.png \
"

S = "${WORKDIR}/trunk"

INITSCRIPT_NAME = "matrix-gui-e"
INITSCRIPT_PARAMS = "defaults 99"
#INITSCRIPT_PARAMS = "start 99 3 . stop 99 3 ."

CXXFLAGS_omapl138_append = " -DPlatform_omapl138 "
CXXFLAGS_dm365_append = " -DPlatform_dm365 "
PACKAGE_ARCH = ${MACHINE_ARCH}

inherit qt4e update-rc.d

do_prepsources() {
    cp ${WORKDIR}/menu_main.html ${S}/ti816x/html
    cp ${WORKDIR}/runOMTB ${S}/ti816x/bin
    cp ${WORKDIR}/menu_multimedia.html ${S}/ti816x/html
    cp ${WORKDIR}/target.html ${S}/ti816x/html
    cp ${WORKDIR}/video-conference-icon.png ${S}/images
    cp ${WORKDIR}/top_ti816x_evm.png ${S}/images
}

addtask prepsources after do_patch before do_configure

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/matrix_gui ${D}/${bindir}/matrix_guiE
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 0755 ${S}/${PLATFORM}/etc/init ${D}${sysconfdir}/init.d/matrix-gui-e

}

RRECOMMENDS_${PN} = "qt4-embedded-plugin-mousedriver-tslib qt4-embedded-fonts"
RDEPENDS_${PN} += "matrix-gui-common"
