PV = "4_20_12"
PV_dot = "4.20.12"

require ti-dvtb.inc

SRC_URI[dvtbtarball.md5sum] = "66bed3f3cb012aba7a200b33885758bf"
SRC_URI[dvtbtarball.sha256sum] = "56bf6b3110cdc38237b609a78c7809405129ac7b5cea0565ad81dccc2f08a706"


SRC_URI_append = " file://add-c6accel-path.patch "
