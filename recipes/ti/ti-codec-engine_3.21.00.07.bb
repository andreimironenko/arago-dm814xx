require ti-codec-engine.inc

PV = "3_21_00_07"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball"

SRC_URI[cetarball.md5sum] = "bb278cd7f08e6bf8f16496d2c3cd1f63"
SRC_URI[cetarball.sha256sum] = "b63e80fa051850538b4ec426f18a94f61af804efe57c880330c8d441da963f5c"

