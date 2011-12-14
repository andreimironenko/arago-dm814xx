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

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

SGXPV = "4_05_00_03"
IMGPV = "1.6.16.4117"
BINFILE = "Graphics_SDK_setuplinux_${SGXPV}.bin"

SRC_URI[md5sum] = "0e651eaa92bb91760f0b40a17697a7dc"
SRC_URI[sha256sum] = "bfe764a8959556195545d6fff76f63a489642f345c105bbbc309a3f243c2dd0e"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}"
