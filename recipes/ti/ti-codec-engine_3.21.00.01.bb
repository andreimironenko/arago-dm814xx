require ti-codec-engine.inc

PV = "3_21_00_01"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/Codec_Engine/${PV}/exports/codec_engine_${PV}${PVExtra},lite.tar.gz;name=cetarball"

SRC_URI[cetarball.md5sum] = "9a4af80d3ccd6001dd54b91ac3b11cbb"
SRC_URI[cetarball.sha256sum] = "504adc37e563bd959bbaabbbb0b684d5e26a554efe38f93d4692a9062e75ca89"

