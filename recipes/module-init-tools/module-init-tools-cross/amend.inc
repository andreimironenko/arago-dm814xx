# amend the PROVIDES to provide depmod for 3.1 kernels

PR_append = "-arago1"

PROVIDES += "virtual/${TARGET_PREFIX}depmod-3.1"
