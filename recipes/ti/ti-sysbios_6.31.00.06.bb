require ti-sysbios.inc

PV = "6_31_00_06_eng"

SRC_URI[sysbiosbin.md5sum] = "875626f04260d50fe90f903ff424f485"
SRC_URI[sysbiosbin.sha256sum] = "6e48e305349d6dff456bc56c13edfd7f45a4e9f5cc6295366a7ce1d451199f80"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="www.sanb.design.ti.com"
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/BIOS/6_31_00_06/exports/bios_setuplinux_${PV}.bin;name=sysbiosbin"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"
