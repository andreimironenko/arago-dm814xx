require ti-edma3lld.inc

PV = "02_11_02_04"

SRC_URI[edma3lldbin.md5sum] = "8fe71e19c1044b534d364133ed6d6c27"
SRC_URI[edma3lldbin.sha256sum] = "85c3ad7d15282235879d9743f1c043a11d595eb109af6d9786fc180a25b8a664"

SRC_URI += "file://0001-Added-sdk-make-install-file-to-edma3lld.patch \
            file://0001-Added-targets-for-libs-only-build-of-centaurus-and-n.patch \
"
