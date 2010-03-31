require gst-plugins.inc

PR = "r1"

OE_ALLOW_INSECURE_DOWNLOADS = "1"

DEPENDS += "gst-plugins-base"

EXTRA_OECONF += "--disable-esd --disable-annodex --disable-x " 

