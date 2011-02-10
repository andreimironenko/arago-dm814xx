require ti-framework-components.inc

PV = "3_21_00_13"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball"

SRC_URI[fctarball.md5sum] = "183496f38c1d65ef5f724d87681dee86"
SRC_URI[fctarball.sha256sum] = "67aa6731a899cdd09ca265c58205a80d4e17c4808c527a0014fe49b148df30bd"

