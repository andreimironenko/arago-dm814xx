require ti-codec.inc

# Should be replaced with real http URL, but for now create codec combo tar from DVSDK installation.
SRC_URI	= "http://install.source.dir.local/omapl137_dvsdk_combos_1_0.tar.gz"

S = "${WORKDIR}/omapl137_dvsdk_combos_1_0"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "1_0"
PR = "r1"

do_compile() {
	echo "do nothing"
}

do_install () {
    install -d ${D}/${installdir}/codec-combo
	cd ${S}
	for file in `find . -name *.${DSPSUFFIX}`; do
		cp ${file} ${D}/${installdir}/codec-combo
	done

    install -d ${D}${CODEC_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${CODEC_INSTALL_DIR_RECIPE}
}

