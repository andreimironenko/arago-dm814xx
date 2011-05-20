require ti-omx-libs.inc

PV = "05_01_01_78"

SRC_URI[omx.md5sum] = "142c657f04bfafac9cc7824abcef0301"
SRC_URI[omx.sha256sum] = "fb560fb4a1a4c2b2fe0fdd48ec4f1bba3dfd48d967b7dc5a9169954237f550e5"

SYSLINKPV = "02_00_00_68"
SYSLINKPVwithdots = "02.00.00.68"
SYSLINKPVextra = "_beta1"

SRC_URI[syslinktarball.md5sum] = "468034372124f70f82b60cfb5f11c8e8"
SRC_URI[syslinktarball.sha256sum] = "ed574dcb3a5477cfbc69a1c9e768d5197291cb057d19fd791e16e1c89af3e8e1"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI += "http://bangsdowebsvr01.india.ti.com:8060/SysLink_int/${SYSLINKPV}${SYSLINKPVextra}/exports/syslink_${SYSLINKPV}${SYSLINKPVextra}.tar.gz;name=syslinktarball \
"

SYSLINK_ROOT = "${WORKDIR}/syslink_${SYSLINKPV}${SYSLINKPVextra}"
export SYSLINK_ROOT
