DESCRIPTION = "Package contains DSP Devkit Documentation"
LICENSE = "TI"

SRC_URI = "\
	file://environment-setup \
	file://README-dsp-devkit.txt \
"

PR = "r1"

do_install() {
    install -d ${D}/dsp-devkit
    install ${WORKDIR}/environment-setup ${D}/dsp-devkit
    install ${WORKDIR}/README-dsp-devkit.txt ${D}/dsp-devkit
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "dsp-devkit/*"
