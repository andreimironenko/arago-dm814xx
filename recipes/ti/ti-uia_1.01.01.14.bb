require ti-uia.inc

PV = "1_01_01_14"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia \
"

SRC_URI[uia.md5sum] = "b069db2510eb45cc9ccb2bc1b42c7b71"
SRC_URI[uia.sha256sum] = "155589e597d98b4ace2a4a7c51325a3dd1eaef16efbe4b6948f993bd1e665795"

INSANE_SKIP_${PN} = True