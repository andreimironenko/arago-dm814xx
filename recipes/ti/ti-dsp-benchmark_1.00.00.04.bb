require ti-dsp-benchmark.inc

PV = "1_00_00_04"
PVExtra = "_pre-release"

SRC_URI = "https://gforge01.dal.design.ti.com/gf/download/frsrelease/493/4291/dsp_benchmark_demo_${PV}${PVExtra}_Linux-x86_Setup.bin;name=dspbenchmarksbin"

SRC_URI[dspbenchmarksbin.md5sum] = "296f2110f594fbf7c373a6ccaf6e95c3"
SRC_URI[dspbenchmarksbin.sha256sum] = "8291e857b476ec534cf52718ed3b6b9836298f13ad1460db8e42fbd1545f9ab2"

BINFILE="dsp_benchmark_demo_${PV}${PVExtra}_Linux-x86_Setup.bin"

S = "${WORKDIR}/dsp_benchmark_demo_${PV}${PVExtra}"
