inherit sdk

require external-toolchain-arago.inc

PR = "r2"

PACKAGE_STRIP = "no"

INHIBIT_DEFAULT_DEPS = "1"
EXCLUDE_FROM_SHLIBS = "1"

PROVIDES = "\
	gcc-cross-sdk \
	binutils-cross-sdk \
"

PACKAGES = "\
	gcc-cross-sdk \
	binutils-cross-sdk \
"

gcclibdir = "${libdir}/gcc"

FILES_gcc-cross-sdk = "\
	${prefix}/${TARGET_SYS}/bin/cpp \
	${prefix}/${TARGET_SYS}/bin/cc \
	${prefix}/${TARGET_SYS}/bin/gccbug \
	${prefix}/${TARGET_SYS}/bin/g++ \
	${prefix}/${TARGET_SYS}/bin/gcov \
	${prefix}/${TARGET_SYS}/bin/gcc \
	${prefix}/${TARGET_SYS}/lib/libstdc++.* \
	${prefix}/${TARGET_SYS}/lib/libssp* \
	${prefix}/${TARGET_SYS}/lib/libgcc_s.* \
	${prefix}/${TARGET_SYS}/lib/libsupc++.* \
	${gcclibdir}/${TARGET_SYS}/${ARG_VER_GCC}/* \
	${bindir}/${TARGET_PREFIX}gcov \
	${bindir}/${TARGET_PREFIX}gccbug \
	${bindir}/${TARGET_PREFIX}gcc \
	${bindir}/${TARGET_PREFIX}g++ \
	${bindir}/${TARGET_PREFIX}cpp \
	${libexecdir}/* \
"

FILES_binutils-cross-sdk = "\
	${prefix}/${TARGET_SYS}/bin/ld \
	${prefix}/${TARGET_SYS}/bin/addr2line \
	${prefix}/${TARGET_SYS}/bin/objcopy \
	${prefix}/${TARGET_SYS}/bin/readelf \
	${prefix}/${TARGET_SYS}/bin/strip \
	${prefix}/${TARGET_SYS}/bin/nm \
	${prefix}/${TARGET_SYS}/bin/ranlib \
	${prefix}/${TARGET_SYS}/bin/gprof \
	${prefix}/${TARGET_SYS}/bin/as \
	${prefix}/${TARGET_SYS}/bin/c++filt \
	${prefix}/${TARGET_SYS}/bin/ar \
	${prefix}/${TARGET_SYS}/bin/strings \
	${prefix}/${TARGET_SYS}/bin/objdump \
	${prefix}/${TARGET_SYS}/bin/size \
	${includedir}/*.h \
	${libdir}/ldscripts/* \
	${libdir}/libiberty.a \
	${bindir}/${TARGET_PREFIX}ld \
	${bindir}/${TARGET_PREFIX}addr2line \
	${bindir}/${TARGET_PREFIX}objcopy \
	${bindir}/${TARGET_PREFIX}readelf \
	${bindir}/${TARGET_PREFIX}strip \
	${bindir}/${TARGET_PREFIX}nm \
	${bindir}/${TARGET_PREFIX}ranlib \
	${bindir}/${TARGET_PREFIX}gprof \
	${bindir}/${TARGET_PREFIX}as \
	${bindir}/${TARGET_PREFIX}c++filt \
	${bindir}/${TARGET_PREFIX}ar \
	${bindir}/${TARGET_PREFIX}strings \
	${bindir}/${TARGET_PREFIX}objdump \
	${bindir}/${TARGET_PREFIX}size \
	${prefix}/${HOST_SYS}/* \
"

DESCRIPTION_gcc-cross-sdk = "The GNU cc and gcc C compilers"
DESCRIPTION_binutils-cross-sdk = "A GNU collection of binary utilities"

LICENSE = "${ARG_LIC_LIBC}"
LICENSE_gcc-cross-sdk = "${ARG_LIC_GCC}"
LICENSE_binutils-cross-sdk = "${ARG_LIC_BFD}"

PKGV = "${ARG_VER_MAIN}"
PKGV_gcc-cross-sdk = "${ARG_VER_GCC}"
PKGV_binutils-cross-sdk = "${ARG_VER_BFD}"

do_install() {
	install -d ${D}${prefix}/${TARGET_SYS}/bin
	install -d ${D}${prefix}/${TARGET_SYS}/lib
	install -d ${D}${bindir}
	install -d ${D}${libdir}
	install -d ${D}${libdir}/ldscripts
	install -d ${D}${includedir}
	install -d ${D}${libexecdir}
	install -d ${D}${gcclibdir}/${TARGET_SYS}/${ARG_VER_GCC}/include
	install -d ${D}${prefix}/${HOST_SYS}

	cp -a ${TOOLCHAIN_PATH}/${TARGET_SYS}/bin/{cpp,cc,gccbug,g++,gcov,gcc} ${D}${prefix}/${TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${TARGET_SYS}/lib/{libstdc++.*,libssp*,libgcc_s.*,libsupc++.*} ${D}${prefix}/${TARGET_SYS}/lib
	cp -a ${TOOLCHAIN_PATH}/lib/gcc/${TARGET_SYS}/${ARG_VER_GCC}/* ${D}${gcclibdir}/${TARGET_SYS}/${ARG_VER_GCC}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}{gcov,gccbug,gcc,g++,cpp} ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/libexec/* ${D}${libexecdir}

	cp -a ${TOOLCHAIN_PATH}/${TARGET_SYS}/bin/{ld,addr2line,objcopy,readelf,strip,nm,ranlib,gprof,as,c++filt,ar,strings,objdump,size} ${D}${prefix}/${TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/include/*.h ${D}${includedir}
	cp -a ${TOOLCHAIN_PATH}/lib/ldscripts/* ${D}${libdir}/ldscripts
	cp -a ${TOOLCHAIN_PATH}/lib/libiberty.a ${D}${libdir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}{ld,addr2line,objcopy,readelf,strip,nm,ranlib,gprof,as,c++filt,ar,strings,objdump,size} ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/${HOST_SYS}/* ${D}${prefix}/${HOST_SYS}
}

NATIVE_INSTALL_WORKS = "1"
