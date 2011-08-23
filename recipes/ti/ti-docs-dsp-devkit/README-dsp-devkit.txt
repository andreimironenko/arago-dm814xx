DSP DEVKIT
==========

This is a development kit for DSP applications in the EZSDK. For more
information read the "Using the devkits" section of the Software Developer's
Guide.

Content
=======

The top level directory of the dsp-devkit contains a script
"environment-setup.sh" which should be sourced before using
the dsp-devkit from the command line like:

$ . environment-setup

This will set up your XDCPATH and adds the DSP host tools (compiler and xdc)
to your PATH variable for DSP development.

The include/ directory contains include files for non-RTSC products, and the
lib/ directory contains the corresponding libraries.

The packages/ directory contains the RTSC packages for use with the XDC tools.

The doc/ directory contains documentation for the components installed in the
dsp-devkit.

Repopulation
============

The dsp-devkit does not contain any build files, you will need to rebuild
the software from the EZSDK top level directory. Enter the EZSDK top level
directory and execute 'make help' to see a list of available build targets.

Then execute the following to repopulate the dsp-devkit: 

$ make clean
$ make components_dsp
$ make dsp-devkit

Relocation
==========

This devkit can be relocated to another directory on your file system. You
will need to do the following after relocating the dsp-devkit:

1) Edit environment-setup and change the first (DSP_DEVKIT_PATH) variable to
point to your new location.

2) Source the environment-setup script as per above to pick up the new path.
