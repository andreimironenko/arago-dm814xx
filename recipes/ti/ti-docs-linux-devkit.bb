DESCRIPTION = "Package contains Linux Devkit Documentation"
LICENSE = "TI"

SRC_URI = "\
	file://README-linux-devkit.txt \
"

PR = "r1"

do_install() {
    install -d ${D}/linux-devkit
    install ${WORKDIR}/README-linux-devkit.txt ${D}/linux-devkit
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "linux-devkit/*"
