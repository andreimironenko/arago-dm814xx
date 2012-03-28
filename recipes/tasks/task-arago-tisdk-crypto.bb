# NOTE: This task is used to group applications for the SDK
#       that require filing for a TSU exemption or applications
#       that depend on TSU exempt code.
DESCRIPTION = "Task to install crypto packages into target FS"
PR = "r23"
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

# Add crypto hardware support for am37x-evm
# NOTE: this package depends on a kernel patch which also has a TSU
#       exemption filed.  The current name and checksums of the
#       kernel patch are:
# Name: 0001-linux-omap3-PSP-3.0.1.6-kernel-with-OCF-Linux.patch
# md5sum: 00bb20f2f33a37489d8c52212933368d
# sha256sum: 1fea8323d12cf1ee4f743f0f1c82d7f3821a7d9b7996c44b17d7761579bb090d
CRYPTO_SUPPORT_TSU_append_am37x-evm = " ti-ocf-crypto-module"
CRYPTO_SUPPORT_TSU_append_am3517-evm = " ti-ocf-crypto-module"
################################################################################


# Default CRYPTO_SUPPORT value to build in all TSU elements
# Add openssl-misc to get the openssl.cnf file which is
# needed for "openssl req" and to avoid warnings.
# NOTE: This may change to openssl-conf in the future
CRYPTO_SUPPORT = "\
    ${CRYPTO_SUPPORT_TSU} \
    openssl-misc \
    "

# WLAN support packages.  These are added here because they depend on
# crypto packages and are grouped with the crypto task to avoid confusion.

# These are the packages that all platforms use for WLAN support
WLAN_COMMON = "hostap-daemon \
               ti-wifi-utils \
               wireless-tools \
               htop \
               netperf \
               iw \
               linux-firmware-wl12xx \
               wpa-gui-e \
               crda \
               softap-udhcpd-config \
              "

BLUETOOTH = ""
BLUETOOTH_am37x-evm = "bluetooth-gui"
BLUETOOTH_omapl138 = "bluetooth-gui"
BLUETOOTH_ti33x = "bluetooth-gui"

# These are the WLAN packages that are used for compatibility with the
# 2.6.37 kernel used by some devices
WLAN_COMPAT = "ti-compat-wireless-wl12xx \
              "

# Base WLAN value is blank set
WLAN = ""
WLAN_am180x-evm = "${WLAN_COMMON} ${WLAN_COMPAT}"
WLAN_am37x-evm = "${WLAN_COMMON} ${WLAN_COMPAT}"
WLAN_beagleboard = "${WLAN_COMMON}"
WLAN_am335x-evm = "${WLAN_COMMON} ${WLAN_COMPAT}"
WLAN_dm814x-evm = "${WLAN_COMMON} ${WLAN_COMPAT}"

RDEPENDS_${PN} = "\
    ${CRYPTO_SUPPORT} \
    ${WLAN} \
    ${BLUETOOTH} \
    "
