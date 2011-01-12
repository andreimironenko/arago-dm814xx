require ti-syslink.inc

PV = "02_00_00_66"
PVwithdots = "02.00.00.66"
PVextra = "_alpha2"

SRC_URI[syslinktarball.md5sum] = "1c4265aba8fc91bb3507734ab87d6a07"
SRC_URI[syslinktarball.sha256sum] = "c8c61af64af512e877d645f949bb4f6c1dcace637cab7321684d900d5e42f7ae"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="bangsdowebsvr01.india.ti.com:8060"
SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink_int/${PV}${PVextra}/exports/syslink_${PV}${PVextra}.tar.gz;name=syslinktarball" 

