# Arago-specific amendments to openssl to enable openssl to use
# OCF hardware drivers if present.

# only enable this for openssl on the target board, not the native recipe
DEPENDS_pn-openssl += "ocf-linux"
CFLAG_pn-openssl += "-DHAVE_CRYPTODEV -DUSE_CRYPTODEV_DIGESTS"

# Set the suffix to specify amended packages
PR_append = "-arago1"
