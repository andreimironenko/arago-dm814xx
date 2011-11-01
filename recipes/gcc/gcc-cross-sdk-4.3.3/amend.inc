EXTRA_OECONF := "${@oe_filter_out('--disable-libssp', bb.data.getVar('EXTRA_OECONF', d, 1), d)}"

PR_append = "-arago1"
