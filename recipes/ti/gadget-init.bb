# Recipe to allow gadget initialization and host initialization on
# am335x devices using the linux 3.1 kernel.  This is a workaround until
# the limitations in the USB subsystem have been addressed and we can
# have proper host vs gadget support.  Right now in order to sense the
# host mode ID pin we must load a gadget driver first.  Likewise, when
# the device is removed we need to turn the VBUS power back on so that
# re-insertion will work.
# This recipe is based on the recipe by Koen in meta-texasinstruments
DESCRIPTION = "Units to initialize usb gadgets"

PR = "r4"

LICENSE = "MIT"

COMPATIBLE_MACHINE = "ti33x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://init \
           file://99-hokey-pokey.rules \
           file://hokey-pokey.sh \
          "

INITSCRIPT_NAME = "storage-gadget-init"
INITSCRIPT_PARAMS = "defaults 99"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/storage-gadget-init

    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/*.rules ${D}${sysconfdir}/udev/rules.d

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}
}

FILES_${PN} = "${sysconfdir} ${bindir}"
RDEPENDS += "kernel-module-g-ether kernel-module-g-mass-storage"
