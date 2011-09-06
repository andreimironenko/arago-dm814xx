DESCRIPTION = "Embedded Linux Device Driver Tests based on Linux Test Project"
HOMEPAGE = "http://arago-project.org/git/projects/test-automation/ltp-ddt.git"
LICENSE = "GPLv2"
SECTION = "console/utils"
DEPENDS += "zip-native virtual/kernel alsa-lib"
PV = "0.0.4"
PR = "r1"
PR_append = "+gitr${SRCPV}"

PROVIDES += "ltp"

inherit autotools

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"
BRANCH ?= "master"

SRC_URI = "git://arago-project.org/git/projects/test-automation/ltp-ddt.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

LTPROOT = "/opt/ltp"

EXTRA_OEMAKE_append = " \
	prefix=${LTPROOT} \
	CROSS_COMPILE=${HOST_PREFIX} \
	SKIP_IDCHECK=1 \
	KERNEL_INC=${STAGING_KERNEL_DIR}/include \
	KERNEL_USR_INC=${STAGING_INCDIR} \
	ALSA_INCPATH=${STAGING_INCDIR} \
	ALSA_LIBPATH=${STAGING_LIBDIR} \
	PLATFORM=${MACHINE} \
"

TARGET_CC_ARCH += "${LDFLAGS}"

FILES_${PN}-dbg += " \
	${LTPROOT}/.debug \
	${LTPROOT}/bin/.debug \
	${LTPROOT}/runtest/.debug \
	${LTPROOT}/testcases/bin/.debug \
	${LTPROOT}/testcases/bin/*/bin/.debug \
	${LTPROOT}/testcases/bin/*/test/.debug \
	${LTPROOT}/testcases/bin/ddt/.debug \
	${LTPROOT}/testcases/bin/ddt/*/bin/.debug \
	${LTPROOT}/testcases/bin/ddt/*/test/.debug \
"

FILES_${PN} += "${LTPROOT}/*"

do_configure() {
	cp ${S}/include/config.h.default ${S}/include/config.h
	cp ${S}/include/mk/config.mk.default ${S}/include/mk/config.mk
	cp ${S}/include/mk/features.mk.default ${S}/include/mk/features.mk
	echo "${TAG}" > ${S}/ChangeLog
}

do_install() {
	oe_runmake DESTDIR=${D} install
	install -d ${D}${datadir}
	cp -a ${D}${LTPROOT}/share/* ${D}${datadir}
	rm -rf ${D}${LTPROOT}/share/
}
