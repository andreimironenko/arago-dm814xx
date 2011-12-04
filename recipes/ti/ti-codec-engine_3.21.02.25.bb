require ti-codec-engine.inc

PV = "3_21_02_25"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball \
"

SRC_URI[cetarball.md5sum] = "7db51940336a88fb97a60915dc9adb22"
SRC_URI[cetarball.sha256sum] = "2264408c47287f10c46cf19dc077db7803651b213f2bc495e2e271bb9fc28b4a"
