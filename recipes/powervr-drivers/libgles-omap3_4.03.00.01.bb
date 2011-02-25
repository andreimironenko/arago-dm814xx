BINLOCATION = "${S}/gfx_rel_es3.x"

ES2LOCATION = "${S}/gfx_rel_es2.x"
ES3LOCATION = "${S}/gfx_rel_es3.x"
ES5LOCATION = "${S}/gfx_rel_es5.x"

require libgles-omap3.inc

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

SGXPV = "4_03_00_01"
IMGPV = "1.6.16.3977"
BINFILE := "Graphics_SDK_setuplinux_${SGXPV}.bin"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin \
                   file://cputype \
                   file://rc.pvr \
                   file://sample.desktop \
                   file://99-bufferclass.rules  \
"

SRC_URI[md5sum] = "4163ee51bc05689b6f61e7eadf5ab9cf"
SRC_URI[sha256sum] = "1afe0dc4974c3c0a0ccf267a531d0964459d1cdf1929e732958230641973da94"

SRC_URI +=  "file://sysutils_linux.c"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}"

do_prepsources() {
    cp -f ${WORKDIR}/sysutils_linux.c ${S}/GFX_Linux_KM/services4/system/ti81xx/
}

addtask prepsources after do_unpack before do_patch
