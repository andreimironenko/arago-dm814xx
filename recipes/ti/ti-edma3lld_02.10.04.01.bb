require ti-edma3lld.inc

PV = "02_10_04_01"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_tii/psp/edma3_lld/edma3-lld-bios6/${PV}//exports/EDMA3_LLD_setuplinux_${PV}.bin;name=edma3lldbin"

SRC_URI[edma3lldbin.md5sum] = "22b92e75b2777ccbcc232b62bfa7893c"
SRC_URI[edma3lldbin.sha256sum] = "c19594ab6081bec03abef4ed6d49cd4272172a84b0e4ce7b4a2a62c9eeaa9256"

