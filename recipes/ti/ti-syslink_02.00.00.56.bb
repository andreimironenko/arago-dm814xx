require ti-syslink.inc

PV = "02_00_00_56"
PVwithdots = "02.00.00.56"
PVExtra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "76102d30ff7ebea9c142ac784e0bdda3"
SRC_URI[syslinktarball.sha256sum] = "ff60e2f88699bf13355c03265767905f135a1f44f1ec76fd38803db3f6e7dc9a"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink/${PV}${PVExtra}/REL_SYSLINK_${PVwithdots}/syslink_${PV}${PVExtra}.tar.gz;name=syslinktarball"

