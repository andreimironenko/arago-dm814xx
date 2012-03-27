# untested and huge change from 1.4.x
# DEFAULT_PREFERENCE = "-1"

require libgles-omap3.inc

BINLOCATION_omap3  = "${S}/gfx_rel_es3.x"
BINLOCATION_ti816x = "${S}/gfx_rel_es6.x"
BINLOCATION_ti814x = "${S}/gfx_rel_es6.x"

ES2LOCATION = "${S}/gfx_rel_es2.x"
ES3LOCATION = "${S}/gfx_rel_es3.x"
ES5LOCATION = "${S}/gfx_rel_es5.x"
ES6LOCATION = "${S}/gfx_rel_es6.x"

PACKAGE_STRIP = "no"

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

SGXPV = "4_04_00_02"
IMGPV = "1.6.16.4117"
BINFILE := "Graphics_SDK_setuplinux_${SGXPV}.bin"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin \
                   file://cputype \
                   file://rc.pvr \
                   file://sample.desktop \
                   file://99-bufferclass.rules  \
		           file://0001-Added-sdk-make-install-file-to-graphics-sdk.patch \
		           file://0001-GFX_Linux_KM-Fix-os-functions.patch \
"

SRC_URI[md5sum] = "901327765c0991900afda91473f84725"
SRC_URI[sha256sum] = "2ced2329ec819e42d57eca1a11120b02ec9c332fb63baa0a41285eed07d4b48e"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}"

