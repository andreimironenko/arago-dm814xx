# Arago-specific amendments to the standard OE curl recipe

# Disable all crypt support in curl due to export restrictions
CURL_FEATURES = "zlib,cookies,dict,file,ftp,http,telnet,tftp"

# Set the suffix to specify amended packages
PR_append = "-arago1"
