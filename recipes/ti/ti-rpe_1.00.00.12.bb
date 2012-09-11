require ti-rpe.inc

PV = "1_00_00_12"

SRC_URI = "http://swubn01.india.ti.com/publish/RPE/${PV}/exports/rpe-${PV}.tar.gz;name=rpe \
           file://aacdectest_c6a811x.cfg \
           file://aacenctest_c6a811x.cfg \
           file://mp3dectest_c6a811x.cfg \
"

SRC_URI[rpe.md5sum] = "af179b5bcb2263232ea434b3578fc3ba"
SRC_URI[rpe.sha256sum] = "fc2b8eee386e98b7376c1805b102e711e05253ba249e5c14ebff5df82f6bf8aa"
