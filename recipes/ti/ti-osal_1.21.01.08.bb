require ti-osal.inc

PV = "1_21_01_08"
PVExtra = ""

# Internal URL as this is an engineering release.
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/OSAL/${PV}/exports/osal_${PV}${PVExtra}.tar.gz;name=osaltarball"

SRC_URI[osaltarball.md5sum] = "4c11bbb1f2bf093fededda69c8b39062"
SRC_URI[osaltarball.sha256sum] = "59008cf7cd4eff6cf31fa27538d3926d48413682151f5f47b678871b3da60dd3"

SRC_URI += "file://0001-Added-sdk-make-install-file-to-osal.patch \
            file://0001-linuxdist-Set-a-default-scheduling-policy-before-pri.patch \
"
