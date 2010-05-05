DESCRIPTION = "Task for AMSDK additional dependencies"
PR = "r7"

inherit task

RDEPENDS_${PN} = "\
    dbus \
    expat \
    glib-2.0 \
    libxml2 \
    openssl \
    libpcre \
    iptables \
    wpa-supplicant \
    bluez4 \
    bluez4-agent \
    libasound-module-bluez \
    bluez-hcidump \
    openobex \
    openobex-apps \
    obexftp \
    ussp-push \
    iperf \
    matrix-gui \
    am-sysinfo \
    libgles-omap3-rawdemos \
    "
