require ti-syslink.inc

PV = "02_00_00_67"
PVwithdots = "02.00.00.67"
PVextra = "_alpha2"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink_int/${PV}${PVextra}/exports/syslink_${PV}${PVextra}.tar.gz;name=syslinktarball" 

SRC_URI[syslinktarball.md5sum] = "375c975fad16cf75cb918b6a90073e68"
SRC_URI[syslinktarball.sha256sum] = "055f7c650fffd178b07a163c10699a6817425fd6d310299fbe1bcbde6ae27f23"

SRC_URI =+ "file://0001-ti-syslink-Patch-to-fix-tear-down-issues.patch"
SRC_URI =+ "file://0002-ti-syslink-Cache-invalidate-fix-added.patch"
