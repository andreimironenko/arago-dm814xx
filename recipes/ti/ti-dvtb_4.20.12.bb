
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvtb/4.20.12/exports/dvtb_${PV}.tar.gz;name=dvtbtarball "

require ti-dvtb.inc

PV = "4_20_12"

SRC_URI_append = " file://make-include-rules.make-conditional.patch \
 file://loadmodules-ti-dvtb-${DVTBPLATFORM}.sh"

SRC_URI[dvtbtarball.md5sum] = "74d0c71450265e35b963b89528f9bee9"
SRC_URI[dvtbtarball.sha256sum] = "4b35c595ed78d0773077f5b9a0dffda124b8328a4073bf6654ed690a857f48b7"

