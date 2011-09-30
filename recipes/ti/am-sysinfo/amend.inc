# Add changes for matrix-gui v2
PR_append = "-arago1"

SRC_URI += "https://gforge.ti.com/gf/download/frsrelease/614/5051/settings_1.0.tar.gz"

MATRIX_FILES_DIR = "${WORKDIR}/settings"
require recipes/matrix/matrix-gui-apps.inc

# Make sure the settings submenu has been installed
RDEPENDS += matrix-gui-submenus-settings

# Add the matrix directory to the list of FILES
# Used am-sysinfo because ${PN} was not resolving in time
FILES_am-sysinfo += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "a515470463c36f3dd6d418b06f7bec31"
SRC_URI[sha256sum] = "9aaf362a58f97df50aeabbb28956b2ba11f4d114584eb39a3ad15addeec03e41"

