require ti-codec-engine.inc

PV = "3_21_00_17"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball"

SRC_URI[cetarball.md5sum] = "9c63ddfe210e148fa8e6a92a3e151b41"
SRC_URI[cetarball.sha256sum] = "666c3ba794227323ddc33ba60031cc5028fd2475da3188d7706489da03361bc3"
