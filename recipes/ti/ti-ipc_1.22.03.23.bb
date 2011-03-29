require ti-ipc.inc

PV = "1_22_03_23"

SRC_URI[ipcbin.md5sum] = "beee06b35cb1aee32fbe2cfaa24eac4d"
SRC_URI[ipcbin.sha256sum] = "0712599931c2ec2fd1f28a2a23d06dc360400a1b02cf967cc9e1f32d98a2e81f"

SRC_URI =+ "file://0001-ti-ipc-Fix-for-cache-invalidate-issue.patch"
