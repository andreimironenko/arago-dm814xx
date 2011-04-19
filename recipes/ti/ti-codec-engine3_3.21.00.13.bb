require ti-codec-engine3.inc

PV = "3_21_00_13"
PVExtra = "_eng"

# This is an internal engineering release. Do not use this release by default.
DEFAULT_PREFERENCE = "-1"
HTTP_PROXY_IGNORE = "www.sanb.design.ti.com"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball"

SRC_URI[cetarball.md5sum] = "d838be8278642bc6f4ad604ae00ae64b"
SRC_URI[cetarball.sha256sum] = "97caf3c66eb61454eee8a0c79497d5d8d1bba6e120f29cee28e8cef40f0f4e78"
