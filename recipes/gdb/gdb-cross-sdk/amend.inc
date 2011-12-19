PR_append = "-arago0"

inherit gettext

EXTRA_OECONF += " \
	--with-python="${STAGING_LIBDIR_NATIVE}" \
"

DEPENDS += "python"
