DESCRIPTION = "Audio Head Unit Sample demo app"
LICENSE = "TI Proprietary"

require ti-paths.inc
require ti-staging.inc

COMPATIBLE_MACHINE = "ti811x"

SRC_URI = "http://install.source.dir.local/ahudemo-1.00.00.02.tar.gz"

SRC_URI[md5sum] = "e25bff7c64031d5c5eb2f8f42c51bb3f"
SRC_URI[sha256sum] = "7f4b188253742453dc10a43c3dab3e4edb73c1a299a8e36e31b3f507481d70bd"

S = ${WORKDIR}/ahudemo-1.00.00.02

do_compile() {
}

