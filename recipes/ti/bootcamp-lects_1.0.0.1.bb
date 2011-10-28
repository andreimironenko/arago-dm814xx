require bootcamp-lects.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/707/5143/bc-pres-rel_v_01_00_00_01.tgz;name=base \
    "

SRC_URI[base.md5sum] = "760c5a21be2403f2c8c44996ac7a3913"
SRC_URI[base.sha256sum] = "d40329ba005f264fe004797d118e1b3114ce0dbec3e7d24242fb692240bd56e4"

