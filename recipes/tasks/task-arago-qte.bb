DESCRIPTION = "Task to add Qt/Embedded and basic plugins"
LICENSE = "MIT"
PR = "r8"

inherit task

RDEPENDS_${PN} = "\
    qt4-embedded \
    qt4-embedded-plugin-mousedriver-tslib \
    qt4-embedded-plugin-gfxdriver-gfxtransformed \
    qt4-embedded-plugin-gfxdriver-screenbitblt \
    ${@base_conditional('ARAGO_QT_PROVIDER', 'qt4-embedded-gles', 'qt4-embedded-plugin-gfxdriver-gfxpvregl', '', d)} \
    "
