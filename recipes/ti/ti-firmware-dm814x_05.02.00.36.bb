require ti-firmware-dm814x.inc

PV = "05_02_00_36"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/DM814x-Firmware/${PV}/exports/firmware-ti814x_${PV}.tar.gz;name=firmware \
"

SRC_URI[firmware.md5sum] = "d33789202a950c87928b70e6f7b96783"
SRC_URI[firmware.sha256sum] = "50a7179cba919500df2eae7785cfdad9092f2808fad7bd2fb50cb6ed87f1e208"
