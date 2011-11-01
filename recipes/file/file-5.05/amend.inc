do_configure_prepend () {
	sed -i 's|-Wextra||' ${S}/configure.ac
}

PR_append = "-arago1"
