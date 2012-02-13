# This is a TI specific version of the compat-wireless recipe using a
# compat-wireless package created from the TI Systems Tested mac80211 releases.
#
# This approach has been taken to simplify the packaging of a compat-wireless
# release for WL12xx on the PSP/SDK from TI.
#
# This approach avoids the need to take the latest compat-wireless package
# from public sources and patching this onto the SDK along with additional
# patches that are currently being up-streamed.
# These later patches are essential to address defects that have been found
# during systems testing. This approach avoids the need to apply many
# patches (100âs) which is unmanageable.
 
include ti-compat-wireless.inc

MACHINE_KERNEL_PR_append = "a"

COMPAT_WIRELESS_VERSION = "2011-12-20-r4-12_01"
S = "${WORKDIR}/compat-wireless"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/768/5331/ti-compat-wireless-wl12xx-r4-12-12-20.tar.gz \
           file://0004-added-driver-version.patch \
           file://0005-wl12xx-don-t-allow-scans-in-AP-mode.patch \
           file://0006-wl12xx-handle-idle-changes-per-interface.patch \
           file://0007-wl12xx-prevent-double-idle-on-off-transitions.patch \
           file://0008-wl12xx-remove-warning-message-during-IBSS-Tx.patch \
           file://0012-mac80211-set-upasd-queues-and-max-sp-only-on-sta-addition.patch \
           file://0013-wl12xx-Make-sure-HW-is-available-in-sched-scan-ops.patch \
          "
 
SRC_URI_append_am180x-evm = "file://0009-wl12xx-Decrease-number-of-RX-transactions.patch \
                             file://0010-wl12xx-Decrease-number-of-TX-transactions.patch \
                             file://0011-ti-compat-wireless-wl12xx-activate-dynamic-memory-al.patch \
                            "
 
SRC_URI[md5sum] = "89fa40e0ece490cf73920a71df1c3558"
SRC_URI[sha256sum] = "752b53b09e9ae168a4a7f32ad009bb2b8b245c152f65ace1edc51a1ef8bba59f"
