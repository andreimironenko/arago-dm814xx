require ti-rpe.inc

PV = "1_00_00_03"

SRC_URI = "http://swubn01.india.ti.com/publish/RPE/${PV}/exports/rpe-${PV}.tar.gz;name=rpe \
           file://0001-examples-Fix-platform-for-ti816x.patch \
           file://0001-makerules-Change-from-generic-platform-for-ti816x.patch \
           file://0002-examples-dm81xx-Setup-memory-map-for-ti816x-as-per-e.patch \
"

SRC_URI[rpe.md5sum] = "8c64b031a19e309c5df1f11730080d25"
SRC_URI[rpe.sha256sum] = "13ae740ba01979fbd0f47a5065445792ac5a00a3eedcdf190df95548a3a466c3"
