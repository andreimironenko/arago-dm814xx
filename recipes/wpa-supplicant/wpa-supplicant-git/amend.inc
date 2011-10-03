# Adding patches from TI nlcp r&d which are part of R4-10 release
# These patches are pending for upstream 

PR_append = "-arago1"

SRC_URI_append = " \
    file://0001-hostapd-set-sta-flags-when-adding-a-new-station.patch;patchdir=.. \
    file://0002-nl80211-consider-p2p-when-changing-vif-type.patch;patchdir=.. \
    file://0003-nl80211-change-vif-type-to-P2P_CLI-upon-p2p-authenti.patch;patchdir=.. \
    file://0004-wpa_supplicant-create-symlink-to-src-inside-wpa_supp.patch;patchdir=.. \
    file://0005-wpa_supplicant-p2p-android-.config-file-and-Android..patch;patchdir=.. \
    file://0006-wpa_ctrl-added-socket-cleanup-function-for-Android.patch;patchdir=.. \
    file://0007-driver_nl80211-added-private-driver-commands-for-And.patch;patchdir=.. \
    file://0008-cli-ctrl_iface-added-blacklist-and-log_level-command.patch;patchdir=.. \
    file://0009-wpa_supplicant-made-a-few-scan-tweaks-for-Android.patch;patchdir=.. \
    file://0010-wpa_supplicant-makefile-changes-for-wpa_supplicant-u.patch;patchdir=.. \
    file://0011-wpa_supplicant-enabld-the-WPS2-configuration.patch;patchdir=.. \
    file://0012-nl80211-send-sta-flags-to-kernel-on-station-addition.patch;patchdir=.. \
    file://0013-hostapd-modifications-for-clean-android-compilation.patch;patchdir=.. \
    file://0014-add-android_hostapd.conf-file.patch;patchdir=.. \
    file://0015-hostapd-add-android-11a-hostapd.conf-file-and-enable.patch;patchdir=.. \
    file://0016-hostapd-add-uapsd_queues-and-max_sp-fields.patch;patchdir=.. \
    file://0017-nl80211-send-uapsd_queues-and-max_sp-values-on-sta-a.patch;patchdir=.. \
    file://0018-hostap-makefile-and-configuration-modifications-for-.patch;patchdir=.. \
    file://0019-driver_nl80211-implement-hapd_set_ssid-callback.patch;patchdir=.. \
    file://0020-wpa_supplicant-correct-a-typo.patch;patchdir=.. \
    file://0021-hostapd-add-support-for-android-created-sockets-dev-.patch;patchdir=.. \
    file://0022-hostapd-change-hostapd.conf-files-to-work-with-andro.patch;patchdir=.. \
    file://0023-wpa_supplicant-prevent-connection-attempt-while-unfi.patch;patchdir=.. \
    file://0024-wpa_supplicant-fix-call-to-preauth-candidate-list-ge.patch;patchdir=.. \
    file://0025-hostapd.conf-change-the-max-number-of-connected-stat.patch;patchdir=.. \
    file://0026-hostapd.conf-support-one-stream-of-RX-STBC-for-AP-mo.patch;patchdir=.. \
    file://0027-wpa_supplicant-config-add-support-for-11n-and-remove.patch;patchdir=.. \
    file://0028-wpa_supplicant-add-hard-coded-HT-capabilities-for-P2.patch;patchdir=.. \
    file://0029-wpa_s-AP-mode-Enable-HT20-if-driver-supports-it.patch;patchdir=.. \
    file://0030-hostap-set-ht_capab-from-wiphy-when-in-P2P-GO-mode.patch;patchdir=.. \
    file://0031-If-uapsd-is-explicitly-enabled-set-conf-bss-wmm_enab.patch;patchdir=.. \
    file://0032-backport-P2P-Do-not-reply-to-Probe-Request-frame-ind.patch;patchdir=.. \
    "
