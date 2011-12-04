require ti-framework-components.inc

PV = "3_21_03_34"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball \
"

SRC_URI[fctarball.md5sum] = "849a3d27bd62ff1e5b45274c332ca2a3"
SRC_URI[fctarball.sha256sum] = "2962d0b94a2875b08d19dc7f85fc390e79fa6e390442d9adf97c7b40a6cb8ccf"

do_compile() {
    :
}
