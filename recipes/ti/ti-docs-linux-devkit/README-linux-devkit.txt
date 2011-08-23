LINUX DEVKIT
============

This is a development kit for Linux applications in the EZSDK. For more
information read the "Using the devkits" section of the Software Developer's
Guide.

Content
=======

The top level directory of the linux-devkit contains host tools. It also
contains a script "environment-setup.sh" which should be sourced before using
the linux-devkit like:

$ . environment-setup

This will set up your environment and paths for Linux development.

The "arm-none-linux-gnueabi" contains the target software. The TI software
is installed under arm-none-linux-gnueabi/usr/include and
arm-none-linux-gnueabi/usr/lib by default.

The TI component documentation is available from
arm-none-linux-gnueabi/usr/share/ti/.

The Linux kernel PSP headers are installed under arm-none-linux-gnueabi/usr/src.

Repopulation
============

The linux-devkit does not contain any build files, you will need to rebuild
the software from the EZSDK top level directory. Enter the EZSDK top level
directory and execute 'make help' to see a list of available build targets.

Then execute the following to repopulate the linux-devkit with TI components:

$ make clean
$ make components_linux
$ make linux-devkit

Relocation
==========

This devkit can be relocated to another directory on your file system. You
will need to do the following after relocating the linux-devkit:

1) Edit environment-setup and change the first (SDK_PATH) variable to point
to your new location.

2) Source the environment-setup script as per above to pick up the new path.
