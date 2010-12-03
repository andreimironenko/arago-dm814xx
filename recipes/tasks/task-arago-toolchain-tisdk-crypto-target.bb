DESCRIPTION = "Task to install crypto dev packages in SDK"
PR = "r0"
LICENSE = "MIT"

inherit task

WLAN_SUPPORT = "\
    openssl-dev \
    wpa-supplicant-dev \
    "

RDEPENDS_${PN} = "\
    ${WLAN_SUPPORT} \
    "
