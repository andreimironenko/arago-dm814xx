require ti-framework-components.inc

PV = "3_21_01_26"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball \
           file://0001-Added-sdk-make-install-file-to-fc.patch \
"

SRC_URI[fctarball.md5sum] = "437a96a844f14f6600e53fe051c2f654"
SRC_URI[fctarball.sha256sum] = "057f6b32f03620d0a3d0e63995ae30b37109d4a95d8c40564e03a2c86a7bcca2"

do_compile() {
    :
}
