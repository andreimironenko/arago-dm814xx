require ti-framework-components.inc

PV = "3_21_03_33"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball \
"

SRC_URI[fctarball.md5sum] = "60a49d9f11d73668d9dc098a3a6923fe"
SRC_URI[fctarball.sha256sum] = "6384532b7a0880a890dc41f58673a159c0071aa889ec805da75bf7079eaf3938"

do_compile() {
    :
}
