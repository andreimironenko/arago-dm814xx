require ti-ipc.inc

PV = "1_23_03_31"

SRC_URI[ipcbin.md5sum] = "5f3bc7da44a0335366ced7ab4243fa21"
SRC_URI[ipcbin.sha256sum] = "8dacaede8afeacd29c76d882f6f99a4e4207da3fdafbf2d2a5dc0e9f79efdef9"

SRC_URI += "file://0001-Add-SDK-Config.bld-to-enable-out-of-the-box-builds.patch \
"
