require ti-sysbios.inc

PV = "6_32_05_51"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/BIOS/${PV}/exports/bios_setuplinux_${PV}${PVExtra}.bin;name=sysbiosbin"

SRC_URI[sysbiosbin.md5sum] = "296f3776e65f7ceaf1cbaf1e15584ebf"
SRC_URI[sysbiosbin.sha256sum] = "20f63a78afad4b320e468b2d4b2eef31728e9d31f7b13daa7fe62bd7ac829711"
