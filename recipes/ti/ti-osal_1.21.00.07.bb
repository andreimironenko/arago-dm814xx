require ti-osal.inc

PV = "1_21_00_07"
PVExtra = ""

# Internal URL as this is an engineering release.
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/OSAL/${PV}/exports/osal_${PV}${PVExtra}.tar.gz;name=osaltarball"

SRC_URI[osaltarball.md5sum] = "d18f7cbad1727d190cb82ff6935f8fce"
SRC_URI[osaltarball.sha256sum] = "4d7574583ff5af9182f7a82147de013d0f59887b5ae1e2d7ff362bd73a5031de"

