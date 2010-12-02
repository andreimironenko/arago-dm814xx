require ti-syslink.inc

PV = "02_00_00_65"
PVwithdots = "02.00.00.65"
PVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "c20707d8205a66be0f1aca95761b51e5"
SRC_URI[syslinktarball.sha256sum] = "79f694e37182892f1f3ed066ad62a6b502111ed23995328fb669b351b788e95e"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink/${PV}${PVextra}/REL_SYSLINK_${PVwithdots}/syslink_${PV}${PVextra}.tar.gz;name=syslinktarball"

