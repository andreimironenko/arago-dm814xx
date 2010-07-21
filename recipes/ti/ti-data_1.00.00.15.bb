require ti-data.inc

PV = "1_00_00_15"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/avdata/latest/exports/${DEMOSPLATFORM}_avdata_${PV}.tar.gz;name=${DEMOSPLATFORM}_datatarball"

SRC_URI[dm365_datatarball.md5sum] = "43c1aba1d6af2103249f0708d3b01522"
SRC_URI[dm365_datatarball.sha256sum] = "841c894281f33c9ea1fddb71251b74b0ecb9ff8d971eabbd2b0170f04548aeb1"

SRC_URI[dm355_datatarball.md5sum] = "d4dec668fc7eb226b4fa9785d17737ec"
SRC_URI[dm355_datatarball.sha256sum] = "dcda5a5718a09d102e2dc601d69ee04aa8b47d6cdbd6778ed6bcb05aae85eaa3"

SRC_URI[dm6467_datatarball.md5sum] = "dcfeebc18ba34899c3827badf72d5945"
SRC_URI[dm6467_datatarball.sha256sum] = "ade2d5276ccbd97377f0d0c3d23bfe6fd06513202a1fdff11566d55e0c1cec19"

SRC_URI[omapl138_datatarball.md5sum] = "2d6012aa5fbf12fa02d43fac4c9f0f47"
SRC_URI[omapl138_datatarball.sha256sum] = "7cb5a88f6c9defa84112173bdbdc66086560ae8dbcb00ae2505883c8dd4beace"

