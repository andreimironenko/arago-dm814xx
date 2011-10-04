require ti-framework-components.inc

PV = "3_21_02_30"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball \
"

SRC_URI[fctarball.md5sum] = "7a3787c0d5340d0043100da1dc387256"
SRC_URI[fctarball.sha256sum] = "470c2ef95370361b18872512587a92a63142f7ecbef4602095eb81d0737a9085"
