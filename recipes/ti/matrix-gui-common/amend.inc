# bump svn rev
SRCREV = "174"

PR_append = "-arago4"

do_prepsources() {
    # run step - this will delete .svn file
    find ${WORKDIR}/${DMAIBRANCH} -name .svn -type d | xargs rm -rf
}

# run prepare sources before do_patch to ensure that sourceipk picks the right sources.
addtask prepsources after do_unpack before do_patch

