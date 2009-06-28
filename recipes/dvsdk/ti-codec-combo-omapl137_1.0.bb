DESCRIPTION = "OMAPL137 Codec Combo"

# Should be replaced with real http URL, but for now create codec combo tar from DVSDK installation.
SRC_URI	= "http://install.source.dir.com/omapl137_dvsdk_combos_1_0.tar.gz"

S = "${WORKDIR}/omapl137_dvsdk_combos_1_0"

# Yes, the xdc stuff still breaks with a '.' in PWD
PV = "100"
PR = "r1"

installdir = "${prefix}/ti"
do_compile() {
	echo "do nothing"
}

do_install () {
    install -d ${D}/${installdir}/codec-combo
	cd ${S}
	for file in `find . -name *.x64P`; do
		cp ${file} ${D}/${installdir}/codec-combo
	done
}

do_stage() {
    install -d ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}
    cp -pPrf ${S}/* ${STAGING_DIR}/${MULTIMACH_TARGET_SYS}/${PN}/ 
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "/${installdir}/codec-combo/*"

