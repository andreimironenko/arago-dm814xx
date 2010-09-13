
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvtb/4.20.10/exports/dvtb_${PV}.tar.gz;name=dvtbtarball "

require ti-dvtb.inc

PV = "4_20_10"

SRC_URI_append = " file://make-include-rules.make-conditional.patch \
 file://loadmodules-ti-dvtb-${DVTBPLATFORM}.sh \
 file://dsplink-and-ncurses-fix.patch"

SRC_URI[dvtbtarball.md5sum] = "5fa629045dd5179e08be6bdea774b978"
SRC_URI[dvtbtarball.sha256sum] = "5a008332c01c12f6f10d9c7204f535c70d543e56265631107548bc139dde85da"

