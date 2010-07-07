require ti-dvsdk-demos.inc

SRC_URI = "http://tigt_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dvsdk_demos_${PV}.tar.gz;name=dvsdkdemostarball"

S = "${WORKDIR}/dvsdk_demos_${PV}"

PV = "3_10_00_11"
PR = "${INC_PR}.0"

SRC_URI[dvsdkdemostarball.md5sum] = "7e92c559c9240fadc9efeb12eb99d85e"
SRC_URI[dvsdkdemostarball.sha256sum] = "6fe7d93db4239a439758829c1fecfe31546733f4895acc5019bb834d77166c00"
