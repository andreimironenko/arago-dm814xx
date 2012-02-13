# NLCP supports both station and AP modes.
# don't start hostapd automatically when the system boot up.

PR_append = "-arago5"

# The default hostapd.conf file that is fetched from upstream is using
# "a" band which is not supported by the 1271 chip.
# modify it to use "g" band and default channel 6 and change the ssid from 
# "test" to a more meaningful name.

updatercd_postinst () {
:
}

# Update hostapd sources in the public git with r4-11 patches that have not been up 
# streamed yet. Once the up stream of R4 release by wlan r&d is finsished we would
# remove these patches from the amend.

SRC_URI_append = " \
    file://0001-hostapd-change-conf-file-for-better-oob-startup.patch;patchdir=.. \
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
    file://0033-WPS2-Add-WPS-IE-to-Assoc-Resp-for-cases-of-WLAN_STA_.patch;patchdir=.. \
    file://0034-hostap-allow-action-frames-with-unknown-BSSID-in-GO-.patch;patchdir=.. \
    file://0035-use-GO-channel-for-Operating-Channel-IE.patch;patchdir=.. \
    file://0036-Sync-with-include-linux-nl80211.h.patch;patchdir=.. \
    file://0037-P2P-Remove-CCK-supported-rates-when-running-P2P-scan.patch;patchdir=.. \
    file://0038-nl80211-Add-support-to-disable-CCK-rate-for-p2p-prob.patch;patchdir=.. \
    file://0039-p2p-disable-11b-rates-only-on-p2p-interface-creation.patch;patchdir=.. \
    file://0040-nl80211-fix-the-not-disabled-case-in-disable_11b_rat.patch;patchdir=.. \
    file://0041-driver_nl80211-added-the-power-save-private-driver-c.patch;patchdir=.. \
    file://0042-wpa_s-reorganize-P2P-and-WPS-scan-code.patch;patchdir=.. \
    file://0043-wpa_s-add-scheduled-scan-driver-operations.patch;patchdir=.. \
    file://0044-nl80211-update-nl80211_copy.h-to-add-support-for-sch.patch;patchdir=.. \
    file://0045-nl80211-add-scheduled-scan-support.patch;patchdir=.. \
    file://0046-wpa_s-use-sched_scan-in-driver-init.patch;patchdir=.. \
    file://0047-wpa_s-Increase-number-of-SSIDs-per-scan.patch;patchdir=.. \
    file://0048-wpa_s-check-if-sched_scan-is-supported-before-using-.patch;patchdir=.. \
    file://0049-nl80211-check-if-sched_scan-is-supported-in-wiphy-in.patch;patchdir=.. \
    file://0050-wpa_s-add-filter-support-to-scheduled-scans.patch;patchdir=.. \
    file://0051-nl80211-add-support-for-sched_scan-filtering.patch;patchdir=.. \
    file://0052-wpa_s-add-delayed-scheduled-scan-request.patch;patchdir=.. \
    file://0053-wpa_s-add-extra-IEs-for-P2P-and-WPS-in-scheduled-sca.patch;patchdir=.. \
    file://0054-nl80211-update-nl80211_copy.h-to-add-support-for-WPL.patch;patchdir=.. \
    file://0055-driver_nl8011-Add-support-for-DRIVER-RXFILTER-comman.patch;patchdir=.. \
    file://0056-Android-Add-DRIVER-command-to-wpa_cli.patch;patchdir=.. \
    file://0057-sched-scan-Fix-passive-scanning.patch;patchdir=.. \
    file://0058-sched-scan-Turn-passive-scan-into-active.patch;patchdir=.. \
    file://0060-sched-scan-Avoid-changing-state-to-SCANNING.patch;patchdir=.. \
    file://0061-sched-scan-Avoid-scan-if-no-enabled-networks.patch;patchdir=.. \
    file://0062-sched-scan-Update-scan-according-to-changes-in-netwo.patch;patchdir=.. \
    file://0063-sched-scan-Fallback-to-normal-scan-during-WPS.patch;patchdir=.. \
    file://0064-sched-scan-Logic-changes.patch;patchdir=.. \
    file://0065-Revert-hostap-set-ht_capab-from-wiphy-when-in-P2P-GO.patch;patchdir=.. \
    file://0066-hostap-set-ht_capab-from-wiphy-when-in-P2P-GO-mode.patch;patchdir=.. \
    file://0067-rx-filters-Add-support-for-longer-patterns.patch;patchdir=.. \
    file://0068-update-nl80211_copy.h.patch;patchdir=.. \
    file://0069-add-PRE_ASSOC-flag-support.patch;patchdir=.. \
    file://0070-save-eapol-for-later-use-EAPOL-Start-race.patch;patchdir=.. \
    file://0071-Remove-ARP-wowlan-wakeup-pattern.patch;patchdir=.. \
    file://0072-Enable-wpa_dbg-on-Android.patch;patchdir=.. \
    file://0073-sched-scan-Continue-to-scan-even-if-previous-scan-ca.patch;patchdir=.. \
    "
