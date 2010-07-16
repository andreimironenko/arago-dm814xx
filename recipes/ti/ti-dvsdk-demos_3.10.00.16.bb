
require ti-dvsdk-demos.inc

SRC_URI = "http://tigt_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dvsdk_demos_${PV}.tar.gz;name=dvsdkdemostarball"

S = "${WORKDIR}/dvsdk_demos_${PV}"

PV = "3_10_00_16"
PR = "${INC_PR}.1"

SRC_URI_append_dm365 = " file://dm365-demos-with-new-data-paths.patch;patch=1 \
    file://changed_insmod_to_modprobe_in_loadmodules_3.10.00.14.patch;patch=1 \
    file://remove-loadmodules_hd.sh-from-make-install_3.10.00.14.patch;patch=1"

SRC_URI[dvsdkdemostarball.md5sum] = "a9e7ee67129b33511debc197b38d456b"
SRC_URI[dvsdkdemostarball.sha256sum] = "36af5854d1f0e959b7e2f3b5ec210fd2bcd00f54b868303eb2e789e1230a0b05"
