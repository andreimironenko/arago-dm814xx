require ti-codec-engine.inc

PV = "3_21_00_06"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball"

SRC_URI[cetarball.md5sum] = "5c2fddcaf4fedcbca85fba9082c7c135"
SRC_URI[cetarball.sha256sum] = "94230a85df1f5f8982e902beba63ad16f6d56487dc38cd947785717b62ffe574"

