DESCRIPTION = "Task to install crypto packages into target FS"
PR = "r0"
LICENSE = "MIT"

inherit task

WLAN_SUPPORT = "\
    openssl \
    wpa-supplicant \
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
