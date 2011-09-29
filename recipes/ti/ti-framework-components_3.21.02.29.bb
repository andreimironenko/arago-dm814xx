require ti-framework-components.inc

PV = "3_21_02_29"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball \
           file://0001-Added-sdk-make-install-file-to-fc.patch \
"

SRC_URI[fctarball.md5sum] = "1bac92003aae9b74d55399f646fcf2e2"
SRC_URI[fctarball.sha256sum] = "a5348d20065741a82c59b79f0bd881c4f933e61882179997fc88fe53b96f8c7a"

do_compile() {
    :
}
