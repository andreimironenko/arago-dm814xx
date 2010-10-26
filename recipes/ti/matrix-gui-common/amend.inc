# Integra amend

SRC_URI += "\
          file://signal-analyzer-icon.png \
          file://menu_dsp.html \
          file://menu_main.html \
"


do_install_append_c6a816x-evm () {
    install -m 0644 ${WORKDIR}/*.html ${D}/${datadir}/matrix/html
    install -m 0644 ${WORKDIR}/*.png ${D}/${datadir}/matrix/images/
}

# Set the suffix to specify amended packages
PR_append = "-arago1"
