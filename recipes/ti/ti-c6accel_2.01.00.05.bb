require ti-c6accel.inc

PV = "2_01_00_05"

SRC_URI = "https://gforge01.dal.design.ti.com/gf/download/frsrelease/492/4289/c6accel_${PV}_prerelease_Linux-x86_Setup.bin;name=c6accelbin"

SRC_URI[c6accelbin.md5sum] = "fe87471d2cb4997f20e9ec32d4544c3c"
SRC_URI[c6accelbin.sha256sum] = "5f6952b6b7b6e30553c087e6780a4dec25b24e5250feb4e667c25a6b4303c04b"

BINFILE="c6accel_${PV}_prerelease_Linux-x86_Setup.bin"

S = "${WORKDIR}/c6accel_${PV}_prerelease"
