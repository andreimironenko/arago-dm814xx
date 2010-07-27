COMPATIBLE_MACHINE = "(da850-omapl138-evm)"
DEMOSPLATFORM = "omapl138"
PV = "1_10_00_00"
SRC_URI = "http://install.source.dir.local/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

require ti-data.inc

SRC_URI[omapl138_datatarball.md5sum] = "2fe04a1aca3c758bcdab3798b557699e"
SRC_URI[omapl138_datatarball.sha256sum] = "c04dc484c1c31fde13e05c450ccc450d4879c5f7b994a691e21a702cf76276d3"

