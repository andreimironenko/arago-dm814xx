require ti-syslink.inc

PV = "02_00_00_68"
PVwithdots = "02.00.00.68"
PVExtra = "_beta1"

SRC_URI[syslinktarball.md5sum] = "468034372124f70f82b60cfb5f11c8e8"
SRC_URI[syslinktarball.sha256sum] = "ed574dcb3a5477cfbc69a1c9e768d5197291cb057d19fd791e16e1c89af3e8e1"

SRC_URI =+ "file://0001-TI81XX-Ducati-Avoid-clearing-extra-bits-when-loading.patch"
