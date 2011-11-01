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
# patches (100’s) which is unmanageable.

include ti-compat-wireless.inc

PR = "r0"
COMPAT_WIRELESS_VERSION = "2011-10-10-r4-11"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/659/5061/ti-compat-wireless-wl12xx-${COMPAT_WIRELESS_VERSION}.tgz \
	"

SRC_URI[md5sum] = "eb47c5ea801e0002db244a9d4cae071e"
SRC_URI[sha256sum] = "8d62b774bda090f5e72e8ee397011d049f6e7fded3057b72961b3545a334822f"

