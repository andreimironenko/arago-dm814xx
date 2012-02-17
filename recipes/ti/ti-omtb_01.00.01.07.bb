require ti-omtb.inc

PV = "01_00_01_07"

SRC_URI[omtb.md5sum] = "fcb523f381eb91367c1565fb09dc3228"
SRC_URI[omtb.sha256sum] = "2060cc77cd08fcdb1472563b1c24df935ece0b37fb1a44d4b3ca77702139f4af"

SRC_URI += "file://0001-makerules-Add-support-for-multiple-toolchains.patch \
"
