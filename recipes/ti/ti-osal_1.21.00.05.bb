require ti-osal.inc

PV = "1_21_00_05"
PVExtra = "_eng"

# Internal URL as this is an engineering release.
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/OSAL/${PV}/exports/osal_${PV}${PVExtra}.tar.gz;name=osaltarball"

SRC_URI[osaltarball.md5sum] = "ce1bdbb9fb0a743e7e291dc8e5e69624"
SRC_URI[osaltarball.sha256sum] = "a5a6bd27f26f2aa2ad98bc2b8fb62daf1334eaeb10aaa658746b392e13194a57"

