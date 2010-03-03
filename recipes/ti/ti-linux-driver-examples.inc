DESCRIPTION = "Linux driver examples/test applications"
HOMEPAGE = "http://arago-project.org/git/projects/?p=examples-davinci.git;a=summary"

DEPENDS = "virtual/kernel"

require ti-paths.inc
require ti-staging.inc

S = "${WORKDIR}/git"

do_compile () {
	cd ${S}/media_ti
    make KERNEL_PATH=${STAGING_KERNEL_DIR} clean
    make KERNEL_PATH=${STAGING_KERNEL_DIR}/include 
}
do_install () {
  	cd ${S}/media_ti
    install -d ${D}/${installdir}/ti-psp-tree/linux-driver-examples/
    make ${TARGET} EXEC_DIR=${D}/${installdir}/linux-driver-examples install
    cp -ar ${S}/* ${D}/${installdir}/ti-psp-tree/linux-driver-examples
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${installdir}/linux-driver-examples/*"
INSANE_SKIP_${PN} = True

