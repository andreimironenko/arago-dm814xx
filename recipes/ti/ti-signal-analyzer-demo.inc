DESCRIPTION = "EZSDK Integra Signal Analyzer Demo application for C6A816x processor"
SECTION = "devel"
LICENSE = "BSD"
 
require ti-paths.inc
require ti-staging.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r2"

S  = "${WORKDIR}/signal-analyzer-demo_${PV}"

SRC_URI = "http://install.source.dir.local/signal-analyzer-demo_${PV}.tar.gz;name=sademo"

PROVIDES += "ti-integra-demos"

# Currently unable to build out of Arago. Till a solution is found will use
# prebuilt binary package.

do_compile() {
    :
}

do_install() {
    install -d ${D}/${installdir}/ti-integra-demos
    cp -pPrf ${S}/sigan-gui-r/sigan_demo_app ${D}/${installdir}/ti-integra-demos
    cp -pPrf ${S}/scripts/bin/runSADemo ${D}/${installdir}/ti-integra-demos
    cp -pPrf ${S}/scripts/bin/fft_demo_8khz.pcm ${D}/${installdir}/ti-integra-demos
}

PACKAGES += "ti-integra-demos"
FILES_ti-integra-demos = "${installdir}/ti-integra-demos/*"
INSANE_SKIP_ti-integra-demos = True

pkg_postinst_ti-integra-demos () {
	ln -sf ${installdir}/c6accel-apps/ti816x.${DSPSUFFIX} ${installdir}/ti-integra-demos/
	ln -sf ${installdir}/c6accel-apps/test_files ${installdir}/ti-integra-demos/
}
