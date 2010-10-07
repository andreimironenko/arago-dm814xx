require ti-syslink.inc

PV = "02_00_00_56"
PVwithdots = "02.00.00.56"
PVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "3c64532333e630e3742af84501d0dc7f"
SRC_URI[syslinktarball.sha256sum] = "d04eb1e3141f2056f3cd000c21a98c3bac14c4a43c88b74f0fb5d783bddeefe9"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink/${PV}${PVextra}/REL_SYSLINK_${PVwithdots}/syslink_${PV}${PVextra}.tar.gz;name=syslinktarball"

