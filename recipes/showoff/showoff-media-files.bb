DESCRIPTION = "Media files to include on showoff demo"
LICENSE="Various"

SRC_URI[showoffmediafiles.md5sum] = "e0a0516fbafaf6ddf1c454fc3db46da4"
SRC_URI[showoffmediafiles.sha256sum] = "f8d2bfb1c5244ab9b3fa67d499c5a2ff0e318f78725533ebcb78f18fd6558200"

SRC_URI = "http://downloads.sourceforge.net/project/showoff/media_files.tar.gz;name=showoffmediafiles"

PV=1

FILES_${PN} += "${datadir}/showoff"
S=${WORKDIR}/media_files

inherit base

do_install() {
    mkdir -p ${D}/${datadir}/showoff
    cp -R ${S}/*.m4a ${D}/${datadir}/showoff
    cp -R ${S}/*.mp4 ${D}/${datadir}/showoff
    cp -R ${S}/*.aac ${D}/${datadir}/showoff
    cp -R ${S}/*.license ${D}/${datadir}/showoff
}

