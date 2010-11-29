require ti-c6accel.inc


SRC_URI_append = "file://fix-loadmodule.patch \
                  file://remove-benchmark-file.patch "

PV = "1_01_00_02"

SRC_URI[c6accelbin.md5sum] = "e198843367883f3b1834bc15e9c860f4"
SRC_URI[c6accelbin.sha256sum] = "9869614753cb9347b59e5fe1ab098ed02cf90a9b9efc53d107528e0ff86ba4fa"

