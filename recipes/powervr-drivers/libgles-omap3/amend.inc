# Changes to powervr.ini to support both SGX/OpenGL ES2 and Qt demos.
# Right now, the user has to manually edit the /etc/powervr.ini at
# run time to switch between SGX and Qt

# If Qt is built with OpenGLES, set the window system library to
# libpvrQWSWSEGL.so.1. Otherwise use the default
WS_LIBRARY := "${@base_conditional('ARAGO_QT_PROVIDER', 'qt4-embedded-gles', 'libpvrQWSWSEGL.so.1', '${LIBGLESWINDOWSYSTEM}', d)}"

LIBGLESWINDOWSYSTEM_armv7a = "${WS_LIBRARY}"

PR_append = "-arago2"
