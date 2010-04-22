DESCRIPTION = "Task to provide factory default packages"
ALLOW_EMPTY = "1"
PR = "r1"

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
    ti-dvsdk-demos \
    ti-data \
    ti-dvsdk-demos-rc \
    "
