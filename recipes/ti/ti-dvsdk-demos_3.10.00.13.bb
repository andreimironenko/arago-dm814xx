require ti-dvsdk-demos.inc

SRC_URI = "http://tigt_dev.gt.design.ti.com/dev/DVSDK/310_DVSDK/3_10_00/dvsdk_demos_${PV}.tar.gz;name=dvsdkdemostarball"

S = "${WORKDIR}/dvsdk_demos_${PV}"

PV = "3_10_00_13"
PR = "${INC_PR}.1"

SRC_URI_append_dm365 = " file://dm365-demos-with-new-data-paths.patch;patch=1 \
    file://changed_insmod_to_modprobe_in_loadmodules.patch;patch=1"

SRC_URI[dvsdkdemostarball.md5sum] = "6aa699881e4afdac2b6797fc314b324a"
SRC_URI[dvsdkdemostarball.sha256sum] = "af72608d0a06cf8b9214efeb0e9689f6acfa3d3ceed10767eb93d10e979075d1"
