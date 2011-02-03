require recipes/ortp/ortp.inc
PR = "${INC_PR}.0"
PVextra = "-20080211"

SRC_URI = "http://download.savannah.nongnu.org/releases/linphone/${PN}/sources/${P}${PVextra}.tar.bz2"

SRC_URI =+ "file://0001-ORTP-TI-Changes-on-top-of-0.14.2.patch"
SRC_URI =+ "file://0002-ORTP-Existing-files-in-0.14.2-modified-by-TI.patch"
SRC_URI =+ "file://libortp.a"

S = "${WORKDIR}/${P}"

SRC_URI[md5sum] = "daa56330561352c2da1836576380f4eb"
SRC_URI[sha256sum] = "a6efdb1ac4f6d43107a82d6a73686614aab442d0b19453decd2281102ff29b27"


do_prepsources () {
	
    # Copy over a prebuilt .a file
    mkdir -p ${S}/lib
    cp ${WORKDIR}/libortp.a ${S}/lib
}

addtask prepsources after do_unpack before do_patch
