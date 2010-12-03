DESCRIPTION = "Task to install crypto packages into target FS"
PR = "r1"
LICENSE = "MIT"

inherit task

WLAN_SUPPORT = "\
    openssl \
    wpa-supplicant \
    wpa-gui-e \
    "

# Packages required for Eclipse RSE plugin
RSE_SUPPORT = "\
    dropbear \
    openssh-sftp-server \
    "

RDEPENDS_${PN} = "\
    ${WLAN_SUPPORT} \
    ${RSE_SUPPORT} \
    "
