require ti-uia.inc

PV = "1_00_01_05"
PVExtra = "_eng"

# This is an internal engineering release. Do not use this release by default.
DEFAULT_PREFERENCE = "-1"
HTTP_PROXY_IGNORE = "www.sanb.design.ti.com"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia"

SRC_URI[uia.md5sum] = "0520a398c6de44134f2a4433c544f40b"
SRC_URI[uia.sha256sum] = "36085abd637288a8259cb56a8313cd6f5b47c61fed4d45dd3a0c18df4f8f5734"

INSANE_SKIP_${PN} = True
