require ti-xdais.inc

PV = "7_21_00_02"

SRC_URI[xdaistarball.md5sum] = "cb59ed36ff9bf5a36710ce03fee6bcd6"
SRC_URI[xdaistarball.sha256sum] = "560419048ec1061da6cdb8d3d38b80a98d2549425e1778d9e0c53a28297f0dfa"

SRC_URI += "file://0001-Added-sdk-make-install-file-to-xdais.patch \
"
