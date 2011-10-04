require ti-ipc.inc

PV = "1_23_05_38"
PVExtra = "_eng"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/IPC/${PV}/exports/ipc_setuplinux_${PV}${PVExtra}.bin;name=ipcbin \
           file://ipc.pc \
"

SRC_URI[ipcbin.md5sum] = "003e6a152a109d6fda318c65c27124fc"
SRC_URI[ipcbin.sha256sum] = "f152c1c669c9945e599fec7cb70bf1c5416d1b2cd955fa81c769af92523086d1"
