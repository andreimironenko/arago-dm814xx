require ti-framework-components.inc

PV = "3_21_00_21"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball"

SRC_URI[fctarball.md5sum] = "01ab8e3bfa8490570cb936c257dfdf76"
SRC_URI[fctarball.sha256sum] = "44f164f021614cd805abeea85ddc9bc96323ea3a2ee7b197bb42ed982d988141"

