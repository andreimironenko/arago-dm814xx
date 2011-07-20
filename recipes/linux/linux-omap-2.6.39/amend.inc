#Arago specific amendment that brings in the AMSDK defconfig for beagleboard
#and the functionality to save the defconfig file

require recipes/linux/copy-defconfig.inc

PR_append = "-arago2"
