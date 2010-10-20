require ti-syslink.inc

PV = "02_00_00_56"
PVwithdots = "02.00.00.56"
PVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "2b2f097a63dabc066a42323bdf3cdb9e"
SRC_URI[syslinktarball.sha256sum] = "3a3ff92e3f2a181df1ff8392d3c1543ce802fb6467a375d0d9fd74cf04b69cda"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink/${PV}${PVextra}/REL_SYSLINK_${PVwithdots}/syslink_${PV}${PVextra}.tar.gz;name=syslinktarball"

