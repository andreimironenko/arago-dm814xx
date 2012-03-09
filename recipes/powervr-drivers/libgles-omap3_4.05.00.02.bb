# Only pushed to amsdk integration tree for now because this is an internal
# only release and the SDK was not built by a single toolchain.

BINLOCATION_omap3  = "${S}/gfx_rel_es3.x"
BINLOCATION_ti816x = "${S}/gfx_rel_es6.x"
BINLOCATION_ti33x = "${S}/gfx_rel_es8.x"

#ES2LOCATION = "${S}/gfx_rel_es2.x"
ES3LOCATION = "${S}/gfx_rel_es3.x"
ES5LOCATION = "${S}/gfx_rel_es5.x"
ES6LOCATION = "${S}/gfx_rel_es6.x"
ES8LOCATION = "${S}/gfx_rel_es8.x"

require libgles-omap3.inc

DEFAULT_PREFERENCE = "-1"

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

SGXPV = "4_05_00_02"
IMGPV = "1.6.16.4117"
BINFILE = "Graphics_SDK_setuplinux_${SGXPV}.bin"

SRC_URI[md5sum] = "710f397849c3d1fbc7ec84a0cba980f5"
SRC_URI[sha256sum] = "0625308c31e32eadd5fa48e760d1b18305aa3d8b89375be8cfb29dd4f222c1d6"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}"
