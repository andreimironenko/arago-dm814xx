DESCRIPTION = "Task to install sources for additional utilities/demos for SDKs"
PR = "r5"
LICENSE = "MIT"

inherit task
PACKAGE_ARCH = "${MACHINE_ARCH}"

UTILS = ""

UTILS_COMMON = " \
    matrix-tui-src \
    am-sysinfo-src \
    am-benchmarks-src \
    "

UTILS_omap3 = " \
  ${UTILS_COMMON}  \
 "

UTILS_ti816x = " \ 
  ${UTILS_COMMON} \
  "

UTILS_omapl138 = " \
  ${UTILS_COMMON} \
  "

RDEPENDS_${PN} = "\
    ${UTILS} \
    "
