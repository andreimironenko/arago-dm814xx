DESCRIPTION = "Script to parse ip address during boot and make it available to the host system using shared partitions"

PR = "r1"

LICENSE = "MIT"

COMPATIBLE_MACHINE = "ti33x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://init \
          "

INITSCRIPT_NAME = "parse-ip"
INITSCRIPT_PARAMS = "defaults 98"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/parse-ip
}

FILES_${PN} = "${sysconfdir}"
