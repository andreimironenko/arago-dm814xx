PR_append = "-arago2"

RDEPENDS_task-sdk-bare := "${@oe_filter_out('glibc-dbg', bb.data.getVar('RDEPENDS_task-sdk-bare', d, 1), d)}"

RDEPENDS_task-sdk-bare += " \
    gdbserver \
    binutils-dev \
"
