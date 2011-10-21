require recipes/qt4/qt4-embedded.inc
PR = "${INC_PR}.10"

PROVIDES += "qt4-embedded"
RPROVIDES_${PN} += "qt4-embedded"
RPROVIDES_${PN}-dev += "qt4-embedded-dev"

QT_GLFLAGS = "-opengl es2 -depths 16,24,32 -plugin-gfx-powervr"

require recipes/qt4/qt-${PV}.inc

SRC_URI += "file://cursor-hack.diff"

FILESPATHBASE .= ":${OEBASE}/arago-oe-dev/recipes/qt4"
FILESPATHPKG .= ":qt4-embedded-${PV}:qt4-embedded"

DEPENDS += "virtual/egl"
require recipes/egl/egl.inc
