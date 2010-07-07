
require ti-dvsdk-demos.inc

SRC_URI = "http://tigt_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dvsdk_demos_${PV}.tar.gz;name=dvsdkdemostarball"

S = "${WORKDIR}/dvsdk_demos_${PV}"

PV = "3_10_00_15"
PR = "${INC_PR}.1"

SRC_URI_append_dm365 = " file://dm365-demos-with-new-data-paths.patch;patch=1 \
    file://changed_insmod_to_modprobe_in_loadmodules_3.10.00.14.patch;patch=1 \
    file://remove-loadmodules_hd.sh-from-make-install_3.10.00.14.patch;patch=1"

SRC_URI[dvsdkdemostarball.md5sum] = "0d201ecbb3e933bdb93a71fe4c270a4d"
SRC_URI[dvsdkdemostarball.sha256sum] = "65e6392be21963ac07361f8be8555fdda92c300d08cf72ca73def5ea8dde8353"
