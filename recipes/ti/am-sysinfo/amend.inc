# Add changes for matrix-gui v2
PR_append = "-arago3"

SRC_URI += "https://gforge.ti.com/gf/download/frsrelease/684/5107/settingsdemo_1.2.tar.gz"

MATRIX_FILES_DIR = "${WORKDIR}/settings"
require recipes/matrix/matrix-gui-apps.inc

# Make sure the settings submenu has been installed
RDEPENDS += matrix-gui-submenus-settings

# Add the matrix directory to the list of FILES
# Used am-sysinfo because ${PN} was not resolving in time
FILES_am-sysinfo += "${MATRIX_BASE_DIR}/*"

SRC_URI[md5sum] = "6410c89bc88b24d190435ff08bc14ef8"
SRC_URI[sha256sum] = "c643c447c717ce93b8dfb080e8345af37eb47ee6c241d8303283be642d321886"

