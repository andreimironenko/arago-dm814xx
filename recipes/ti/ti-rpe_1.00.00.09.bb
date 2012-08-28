require ti-rpe.inc

PV = "1_00_00_09"

SRC_URI = "http://swubn01.india.ti.com/publish/RPE/${PV}/exports/rpe-${PV}.tar.gz;name=rpe \
           file://aacdectest_c6a811x.cfg \
           file://aacenctest_c6a811x.cfg \
           file://mp3dectest_c6a811x.cfg \
"

SRC_URI[rpe.md5sum] = "95855f57ec018762598a1692a0c5d77b"
SRC_URI[rpe.sha256sum] = "8bd761aeea0de629d56745f8864dbc231479fab80fe7017a0e3375462adf5c5c"
