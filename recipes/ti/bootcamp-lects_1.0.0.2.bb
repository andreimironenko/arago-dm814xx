require bootcamp-lects.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/743/5240/bc-pres-rel_v_01_00_00_02.tgz;name=base \
    "

SRC_URI[base.md5sum] = "f388814d034d64d530c4ff8a46566c52"
SRC_URI[base.sha256sum] = "27d24edbf5c640585112fd8900f850ec717b2636065ea42981066dd9f98f0c80"

