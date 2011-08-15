require ti-c6accel.inc

PV = "2_01_00_09"

SRC_URI = "https://gforge01.dal.design.ti.com/gf/download/frsrelease/563/4833/c6accel_${PV}_Linux-x86_Setup.bin;name=c6accelbin \
           file://0001-C6Accel-Update-Memory-Map-for-EZSDK-5.02.patch \
           file://0001-Fixed-server-and-packages-targets.patch \
           file://0001-ti-c6accel-Fix-Makefile-to-install-for-ti814x-correc.patch \
           file://0001-Added-sdk-make-install-file-to-c6accel.patch \
           file://0001-Fixed-Makefile-server-and-packages-targets.patch \
"

SRC_URI[c6accelbin.md5sum] = "60f0cb727094408a7acfc46f40757db4"
SRC_URI[c6accelbin.sha256sum] = "5aa2067902dc4abc39054195d8f79da0a5c91b1b9c81bae007cc2966c843a076"
