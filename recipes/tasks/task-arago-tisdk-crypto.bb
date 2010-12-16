# NOTE: This task is used to group applications for the SDK
#       that require filing for a TSU exemption or applications
#       that depend on TSU exempt code.
DESCRIPTION = "Task to install crypto packages into target FS"
PR = "r4"
LICENSE = "MIT"

inherit task

PACKAGE_ARCH = ${MACHINE_ARCH}

################################################################################
######################### TSU EXEMPTION REQUIRED ###############################
################################################################################

# The following packages are the set of packages used for crypto
# support which require TSU exemption:
#   wpa-supplicant - Used by WLAN drivers
#   openssl - Used by WLAN and OCF crypto drivers
#   dropbear - Used for Eclipse RSE plugin
#   openssh-sftp-server - Used for Eclipse RSE plugin
CRYPTO_SUPPORT_TSU = "\
    openssl \
    wpa-supplicant \
    dropbear \
    openssh-sftp-server \
    "
################################################################################


# Default CRYPTO_SUPPORT value to build in all TSU elements
# Add openssl-misc to get the openssl.cnf file which is
# needed for "openssl req" and to avoid warnings.
# NOTE: This may change to openssl-conf in the future
CRYPTO_SUPPORT = "\
    ${CRYPTO_SUPPORT_TSU} \
    openssl-misc \
    "

# Add support for graphical configuration of wpa-supplicant
# used by WLAN drivers.
CRYPTO_SUPPORT_append_am180x-evm = " wpa-gui-e"

RDEPENDS_${PN} = "\
    ${CRYPTO_SUPPORT} \
    "
