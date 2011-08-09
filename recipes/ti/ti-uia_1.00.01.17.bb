require ti-uia.inc

PV = "1_00_01_17"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/UIA/${PV}/exports/uia_${PV}${PVExtra}.zip;name=uia \
           file://0001-Settings.xs-Add-TI814x-Device-support.patch \
           file://0001-UIA-Increase-buffer-size.patch \
"

SRC_URI[uia.md5sum] = "5241814177413da1de4fe55aba721955"
SRC_URI[uia.sha256sum] = "51a8095b22e3d19ba0b0737c0b1e9f6621c55a84ba226457decc8c83e581704e"

INSANE_SKIP_${PN} = True
