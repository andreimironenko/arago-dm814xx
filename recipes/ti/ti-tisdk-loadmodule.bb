DESCRIPTION = "Package contains system wide loadmodule for DVSDK"
LICENSE = "TI"

inherit update-rc.d

SRC_URI = "\
 loadmodule-rc \
"
PR = "r1"

do_compile () {
:
}

do_install () {
    cp ${WORKDIR}/loadmodule-rc
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755  ${WORKDIR}/loadmodule-rc ${D}${sysconfdir}/init.d/loadmodule-rc
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INITSCRIPT_NAME = "loadmodule-rc"
INITSCRIPT_PARAMS = "defaults 99"

