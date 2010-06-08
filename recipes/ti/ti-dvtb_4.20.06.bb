require ti-dvtb.inc

PV = "4_20_06"

SRC_URI_append = " file://make-include-rules.make-conditional.patch;patch=1 \
	file://loadmodules-ti-dvtb-dm365.sh \
	file://loadmodules-ti-dvtb-dm6467.sh \
	file://loadmodules-ti-dvtb-dm355.sh \
"

SRC_URI[dvtbtarball.md5sum] = "c7a52de7e3d276665569f714d0269004"
SRC_URI[dvtbtarball.sha256sum] = "307c387535c9894bd6938519cd445763c01a04815652a60a29b27473c887ba20"
