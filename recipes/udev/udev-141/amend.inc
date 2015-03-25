# Arago-specific amendments to the standard OE udev recipe

# only provide own versions of init and mount.sh

# Provide our own version of local.rules until touchscreen is fixed

# Provide own udev default file, that disables dev cache for armv7a

# Set the suffix to specify amended packages

# A. Mironenko
# For armv5te only
# Fixing dev.tar cache problem where the tar ball was not properly constructed
# and during the next reboot not all devices are properly populated.
# Unfortunately UDEV does not care about integritiy of dev.tar, so the MD5 
# check sum has been introduced to maintain the integrity, and in case of 
# coruption the /dev will be repopulated without using cache. As a consequence
# of this the first boot takes a bit longer (aproximately for additional 20 sec) 
# but all consequent successful reboots will use the dev.tar and will boot    
# normally.

PR_append = "-arago5"
