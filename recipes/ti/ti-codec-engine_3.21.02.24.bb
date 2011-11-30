require ti-codec-engine.inc

PV = "3_21_02_24"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball \
"

SRC_URI[cetarball.md5sum] = "42c9255a64e54d81787dc38219329945"
SRC_URI[cetarball.sha256sum] = "9e98802d2373a5834c065f33f2b7a2ad5422882de1f24c0864dae17c46c116cf"
