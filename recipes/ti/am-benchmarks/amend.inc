# NOTE:  This amend of the recipe is used only in Arago because the
#        version of the CodeSourcery toolchain (2009q1) currently
#        used by Arago does not yield ideal performance, whereas the
#        version used to build the Dhrystone binary (2008q1) does.
#        This issue does not affect the OE mainline which does not
#        use the CodeSourcery toolchain.

# Add the pre-built statically linked version of dhrystone
SRC_URI_append = " https://gforge.ti.com/gf/download/frsrelease/406/3998/dhrystone-static.tar.gz"

SRC_URI[md5sum] = "0b39552d41ba469adfcc92acd7b763d1"
SRC_URI[sha256sum] = "5d57687eeffd1ea9ce0a098904fa25adc4cef3f842910ed4ba5fb2f8806e536c"

# Have do_install function install the static version of dhrystone as well
do_install_append() {
    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/${ARCHITECTURE}/dhrystone_${ARCHITECTURE}_static ${D}/${bindir}/dhrystone_static

}

# Set the suffix to specify amended packages
PR_append = "-arago1"

