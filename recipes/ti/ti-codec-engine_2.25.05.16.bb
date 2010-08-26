require ti-codec-engine.inc

PV = "2_25_05_16"

SRC_URI[cetarball.md5sum] = "fbc1346b5f0ed8d6667b8509d57faa80" 
SRC_URI[cetarball.sha256sum] = "72062186b07fe903e11788e3432df604f93e75f6ed68ab4b1af4b24512d449fa" 

# update 3530 loadmodule to use mem=99 
do_configure_dm37x-evm_prepend () {
    sed -i  \
        -e s:"phys_start=0x85000000 phys_end=0x86000000":"phys_start=0x86300000 phys_end=0x87200000":g \
        ${S}/examples/apps/system_files/${CEEXAMPLESDEVICES}/loadmodules.sh

}

