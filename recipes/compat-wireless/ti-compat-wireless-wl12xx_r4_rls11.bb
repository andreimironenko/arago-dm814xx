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

PR = "r1"
COMPAT_WIRELESS_VERSION = "2011-11-01-r4-11"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/719/5185/ti-compat-wireless-wl12xx-${COMPAT_WIRELESS_VERSION}.tgz \
	"

SRC_URI[md5sum] = "6024aed75f36f0cc380c93e747d7761c"
SRC_URI[sha256sum] = "2c5369f396641e97697558fd0812cc8c4dfa99ca22f2306c7cebd2cfcc76e0a1"
