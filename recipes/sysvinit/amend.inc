# Arago-specific amendments to the standard OE SysVinit recipe

# When building for "unified" arago machine, start gettys on first 3 serial consoles (ttyS?/ttyO?)
add_arago_gettys () {
	echo "S0:2345:respawn:${base_sbindir}/getty 115200 ttyS0" >> ${D}${sysconfdir}/inittab
	echo "S1:2345:respawn:${base_sbindir}/getty 115200 ttyS1" >> ${D}${sysconfdir}/inittab
	echo "S2:2345:respawn:${base_sbindir}/getty 115200 ttyS2" >> ${D}${sysconfdir}/inittab
	echo "O0:2345:respawn:${base_sbindir}/getty 115200 ttyO0" >> ${D}${sysconfdir}/inittab
	echo "O1:2345:respawn:${base_sbindir}/getty 115200 ttyO1" >> ${D}${sysconfdir}/inittab
	echo "O2:2345:respawn:${base_sbindir}/getty 115200 ttyO2" >> ${D}${sysconfdir}/inittab
}

do_install_append_arago-armv5 () {
	add_arago_gettys
}

do_install_append_arago-armv7 () {
	add_arago_gettys
}

# Set the suffix to specify amended packages
PR_append = "-arago2"
