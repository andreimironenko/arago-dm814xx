DESCRIPTION = "Package containing Makefile and Rules.make file for TISDKs"
LICENSE = "TI"

require ti-paths.inc

# Build the list of component makefiles to put together to build the
# Makefile that goes into the SDK.  For legacy devices the base Makefile
# will be picked up and will contain everything.
#
# It is assumed that the component makefiles follow the naming
# Makefile_$component.  All Makefiles will be part of the SRC_URI to be
# fetched, but only the listed ones will be used to build the final Makefile
MAKEFILES_COMMON = "linux matrix-gui am-benchmarks am-sysinfo matrix-gui-browser"

# Blank the MAKEFILES_COMMON for devices that don't use the component flow
MAKEFILES_COMMON_dm816x-evm = ""
MAKEFILES_COMMON_dm814x-evm = ""
MAKEFILES_COMMON_c6a816x-evm = ""
MAKEFILES_COMMON_da830-omapl137-evm = ""
MAKEFILES_COMMON_da850-omapl138-evm = ""
MAKEFILES_COMMON_dm355-evm = ""
MAKEFILES_COMMON_dm365-evm = ""
MAKEFILES_COMMON_dm368-evm = ""
MAKEFILES_COMMON_dm37x-evm = ""
MAKEFILES_COMMON_dm6446-evm = ""
MAKEFILES_COMMON_dm6467-evm = ""

# Add device specific make targets
MAKEFILES_am37x-evm = "x-load u-boot-legacy av-examples"
MAKEFILES_am3517-evm = "x-load u-boot-legacy"
MAKEFILES_ti33x = "u-boot-spl"
MAKEFILES_am389x-evm = "u-boot-min-sd u-boot-legacy"
MAKEFILES_am387x-evm = "u-boot-min-sd u-boot-legacy"
MAKEFILES_beagleboard = "x-load u-boot-legacy"
MAKEFILES_am180x-evm = "pru"



SRC_URI = "\
	file://Makefile \
  	file://Rules.make \
    file://Makefile_linux \
    file://Makefile_u-boot-legacy \
    file://Makefile_matrix-gui \
    file://Makefile_am-benchmarks \
    file://Makefile_am-sysinfo \
    file://Makefile_x-load \
    file://Makefile_av-examples \
    file://Makefile_u-boot-min-sd \
    file://Makefile_u-boot-spl \
    file://Makefile_matrix-gui-browser \
    file://Makefile_pru \
"

PR = "r89"

do_configure_prepend_dm37x-evm () { 
         sed -i -e 's:OMAPES=3.x:OMAPES=5.x:g' ${WORKDIR}/Makefile
         sed -i -e 's:gfx_rel_es3.x:gfx_rel_es5.x:g' ${WORKDIR}/Makefile
         sed -i -e 's:\$(PLATFORM):dm3730:g' ${WORKDIR}/Rules.make
         sed -i -e 's:omap3530_config:dm3730_config:g' ${WORKDIR}/Makefile
}

# This step will stitch together the final Makefile based on the supported
# make targets.
do_install () {
	install -d ${D}/${installdir}

    # Start with the base Makefile
	install  ${WORKDIR}/Makefile ${D}/${installdir}/Makefile

    # Remember the targets added so we can update the all target
    targets=""
    clean_targets=""
    install_targets=""

    # Now add common build targets
    for x in ${MAKEFILES_COMMON}
    do
        cat ${WORKDIR}/Makefile_${x} >> ${D}/${installdir}/Makefile
        targets="$targets""$x\ "
        clean_targets="$clean_targets""$x""_clean\ "
        install_targets="$install_targets""$x""_install\ "
    done

    # Now add device specific build targets
    for x in ${MAKEFILES}
    do
        cat ${WORKDIR}/Makefile_${x} >> ${D}/${installdir}/Makefile
        targets="$targets""$x\ "
        clean_targets="$clean_targets""$x""_clean\ "
        install_targets="$install_targets""$x""_install\ "
    done

    # Update the all, clean, and install targets if we added targets
    if [ "$targets" != "" ]
    then
        sed -i -e "s/__ALL_TARGETS__/$targets/" ${D}/${installdir}/Makefile
        sed -i -e "s/__CLEAN_TARGETS__/$clean_targets/" ${D}/${installdir}/Makefile
        sed -i -e "s/__INSTALL_TARGETS__/$install_targets/" ${D}/${installdir}/Makefile
    fi

	install  ${WORKDIR}/Rules.make ${D}/${installdir}/Rules.make

    # fixup Makefile for x-load and u-boot-min-sd machine types
    sed -i -e "s/__XLOAD_MACHINE__/${XLOAD_MACHINE}/" ${D}/${installdir}/Makefile
    u_boot_min_sd=`echo ${MACHINE} | sed s/-/_/`
    u_boot_min_sd="$u_boot_min_sd""_min_sd"
    sed -i -e "s/__UBOOT_MIN_SD_MACHINE__/$u_boot_min_sd/" ${D}/${installdir}/Makefile

    # fixup Rules.make values
    sed -i -e "s/__PLATFORM__/${MACHINE}/" ${D}/${installdir}/Rules.make
    sed -i -e "s/__ARCH__/${BASE_PACKAGE_ARCH}/" ${D}/${installdir}/Rules.make
    sed -i -e "s/__UBOOT_MACHINE__/${UBOOT_MACHINE}/" ${D}/${installdir}/Rules.make

}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/*"
