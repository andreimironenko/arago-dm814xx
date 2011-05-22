# Changes to powervr.ini to support both SGX/OpenGL ES2 and Qt demos.
# Right now, the user has to manually edit the /etc/powervr.ini at
# run time to switch between SGX and Qt

LIBGLESWINDOWSYSTEM = "libpvrQWSWSEGL.so.1"
