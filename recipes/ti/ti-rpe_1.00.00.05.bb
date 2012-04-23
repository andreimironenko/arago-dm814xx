require ti-rpe.inc

PV = "1_00_00_05"

SRC_URI = "http://swubn01.india.ti.com/publish/RPE/${PV}/exports/rpe-${PV}.tar.gz;name=rpe \
"

SRC_URI[rpe.md5sum] = "25bc9e3debad7544e5164b4c7515f95d"
SRC_URI[rpe.sha256sum] = "7c28f0685c51cb6a82cacb7cf91935a238f613c6275efd3af11408b83b3ae1dc"
