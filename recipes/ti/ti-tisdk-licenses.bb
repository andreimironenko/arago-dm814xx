DESCRIPTION = "Package containing license text for Licenses found in SDK manifests"
LICENSE = "TI"

PR = "r2"

# Generic license files.  Will be packaged in source ipk.
# Licenses specific to a package are handled by the license.bbclass
SRC_URI = "\
    file://ti-tisdk-licenses/APACHEv2.txt \
    file://ti-tisdk-licenses/ECLIPSEv1.txt \
    file://ti-tisdk-licenses/GPLv2.txt \
    file://ti-tisdk-licenses/GPLv3.txt \
    file://ti-tisdk-licenses/LGPLv2.1.txt \
    file://ti-tisdk-licenses/LGPLv3.txt \
    file://ti-tisdk-licenses/MPLv1.1.txt \
    file://ti-tisdk-licenses/TI_TSPA.txt \
    file://ti-tisdk-licenses/BSD.txt \
    file://ti-tisdk-licenses/MIT.txt \
    file://ti-tisdk-licenses/creativecommons.txt \
    file://ti-tisdk-licenses/openssl.txt \
    file://ti-tisdk-licenses/CC-BY-NC-ND-3.0.txt \
"

# Make an empty do_patch function so that the "patches" directory is not
# added to the sources directory.
do_patch() {
}

S = "${WORKDIR}/ti-tisdk-licenses"
