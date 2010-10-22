require ti-ipc.inc

PV = "1_22_00_10_eng"

SRC_URI[ipcbin.md5sum] = "48a6354f4e65df456ba5afbf05f97490"
SRC_URI[ipcbin.sha256sum] = "91a7a50a1a9b5893415cfb1b41f0e7918be7222f772e996645840267700c111d"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="www.sanb.design.ti.com"
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/IPC/1_22_00_10/exports/ipc_setuplinux_${PV}.bin;name=ipcbin"

