# Arago-specific amendments to the standard OE base-files recipe

# base-files package is older than GPLv3 and there were no official announcements
# about changing the license. Stick to GPLv2 for now, clear out with upstream.
LICENSE = "GPLv2+"

# It's not possible to differentiate between DA8xx/OMAP-L1xx/AM18xx parts
hostname_da830-omapl137-evm = "arago"
hostname_da850-omapl138-evm = "arago"

# DM814x-Z3 
hostname_dm814x-z3 = $PRODUCT 

# DM814x-EVM 
hostname_dm814x-stib = $PRODUCT 

# DM365-HTC 
hostname_dm365-evm = $PRODUCT 

PR_append = "-arago2"
