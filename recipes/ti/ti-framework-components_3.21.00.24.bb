require ti-framework-components.inc

PV = "3_21_00_24"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball"

SRC_URI[fctarball.md5sum] = "72ca0f79f4354587e3794b50de233527"
SRC_URI[fctarball.sha256sum] = "b4157ae69c96eb174c852db737a87601ed92bc3e453177403b107d1c63b434a2"

do_compile() {
    :
}
