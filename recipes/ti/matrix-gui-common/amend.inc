# Integra amend

SRC_URI += "\
          file://signal-analyzer-icon.png \
          file://menu_dsp.html \
          file://menu_main.html \
          file://matrix.css \
"


do_install_append_ti816x () {
    install -m 0644 ${WORKDIR}/*.html ${D}/${datadir}/matrix/html
    install -m 0644 ${WORKDIR}/*.css ${D}/${datadir}/matrix/html
    install -m 0644 ${WORKDIR}/*.png ${D}/${datadir}/matrix/images/
}

# Set the suffix to specify amended packages
PR_append = "-arago2"
