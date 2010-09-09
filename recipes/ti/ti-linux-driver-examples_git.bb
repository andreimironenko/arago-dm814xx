require ti-linux-driver-examples.inc

SRCREV = "afa28447addb2fadd9daf5c94c6cf46d8c855d05"

PV = "psp-03.XX.00.37"
PR_append = "e"

SRC_URI = "git://arago-project.org/git/projects/examples-davinci.git;protocol=git \
  file://0001-linux-davinci-example-add-make-install-target.patch;patch=1 \
"

