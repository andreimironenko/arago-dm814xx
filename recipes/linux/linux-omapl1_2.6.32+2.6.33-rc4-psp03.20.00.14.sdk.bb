require linux-omapl1.inc

KVER = "2.6.33-rc4"
PSPREL = "03.20.00.14.sdk"

SRCREV = "v2.6.33-rc4_DAVINCIPSP_03.20.00.14"

SRC_URI += "file://0001-uio_pruss1-Core-driver-addition.patch \
            file://0002-uio_pruss2-Platform-changes.patch \
            file://0003-uio_pruss3-Workarounds-put-into-core-code.patch"
