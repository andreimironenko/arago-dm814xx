DESCRIPTION = "Script for generating a pdf from a TI wiki topic"
LICENSE = "MIT"
PR = "r1"

PV = "1.0"

inherit native

SRC_URI = "file://post-process-tiwiki.pl"

do_stage () {
    cp ${WORKDIR}/post-process-tiwiki.pl ${STAGING_DIR}/${BUILD_SYS}/usr/bin
}
