COMPATIBLE_MACHINE = "(dm365-evm)"
DEMOSPLATFORM = "dm365"
PV = "1_10_00_01"
SRC_URI = "http://install.source.dir.local/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

require ti-data.inc

SRC_URI[dm365_datatarball.md5sum] = "5fc553b0c05d8b14d022af78d7b47864"
SRC_URI[dm365_datatarball.sha256sum] = "ca2a8616ee031454f08fdaacd57c28628ac7b83ba760fe015af350eade3e7aa4"

