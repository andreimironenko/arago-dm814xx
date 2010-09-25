COMPATIBLE_MACHINE = "(omap3evm|dm37x-evm)"
DEMOSPLATFORM = "omap3530"
PV = "1_00_00_16"
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/avdata/latest/exports/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

require ti-data.inc

SRC_URI[omap3530_datatarball.md5sum] = "b1a3712828d6f204dbfe7576e9a58f45"
SRC_URI[omap3530_datatarball.sha256sum] = "18d64976826987aa3a3b515f0eeeaf5fe8e0f2e05fa479860958aeecc068d8bf"

