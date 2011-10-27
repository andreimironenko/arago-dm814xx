require ti-uia.inc

PV = "1_00_03_25"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia \
           file://0001-Added-sdk-make-install-file-for-UIA.patch \
"

SRC_URI[uia.md5sum] = "042d7d4e891d0fe365ad49503c0b7c01"
SRC_URI[uia.sha256sum] = "075324d4e9d2ee57e6c809927f2528a3b114e5ce9688c617d8aa82bcf1b4c89a"

INSANE_SKIP_${PN} = True
