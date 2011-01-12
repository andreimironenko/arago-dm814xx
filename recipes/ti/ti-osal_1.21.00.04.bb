require ti-osal.inc

PV = "1_21_00_04"
PVextra = "_eng"

SRC_URI[osaltarball.md5sum] = "513ab57c101f3b077d3ce131aeb4f7bb"
SRC_URI[osaltarball.sha256sum] = "70733bedaa291281546a0f424f74dba0a17c420cb0f3d001863370318722e7ca"

# Internal URL as this is an engineering release.
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/OSAL/${PV}/exports/osal_${PV}${PVextra}.tar.gz;name=osaltarball"

