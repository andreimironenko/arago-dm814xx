require ti-omx.inc

COMPATIBLE_MACHINE = "dm814x-evm"

PV = "05_00_00_26"
PVExtra = ""

SRC_URI[omx.md5sum] = "e030b4879be7b9e27714b7550a2cb8fa"
SRC_URI[omx.sha256sum] = "baaef61299126655e837d83fba2f286190ff8bdfbf3fe2a40df02308b87e829b"

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

