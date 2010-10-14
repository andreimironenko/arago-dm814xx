require ti-osal.inc

PV = "1_21_00_02"
PVextra = "_eng"

SRC_URI[osaltarball.md5sum] = "6443a953624e08ec13082611db9c82c3"
SRC_URI[osaltarball.sha256sum] = "4458a0cb2da2c65e60f82f9f36beff31d56072598fa7d27d72fcb81d1eaeac26"

# Internal URL as this is an engineering release.
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/OSAL/${PV}/exports/osal_${PV}${PVextra}.tar.gz;name=osaltarball"

