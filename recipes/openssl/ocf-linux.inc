DESCRIPTION = "Install required headers to enable OCF Linux support"
LICENSE = "BSD"

INC_PR = "r1"

SRC_URI = "http://sourceforge.net/projects/ocf-linux/files/ocf-linux/${PV}/ocf-linux-${PV}.tar.gz"

S = "${WORKDIR}/ocf-linux-${PV}"

# The base package does not contain any files to be installed on the target
# system.  However, the ocf-linux-dev package Depends on the base package.
# This will make sure that the base package is created so that the
# dependency will not fail.
# This happens in particular when installing a dev packages such as
# openssl-dev because the package class will cause ocf-linux-dev to be
# installed, which in turn depends on ocf-linux base package.
ALLOW_EMPTY_${PN} = "1"

# Need to unpack the the ocf-linux.tar.gz file contained inside the
# downloaded tarball
do_install_prepend() {
    cd ${S}
    tar xzf ocf-linux.tar.gz
}

# Install the OCF Linux headers so that other packages such as openssl
# can find them.  The headers must be in a crypto directory according to
# the README file.
do_install() {
    install -d ${D}${includedir}/crypto
    install -m 0644 ${S}/ocf/*.h ${D}${includedir}/crypto/
}
