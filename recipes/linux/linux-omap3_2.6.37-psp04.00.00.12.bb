require linux-omap3.inc

COMPATIBLE_MACHINE = "ti816x"

KVER = "2.6.37"
PSPREL = "04.00.00.12"

BRANCH = "ti81xx-master"
# Use literal tags in SRCREV, when available, instead of commit IDs
SRCREV = "v2.6.37_TI816XPSP_04.00.00.12"

# do_sysroot_stage_all_append () {
#     mkdir -p $kerneldir/drivers/video
#     cp -fR drivers/video/* $kerneldir/drivers/video
# }

do_stagevideo () {
     kerneldir=${SYSROOT_DESTDIR}${STAGING_KERNEL_DIR}
     mkdir -p $kerneldir/drivers/video
     cp -fR drivers/video/* $kerneldir/drivers/video
}

addtask stagevideo after do_populate_sysroot
