# Arago-specific amendments to the standard openobex recipes

# Add apps/obex_test for testing

do_configure_prepend() {
	sed -i -e 's:bin_PROGRAMS = irxfer obex_tcp irobex_palm3:bin_PROGRAMS = irxfer obex_tcp irobex_palm3 obex_test:g' ${S}/apps/Makefile.am
	aclocal
	automake --add-missing
	autoreconf
}

PR_append = "-arago2"

# tell the openobex configure script NOT to enable syslog and dump.
# enabling log and dump prints lots of debug messages and hex dump value during BT
# file transfer using openobex, due to which BT transfer get slow.

EXTRA_OECONF := "${@oe_filter_out('--enable-syslog', bb.data.getVar('EXTRA_OECONF', d, 1), d)}"
EXTRA_OECONF := "${@oe_filter_out('--enable-dump', bb.data.getVar('EXTRA_OECONF', d, 1), d)}"

# WiLink Bluetooth application build depends on libmisc.a.

do_install_append() {
	install -m 644 ${S}/apps/libmisc.a ${D}/${libdir}
}
