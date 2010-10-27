require ti-codec-engine.inc

PV = "3_21_00_02"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball"

SRC_URI[cetarball.md5sum] = "13f1bb5c516a14f4ccd577d1d8c88a73"
SRC_URI[cetarball.sha256sum] = "946f4ffbc5e3ede16394739f42d730b7abd90207b865bd23c39e8fe6d188709d"

