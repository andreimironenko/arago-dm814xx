COMPATIBLE_MACHINE = "(omap3evm|dm3730-am3715-evm)"
DEMOSPLATFORM = "omap3530"
PV = "1_00_00_16"
SRC_URI = "http://install.source.dir.local/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

require ti-data.inc

SRC_URI[omap3530_datatarball.md5sum] = "cc7c9537a4be5fd9b4c2523ac3b7060a"
SRC_URI[omap3530_datatarball.sha256sum] = "8a7b27ea460bd261283ec57ea8aeb9505006f4cfaf951a4ef0a25f5d5ea25faf"

