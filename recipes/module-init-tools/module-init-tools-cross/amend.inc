# amend the PROVIDES to provide depmod for 3.1, 3.2 kernels

PR_append = "-arago2"

PROVIDES += "virtual/${TARGET_PREFIX}depmod-3.1"
PROVIDES += "virtual/${TARGET_PREFIX}depmod-3.2"
