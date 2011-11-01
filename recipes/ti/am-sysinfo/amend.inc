# Add changes for matrix-gui v2
#PR_append = "-arago4"
#
#SRC_URI += "https://gforge.ti.com/gf/download/frsrelease/687/5113/settingsdemo_1.3.tar.gz"
#
#MATRIX_FILES_DIR = "${WORKDIR}/settings"
#require recipes/matrix/matrix-gui-apps.inc
#
## Make sure the settings submenu has been installed
#RDEPENDS += matrix-gui-submenus-settings
#
## Add the matrix directory to the list of FILES
## Used am-sysinfo because ${PN} was not resolving in time
#FILES_am-sysinfo += "${MATRIX_BASE_DIR}/*"
#
#SRC_URI[md5sum] = "3840b471fde0b8611b9f8d5af3ec14a5"
#SRC_URI[sha256sum] = "495e0f6ea91c75c5f15b43638b32b50f84822ef72f8e5974733393dfe61e32ef"
#
