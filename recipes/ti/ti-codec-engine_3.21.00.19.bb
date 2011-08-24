require ti-codec-engine.inc

PV = "3_21_00_19"
PVExtra = ""

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball \
           file://0001-CE-Update-memory-map-for-EZSDK-5.02.patch \
           file://0001-Added-sdk-make-install-file-to-ce.patch \
"

SRC_URI[cetarball.md5sum] = "40f2f177503ce58522d119dd424593b2"
SRC_URI[cetarball.sha256sum] = "cba82aa22d7987428b56d3f021b07d52f30907dc24a13ccebdfbbab8152393f1"
