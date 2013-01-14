DESCRIPTION = "Package contains system wide loadmodule for DVSDK"
LICENSE = "TI"

DEPENDS = "update-rc.d"

SRC_URI = "\
          file://loadmodule-rc \
"
PR = "r3"

S = "${WORKDIR}"

do_compile () {
 :
}

do_stage () {
}

do_install () {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755  ${WORKDIR}/loadmodule-rc ${D}${sysconfdir}/init.d/loadmodule-rc
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INITSCRIPT_NAME = "loadmodule-rc"
INITSCRIPT_PARAMS = "defaults 99"
