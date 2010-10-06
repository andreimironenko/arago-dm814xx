require ti-xdctools.inc

PV = "3_20_05_69_eng"

SRC_URI[xdcbin.md5sum] = "641d1106eb919a984925a17fcc53b7f4"
SRC_URI[xdcbin.sha256sum] = "833f556c40979968a7455b48836d7fe28728797efcd16022f703e96c91c5747e"

# Engineering build - SRC_URI is not public yet - override link
HTTP_PROXY_IGNORE="www.sanb.design.ti.com"
SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/XDCtools/3_20_05_69/exports/xdctools_setuplinux_${PV}.bin;name=xdcbin"

