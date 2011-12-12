SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPLv2"
HOMEPAGE = "http://www.gstreamer.net/"

DEFAULT_PREFERENCE = "-1"

inherit autotools pkgconfig

OE_LT_RPATH_ALLOW = "any"
OE_LT_RPATH_ALLOW[export]="1"

AUTOTOOLS_STAGE_PKGCONFIG = "1"
