
LICENSE = "TI"

SRC_URI_append = " file://opengl-gfx.patch \
  file://Graphics_SDK_Manifest.doc\
 "

do_prepsources() {
  cp ${WORKDIR}/Graphics_SDK_Manifest.doc ${S}/
}

# run prepare sources before do_patch to ensure that sourceipk picks the right sources.
addtask prepsources after do_unpack before do_patch

PR_append  = "arago1"

