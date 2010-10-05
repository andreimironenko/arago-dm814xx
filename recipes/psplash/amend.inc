PR = "-arago1"

do_install_append() {
   # disable the psplash timeout, system test team does not want see login prompt on display
   sed -i -e 's/^(sleep*/#(sleep/g' ${D}${sysconfdir}/init.d/psplash
}

