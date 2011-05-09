DESCRIPTION = "Task to install crypto dev packages in SDK"
PR = "r3"
LICENSE = "MIT"

inherit task

PACKAGE_ARCH = ${MACHINE_ARCH}

################################################################################
######################### TSU EXEMPTION REQUIRED ###############################
################################################################################
CRYPTO_SUPPORT_TSU = "\
    openssl-dev \
    wpa-supplicant-dev \
# Comment out OCF until we have an implementation for 2.6.37
#    ocf-linux-dev \
    "
################################################################################

# Default CRYPTO_SUPPORT value to build in all TSU elements
CRYPTO_SUPPORT = "${CRYPTO_SUPPORT_TSU}"

RDEPENDS_${PN} = "\
    ${CRYPTO_SUPPORT} \
    "
