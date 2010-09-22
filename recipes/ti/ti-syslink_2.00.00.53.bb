require ti-syslink.inc

PV = "02_00_00_53_alpha2"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8060/SysLink/${PV}/REL_SYSLINK_02.00.00.53/syslink_${PV}.tar.gz;name=syslinktarball"

SRC_URI[syslinktarball.md5sum] = "99153c7b458defd53496adc6dbc5b4b3"
SRC_URI[syslinktarball.sha256sum] = "03bf931f98d2a576f52492f3b78cc07aaf3889c312fc426a866aa6811d5c4ed1"
