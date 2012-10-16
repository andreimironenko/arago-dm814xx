require recipes/qt4/qt4-embedded.inc
PR = "${INC_PR}.14"

PROVIDES += "qt4-embedded"
RPROVIDES_${PN} += "qt4-embedded"
RPROVIDES_${PN}-dev += "qt4-embedded-dev"

QT_GLFLAGS = "-opengl es2 -depths 16,24,32 -plugin-gfx-powervr -D QT_QWS_CLIENTBLIT"

require recipes/qt4/qt-${PV}.inc

SRC_URI += "file://cursor-hack.diff \
            file://0001-wsegl2-support.patch \
"
SRC_URI_append_c6a811x-evm = " \
            file://0001-bitblt-add-support-for-bitblt-screen-driver.patch \
"

FILESPATHBASE .= ":${OEBASE}/arago-oe-dev/recipes/qt4"
FILESPATHPKG .= ":qt4-embedded-${PV}:qt4-embedded"

DEPENDS += "virtual/egl"
require recipes/egl/egl.inc

QT_CONFIG_FLAGS += " \
 -exceptions \
"

PACKAGES += " \
${QT_BASE_NAME}-qmlimports-folderlistmodel \
${QT_BASE_NAME}-qmlimports-folderlistmodel-dbg \
${QT_BASE_NAME}-qmlimports-gestures \
${QT_BASE_NAME}-qmlimports-gestures-dbg \
${QT_BASE_NAME}-qmlimports-particles \
${QT_BASE_NAME}-qmlimports-particles-dbg \
${QT_BASE_NAME}-qmlimports-webkit \
${QT_BASE_NAME}-qmlimports-webkit-dbg \
"

FILES_${QT_BASE_NAME}-qmlimports-folderlistmodel-dbg = "/usr/imports/Qt/labs/folderlistmodel/.debug/*"
FILES_${QT_BASE_NAME}-qmlimports-folderlistmodel = "/usr/imports/Qt/labs/folderlistmodel/*"
FILES_${QT_BASE_NAME}-qmlimports-gestures-dbg = "/usr/imports/Qt/labs/gestures/.debug/*"
FILES_${QT_BASE_NAME}-qmlimports-gestures = "/usr/imports/Qt/labs/gestures/*"
FILES_${QT_BASE_NAME}-qmlimports-particles-dbg = "/usr/imports/Qt/labs/particles/.debug/*"
FILES_${QT_BASE_NAME}-qmlimports-particles = "/usr/imports/Qt/labs/particles/*"
FILES_${QT_BASE_NAME}-qmlimports-webkit-dbg = "/usr/imports/QtWebKit/.debug/*"
FILES_${QT_BASE_NAME}-qmlimports-webkit = "/usr/imports/QtWebKit/*"
