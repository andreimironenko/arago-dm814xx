
SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvtb/latest/exports/dvtb_${PV}.tar.gz;name=dvtbtarball"

require ti-dvtb.inc

PV = "4_20_10"

SRC_URI_append = " file://make-include-rules.make-conditional.patch \
 file://loadmodules-ti-dvtb-${DVTBPLATFORM}.sh"

SRC_URI[dvtbtarball.md5sum] = "5fa629045dd5179e08be6bdea774b978"
SRC_URI[dvtbtarball.sha256sum] = "5a008332c01c12f6f10d9c7204f535c70d543e56265631107548bc139dde85da"

do_configure() {
         sed -i -e 's:(LINK_INSTALL_DIR)/packages:(LINK_INSTALL_DIR):g' ${S}/packages/ti/sdo/dvtb/omap3530/linux/Makefile
}

