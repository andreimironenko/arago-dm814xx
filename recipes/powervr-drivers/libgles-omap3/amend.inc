# Changes to powervr.ini to support both SGX/OpenGL ES2 and Qt demos.
# Right now, the user has to manually edit the /etc/powervr.ini at
# run time to switch between SGX and Qt

# The current version of the Graphics SDK does not provide this library.
# using this library results in the 3D demos being unable to execute.
# LIBGLESWINDOWSYSTEM = "libpvrQWSWSEGL.so.1"

PR_append = "-arago1"
