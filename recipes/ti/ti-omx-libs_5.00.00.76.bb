require ti-omx-libs.inc

PV = "05_00_00_76"

SRC_URI[omx.md5sum] = "7409075a4370f9c708c8ffce01f5e921"
SRC_URI[omx.sha256sum] = "26d33bb126442c25ec1d7329a35e93951239b780b2383c06e737ffc6903e0043"

SYSLINKPV = "02_00_00_67"
SYSLINKPVwithdots = "02.00.00.67"
SYSLINKPVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "375c975fad16cf75cb918b6a90073e68"
SRC_URI[syslinktarball.sha256sum] = "055f7c650fffd178b07a163c10699a6817425fd6d310299fbe1bcbde6ae27f23"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI += "http://bangsdowebsvr01.india.ti.com:8060/SysLink_int/${SYSLINKPV}${SYSLINKPVextra}/exports/syslink_${SYSLINKPV}${SYSLINKPVextra}.tar.gz;name=syslinktarball \
"

SYSLINK_ROOT = "${WORKDIR}/syslink_${SYSLINKPV}${SYSLINKPVextra}"
export SYSLINK_ROOT

