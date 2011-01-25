require ti-framework-components.inc

PV = "3_21_00_09"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Framework_Components/${PV}/exports/framework_components_${PV}${PVExtra},lite.tar.gz;name=fctarball"

SRC_URI[fctarball.md5sum] = "b68e1c81230b1f5a3bd6058e5a5b0106"
SRC_URI[fctarball.sha256sum] = "32d45eed1f961e467797c2913257f46fd48935346bd93fc6574307e1957aeb40"
