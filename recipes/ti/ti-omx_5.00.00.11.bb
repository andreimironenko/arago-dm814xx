require ti-omx.inc

COMPATIBLE_MACHINE = "dm816x-evm"

PV = "05_00_00_11"
PVExtra = ""

SRC_URI[omx.md5sum] = "d3f2ef7dbfcd42e47aa0db217c44ff66"
SRC_URI[omx.sha256sum] = "6389f9b3dc8c05a651ea00e4f6981070c70252984a11765860cdf65e8998609d"

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

