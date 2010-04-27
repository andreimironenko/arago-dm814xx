require ti-dvtb.inc

PV = "4_20_04_4"

SRC_URI_append = " file://make-include-rules.make-conditional.patch;patch=1 \
	file://loadmodules-ti-dvtb-dm365.sh \
	file://loadmodules-ti-dvtb-dm6467.sh \
	file://loadmodules-ti-dvtb-dm355.sh \
"

SRC_URI[dvtbtarball.md5sum] = "ec2af1f1b94602cab92eeac06b7ababf"
SRC_URI[dvtbtarball.sha256sum] = "df65460a5193e7f0112689a0ff95358c29844ba3ac91f9e4866522bd4e60756d"
