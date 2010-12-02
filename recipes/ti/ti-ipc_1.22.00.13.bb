require ti-ipc.inc

PV = "1_22_00_13_eng"

SRC_URI[ipcbin.md5sum] = "df367fbf310bc2fdd6aabb6bce81a0e6"
SRC_URI[ipcbin.sha256sum] = "8b7892708e5f7d152ef477a08de8f535966c4d86151af3d997f1bf96bf55d2e7"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="www.sanb.design.ti.com"
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/IPC/1_22_00_13/exports/ipc_setuplinux_${PV}.bin;name=ipcbin"

