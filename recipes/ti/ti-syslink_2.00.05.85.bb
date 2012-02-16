require ti-syslink.inc

PV = "2_00_05_85"

SRC_URI = "http://www.sanb.design.ti.com/tisb_releases/SysLink/${PV}/exports/syslink_${PV}.tar.gz;name=syslinktarball \
           file://sdk.mk \
           file://0001-makefile-change-test-to-use-PREFIX-instead-of-a-fixe.patch \
"

SRC_URI[syslinktarball.md5sum] = "521d08397cb27a9871e2bf7593ea85d7"
SRC_URI[syslinktarball.sha256sum] = "56b492a9509c483d50d47f7a83d8852972f2ff1afb5f4b943885b0ff8806b4f7"

do_prepsources() {
    cp ${WORKDIR}/sdk.mk ${S}
}
addtask prepsources after do_unpack before do_patch
