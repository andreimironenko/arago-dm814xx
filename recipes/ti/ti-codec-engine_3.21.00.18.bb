require ti-codec-engine.inc

PV = "3_21_00_18"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball \
           file://0001-CE-Update-memory-map-for-EZSDK-5.02.patch \
"

SRC_URI[cetarball.md5sum] = "70432ec707a2747277317de2b041d46c"
SRC_URI[cetarball.sha256sum] = "ca1fb9611a7f93fc5dfd0be18ee55c2a4ce0cfb585b8935919e3c8e0e57a2dab"

SRC_URI += "file://0001-Add-SDK-Config.bld-to-enable-out-of-the-box-builds.patch \
"
