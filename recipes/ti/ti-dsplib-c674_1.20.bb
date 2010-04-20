DESCRIPTION = "TI DSPLIB Optimised DSP Library for C674x devices"
HOMEPAGE = "http://focus.ti.com/docs/toolsw/folders/print/sprc906b.html"
SECTION = "multimedia"

# TODO : Add variants for 67x/674x/etc
# TODO : Add compile step to enable rebuild

PV = "1_20"

SRC_URI[dsplibgz.md5sum] = "cecbb20089843baf9393f2875f101767"
SRC_URI[dsplibgz.sha256sum] = "cb7d3a33f1b3de4b3d030095d2ea14211a0ed2bd6f66d2848ea323eb6bc7a649"

PR = "r1"

require ti-paths.inc
require ti-staging.inc
require ti-eula-unpack.inc

S = "${WORKDIR}/c674x/dsplib_v12"

SRC_URI = "http://focus.ti.com/lit/sw/sprc906b/sprc906b.gz;name=dsplibgz"

#Later this will have dependencies when we rebuild the libraries/examples
#DEPENDS = "ti-cgt6x ti-xdctools ti-dspbios ti-codec-engine" 

PRETARFILE="sprc906b"
BINFILE="c674x_dsplib_v12-Linux-x86-Install"
TI_BIN_UNPK_CMDS="Y:workdir:"

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_pretar_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}

python ti_pretar_do_unpack() {

    import os

    localdata = bb.data.createCopy(d)
    bb.data.update_data(localdata)

    # Change to the working directory
    save_cwd = os.getcwd()
    workdir  = bb.data.getVar('WORKDIR', localdata)
    workdir  = bb.data.expand(workdir, localdata)
    os.chdir(workdir)

    # Expand the tarball that was created if required
    tarfile  = bb.data.getVar('PRETARFILE', localdata)    
    if bool(tarfile) == True:
        tarfile  = bb.data.expand(tarfile, localdata)
        tcmd = 'tar x --no-same-owner -f %s -C %s' % (tarfile, workdir)
        os.system(tcmd)

    # Return to the previous directory
    os.chdir(save_cwd)
}

do_prepsources() {
    echo "Do Nothing for Now" 
}

addtask prepsources after do_configure before do_compile

do_compile() {
    echo "Do Nothing for Now"
}

do_install() {

    install -d ${D}${DSPLIB_C674_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${DSPLIB_C674_INSTALL_DIR_RECIPE}
}





