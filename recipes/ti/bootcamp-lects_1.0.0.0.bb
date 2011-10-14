require bootcamp-lects.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/655/5072/bc-pres-rel_v_01_00_00.tgz;name=base \
    "

SRC_URI[base.md5sum] = "2cf206014f041d22858a6b6acb5d47e3"
SRC_URI[base.sha256sum] = "6000c3c641e22a3a8612089cd8a1937a53a8b73f0123a4e9078ef7a1d3d23a9c"

