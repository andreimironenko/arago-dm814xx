# This is a TI specific version of the compat-wireless recipe using a 
# compat-wireless package created from the TI Systems Tested mac80211 releases.
#
# This approach has been taken to simplify the packaging of a compat-wireless 
# release for WL1271 on the 2.6.37 based PSP/SDK from TI.
#
# This approach avoids the need to take the latest compat-wireless package 
# from public sources and patching this onto the SDK along with additional 
# patches that are currently being up-streamed. 
# These later patches are essential to address defects that have been found 
# during systems testing. This approach avoids the need to apply many 
# patches (100’s) which is unmanageable.
#
# This approach is an interim approach due to the PSP/SDK being based on 2.6.37. 
# When the PSP/SDK are based on 2.6.39 TI is planning to switch to applying 
# a much smaller number of patches (10’s) on the up-streamed version 
# (which will be more mature in 2.6.39).

include ti-compat-wireless.inc

PR = "r0"
COMPAT_WIRELESS_VERSION = "2011-05-17-r3-m1-rc2"

SRC_URI = "https://gforge.ti.com/gf/download/frsrelease/555/4629/ti-compat-wireless-wl12xx-${COMPAT_WIRELESS_VERSION}.tgz \
	"

SRC_URI[md5sum] = "7c6e4718f0f4c2e018c245d73e111cd0"
SRC_URI[sha256sum] = "7dca98d4d431183a3daaf03499612e8e560387feaaaecc815159e62816873250"

