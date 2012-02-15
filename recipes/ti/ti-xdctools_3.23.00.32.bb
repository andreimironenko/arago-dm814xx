require ti-xdctools.inc

PV = "3_23_00_32"

DEFAULT_PREFERENCE = "-1"

SRC_URI[xdcbin.md5sum] = "263aa11795670265551d36a8c77ad27d"
SRC_URI[xdcbin.sha256sum] = "880b4e263850f2a3c0ea7352c5b6e0a86692361e26634c3b70dddc42c71231a5"

S = "${WORKDIR}/ti/xdctools_${PV}"
