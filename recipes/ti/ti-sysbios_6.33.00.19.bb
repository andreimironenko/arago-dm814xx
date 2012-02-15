require ti-sysbios.inc

PV = "6_33_00_19"
PVExtra = ""

DEFAULT_PREFERENCE = "-1"

SRC_URI[sysbiosbin.md5sum] = "e1a2f28a8b3087896bbd986696e1a908"
SRC_URI[sysbiosbin.sha256sum] = "b7e096662f5d928eb818bbb14474deb1e3e038fea34b516b248de637835121ec"

S = "${WORKDIR}/ti/bios_${PV}${PVExtra}"
