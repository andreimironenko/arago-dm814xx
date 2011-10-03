require ti-codec-engine.inc

PV = "3_21_01_21"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball \
           file://0001-CE-Update-memory-map-for-EZSDK-5.02.patch \
           file://0001-Added-sdk-make-install-file-to-ce.patch \
"

SRC_URI[cetarball.md5sum] = "f4687470affecb884ab5f62df4aa36ec"
SRC_URI[cetarball.sha256sum] = "94203eef34ef0ba3dc6ee912576271bc3a722dcf6198c85e119b0ea2c450e62e"
