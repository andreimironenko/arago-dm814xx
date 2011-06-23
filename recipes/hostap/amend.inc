# NLCP supports both station and AP modes.
# don't start hostapd automatically when the system boot up.

PR_append = "-arago2"

# The default hostapd.conf file that is fetched from upstream is using
# "a" band which is not supported by the 1271 chip.
# modify it to use "g" band and default channel 6 and change the ssid from 
# "test" to a more meaningful name.

SRC_URI += "file://0001-hostapd-change-conf-file-for-better-oob-startup.patch"

updatercd_postinst () {
:
}
