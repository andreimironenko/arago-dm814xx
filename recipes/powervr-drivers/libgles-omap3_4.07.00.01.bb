# untested and huge change from 1.4.x
# DEFAULT_PREFERENCE = "-1"

COMPATIBLE_MACHINE = "ti811x"

BINLOCATION_omap3  = "${S}/gfx_rel_es3.x"
BINLOCATION_ti816x = "${S}/gfx_rel_es6.x"
BINLOCATION_ti814x = "${S}/gfx_rel_es6.x"
BINLOCATION_ti811x = "${S}/gfx_rel_es6.x"
BINLOCATION_ti33x = "${S}/gfx_rel_es8.x"

#ES2LOCATION = "${S}/gfx_rel_es2.x"
#ES3LOCATION = "${S}/gfx_rel_es3.x"
#ES5LOCATION = "${S}/gfx_rel_es5.x"
ES6LOCATION = "${S}/gfx_rel_es6.x"
#ES8LOCATION = "${S}/gfx_rel_es8.x"

PLATFORM = "LinuxARMV7"

require libgles-omap3.inc
PACKAGE_STRIP = "no"

# download required binary distribution from:
# http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/latest/index_FDS.html
# see libgles-omap3.inc for detailed installation instructions

SGXPV = "4_07_00_01"
IMGPV = "1.7.86.7897"
BINFILE := "Graphics_SDK_setuplinux_${SGXPV}.bin"

SRC_URI = "http://bangsdowebsvr01.india.ti.com:8090/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin \
                   file://cputype \
                   file://rc.pvr \
                   file://sample.desktop \
                   file://99-bufferclass.rules  \
		           file://0001-Added-sdk-make-install-file-to-graphics-sdk.patch \
"

SRC_URI[md5sum] = "4ee74adf84fc31298700e21acab6531e"
SRC_URI[sha256sum] = "9b65d329d80afe41519c3f70822d8bbece4b64430483ff758a366705b2d97ca4"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}"

do_install () {
	install -d ${D}${libdir}
	cp -pPR ${BINLOCATION}/*.so* ${D}${libdir}
	install -m 0644 ${BINLOCATION}/*.a ${D}${libdir}

	install -d ${D}${bindir}/
	install -m 0755 ${WORKDIR}/cputype ${D}${bindir}/

	install -m 0755 ${BINLOCATION}/*_test ${D}${bindir}/
	install -m 0755 ${BINLOCATION}/gl* ${D}${bindir}/
	install -m 0755 ${BINLOCATION}/p[dv]* ${D}${bindir}/
	install -m 0755 ${BINLOCATION}/xgles1test1 ${D}${bindir}/

	install -d ${D}${includedir}
	cp -pPR ${S}/GFX_Linux_KM/include4 ${D}${includedir}/
	cp -pPR ${S}/GFX_Linux_KM/services4 ${D}${includedir}/

	cp -pPR ${S}/GFX_Linux_SDK/OGLES2/SDKPackage/Builds/OGLES2/Include/* ${D}${includedir}/
	cp -pPR ${S}/GFX_Linux_SDK/OGLES/SDKPackage/Builds/OGLES/Include/* ${D}${includedir}/
	cp -pPr ${S}/GFX_Linux_SDK/OVG/SDKPackage/Builds/OVG/Include/V* ${D}${includedir}/ || true
	cp -pPr ${S}/include/wsegl/*.h ${D}${includedir} || true
	cp -pPr ${S}/include/pvr2d/*.h ${D}${includedir} || true
	cp -pPr ${S}/include/OGLES/GLES ${D}${includedir}/ || true

	install -d ${D}${sysconfdir}/init.d/
	if [ "$SUPPORT_XORG" = "0" ]; then
	cp -pP ${WORKDIR}/rc.pvr ${D}${sysconfdir}/init.d/pvr-init
	fi
	if [ "$SUPPORT_XORG" = "1" ]; then
	cp -pPR ${S}/targetfs/XSGX ${D}/usr/local
	cp -pP ${WORKDIR}/rc_dri.pvr ${D}${sysconfdir}/init.d/pvr-init
	fi

	install -d ${D}${sysconfdir}
	echo "[default]" > ${D}${sysconfdir}/powervr.ini
	echo "WindowSystem=${LIBGLESWINDOWSYSTEM}" >> ${D}${sysconfdir}/powervr.ini

	# The ES2.x, ES3.x, ES5.x and ES6.x CPUs have different SGX hardware, so we need to install multiple sets of userspace

	install -d ${D}${libdir}/ES6.0
	install -d ${D}${libdir}/ES5.0
	install -d ${D}${libdir}/ES3.0
	install -d ${D}${libdir}/ES2.0
	install -d ${D}${libdir}/ES8.0

	install -d ${D}${bindir}/ES6.0
	install -d ${D}${bindir}/ES5.0
	install -d ${D}${bindir}/ES3.0
	install -d ${D}${bindir}/ES2.0
	install -d ${D}${bindir}/ES8.0


	if [ -e ${ES6LOCATION} ] ; then
		cp -pPR ${ES6LOCATION}/lib*${IMGPV} ${D}${libdir}/ES6.0/
		cp ${ES6LOCATION}/p[dv]* ${D}${bindir}/ES6.0/
	fi

	rm ${D}${bindir}/ES*/*.h ${D}${bindir}/ES*/pdsasm ${D}${bindir}/pdsasm -f || true

	install -d ${D}${prefix}/share/applications
	cp ${WORKDIR}/*.desktop ${D}${prefix}/share/applications
	rm ${D}${prefix}/share/applications/sample.desktop

	install -d ${D}${bindir}/SGX/demos/X11/
	install -d ${D}${bindir}/SGX/demos/Raw/
	install -m 0755 ${S}/demos/x11/* ${D}${bindir}/SGX/demos/X11/
	install -m 0755 ${S}/demos/raw/* ${D}${bindir}/SGX/demos/Raw/

	install -d ${D}${bindir}/SGX/trainingcourses/Raw
	install -d ${D}${bindir}/SGX/trainingcourses/X11
	install -m 0755 ${S}/trainingcourses/x11/* ${D}${bindir}/SGX/trainingcourses/X11/
	install -m 0755 ${S}/trainingcourses/raw/* ${D}${bindir}/SGX/trainingcourses/Raw/

	# Delete objects and linker scripts hidden between the headers
	find ${D} -name "*.o" -delete
	find ${D} -name "*.o.cmd" -delete

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/99-bufferclass.rules ${D}${sysconfdir}/udev/rules.d/

}
