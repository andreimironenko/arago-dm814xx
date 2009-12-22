require linux-omap3_psp2.inc

KVER = "2.6.28+2.6.29-rc3"
PSPREL = "2.1.2.9"

PV = "${KVER}-psp${PSPREL}"

SRCREV = "c40ce00e32082c57070fdba39c7d7cba3228d440"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/tmlind/linux-omap-2.6.git;protocol=git"

SRC_URI_append_omap3evm = " \
file://0001-usb-musb-sergei-s-8-patch-set.patch;patch=1 \
file://0002-usb-musb-adding-high-bandwidth-support.patch;patch=1 \
file://0003-usb-musb-fix-bug-in-musb_start_urb.patch;patch=1 \
file://0004-usb-musb-NAK-timeout-scheme-on-bulk-reserved-ep.patch;patch=1 \
file://0005-usb-musb-adding-nop-usb-transceiver.patch;patch=1 \
file://0006-usb-musb-registering-nop-xceiv-for-musb.patch;patch=1 \
file://0007-usb-musb-add-back-otg_get_transceiver.patch;patch=1 \
file://0008-usb-musb-fix-module-insert-issue.patch;patch=1 \
file://0009-usb-musb-init-musb-gadget_driver-to-null.patch;patch=1 \
file://0010-usb-musb-fix-vbuf-off-after-disconnect.patch;patch=1 \
file://0011-usb-musb-adding-musb-procfs-file.patch;patch=1 \
file://0012-usb-musb-sdma-for-all-the-rx-channels.patch;patch=1 \
file://0013-usb-musb-add-suspend-proc-entry-for-otg-testing.patch;patch=1 \
file://0014-usb-musb-remove-auto-selection-of-USB_SUSPEND-with.patch;patch=1 \
file://0015-usb-ehci-fix-ehci-rmmod-issue.patch;patch=1 \
file://0016-usb-ehci-fix-ehci-issue-when-built-as-module.patch;patch=1 \
file://0017-usb-ehci-update-for-mistral-daughter-card.patch;patch=1 \
file://0018-usb-ehci-EHCI-support-on-ES3.0.patch;patch=1 \
file://0019-OMAP2-3-clock-implement-clock-notifier-infrastructu.patch;patch=1 \
file://0020-OMAP-clock-add-notifier-infrastructure.patch;patch=1 \
file://0021-OMAP2-3-clock-store-planned-clock-rates-into-tempor.patch;patch=1 \
file://0022-OMAP2-3-clock-add-clk-post-rate-change-notifiers.patch;patch=1 \
file://0023-OMAP2-3-clock-add-clock-pre-rate-change-notificatio.patch;patch=1 \
file://0024-OMAP2-3-clock-add-clock-prepare-rate-change-notific.patch;patch=1 \
file://0025-OMAP2-3-clock-add-clock-abort-rate-change-notificat.patch;patch=1 \
file://0026-OMAP2-3-PM-create-the-OMAP-PM-interface-and-add-a-d.patch;patch=1 \
file://0027-OMAP2-3-omapdev-add-basic-omapdev-structure.patch;patch=1 \
file://0028-OMAP242x-omapdev-add-OMAP242x-omapdev-records.patch;patch=1 \
file://0029-OMAP243x-omapdev-add-OMAP243x-omapdev-records.patch;patch=1 \
file://0030-OMAP3xxx-omapdev-add-OMAP3xxx-omapdev-records.patch;patch=1 \
file://0031-OMAP2-3-omapdev-add-code-to-walk-the-omapdev-record.patch;patch=1 \
file://0032-OMAP-PM-counter-infrastructure.patch;patch=1 \
file://0033-OMAP-PM-Hook-into-PM-counters.patch;patch=1 \
file://0034-OMAP-PM-Add-closures-to-clkdm_for_each-and-pwrdm_f.patch;patch=1 \
file://0035-OMAP-PM-Add-pm-debug-counters.patch;patch=1 \
file://0036-OMAP-PM-debug-make-powerdomains-use-PM-debug-count.patch;patch=1 \
file://0037-OMAP-PM-debug-do-not-print-out-status-for-meta-pow.patch;patch=1 \
file://0038-OMAP-PM-debug-Add-PRCM-register-dump-support.patch;patch=1 \
file://0039-OMAP-PM-Add-definitions-for-ETK-pads-and-observabi.patch;patch=1 \
file://0040-OMAP-Debug-observability-and-ETK-padconf-implementa.patch;patch=1 \
file://0041-OMAP-Add-debug-observablity-debobs-Kconfig-item.patch;patch=1 \
file://0042-OMAP3-PM-GPMC-context-save-restore.patch;patch=1 \
file://0043-OMAP3-PM-GPIO-context-save-restore.patch;patch=1 \
file://0044-OMAP3-PM-I2C-context-save-restore.patch;patch=1 \
file://0045-OMAP3-PM-INTC-context-save-restore.patch;patch=1 \
file://0046-OMAP3-PM-PRCM-context-save-restore.patch;patch=1 \
file://0047-OMAP3-PM-Populate-scratchpad-contents.patch;patch=1 \
file://0048-OMAP3-PM-SCM-context-save-restore.patch;patch=1 \
file://0049-OMAP3-PM-SRAM-restore-function.patch;patch=1 \
file://0050-OMAP3-PM-handle-PER-NEON-CORE-in-idle.patch;patch=1 \
file://0051-OMAP3-PM-Restore-MMU-table-entry.patch;patch=1 \
file://0052-OMAP3-PM-MPU-off-mode-support.patch;patch=1 \
file://0053-OMAP3-PM-CORE-domain-off-mode-support.patch;patch=1 \
file://0054-OMAP3-PM-allow-runtime-enable-disable-of-OFF-mode.patch;patch=1 \
file://0055-OMAP3-3430SDP-minimal-kernel-defconfig.patch;patch=1 \
file://0056-OMAP-PM-sysfs-interface-for-enabling-voltage-off-i.patch;patch=1 \
file://0057-OMAP-PM-DMA-context-save-restore.patch;patch=1 \
file://0058-OMAP3-PM-CPUidle-Basic-support-for-C1-C2.patch;patch=1 \
file://0059-OMAP3-PM-CPUidle-Enables-state-C4.patch;patch=1 \
file://0060-OMAP3-PM-CPUidle-Enables-C3-and-C5.patch;patch=1 \
file://0061-OMAP3-PM-CPUidle-Safe-state-on-bm-activity.patch;patch=1 \
file://0062-OMAP3-PM-CPUidle-obey-enable_off_mode-flag.patch;patch=1 \
file://0063-OMAP3-PM-CPUidle-restrict-C-states-on-UART-activi.patch;patch=1 \
file://0064-OMAP3-PM-Fix-cpu-idle-init-sequencing.patch;patch=1 \
file://0065-OMAP-PM-off-mode-support-for-DMA-on-EMU-HS-devices.patch;patch=1 \
file://0066-OMAP3-SRAM-size-fix-for-HS-EMU-devices.patch;patch=1 \
file://0067-OMAP3-PM-off-mode-support-for-HS-EMU-devices.patch;patch=1 \
file://0068-OMAP3-PM-Enable-SDRAM-auto-refresh-during-sleep.patch;patch=1 \
file://0069-OMAP-SDRC-Add-new-register-definitions-for-SDRAM-c.patch;patch=1 \
file://0070-OMAP3-PM-SDRC-auto-refresh-workaround-for-off-mode.patch;patch=1 \
file://0071-OMAP-PM-Implement-get_last_off_on_transaction_id.patch;patch=1 \
file://0072-OMAP3-PM-Use-pwrdm_set_next_pwrst-instead-of-set_p.patch;patch=1 \
file://0073-OMAP3-SRF-Generic-shared-resource-f-w.patch;patch=1 \
file://0074-OMAP3-SRF-MPU-CORE-PD-latency-modeling.patch;patch=1 \
file://0075-OMAP3-SRF-omap3-srf-driver.patch;patch=1 \
file://0076-OMAP3-SRF-OMAP-PM-srf-implementation.patch;patch=1 \
file://0077-OMAP-SRF-Fixes-to-shared-resource-framework-Ver.3.patch;patch=1 \
file://0078-OMAP3-PM-Fix-wrong-sequence-in-suspend.patch;patch=1 \
file://0079-OMAP3-PM-decouple-PER-and-CORE-context-save-and-re.patch;patch=1 \
file://0080-PM-Added-three-PLL-registers-to-the-PRCM-context-sa.patch;patch=1 \
file://0081-PM-Changed-secure-RAM-storage-size-from-0x8000-to-0.patch;patch=1 \
file://0082-OMAP3-PM-Do-not-build-suspend-code-if-SUSPEND-is-n.patch;patch=1 \
file://0083-OMAP-PM-Build-fails-if-PM-is-not-enabled.patch;patch=1 \
file://0084-OMAP2-PM-Fix-omap2-build.patch;patch=1 \
file://0085-OMAP3-SRF-Add-CORE-rate-table-param-in-OMAP-PM.patch;patch=1 \
file://0086-OMAP3-SRF-Add-VDD1-VDD2-rate-tables-for-3430SDP.patch;patch=1 \
file://0087-OMAP3-SRF-Add-virt-clk-nodes-for-VDD1-VDD2.patch;patch=1 \
file://0088-OMAP3-SRF-Adds-OPP-Freq-res-s-in-SRF.patch;patch=1 \
file://0089-OMAP3-SRF-Update-OMAP-PM-layer.patch;patch=1 \
file://0090-OMAP3-SRF-Voltage-scaling-support.patch;patch=1 \
file://0091-OMAP3-SRF-VDD2-scaling-support.patch;patch=1 \
file://0092-OMAP3-SRF-Adds-sysfs-control-for-VDD1-VDD2-OPP-s.patch;patch=1 \
file://0093-OMAP3-PM-Replace-spinlocks-with-mutex-in-SRF.patch;patch=1 \
file://0094-OMAP3-PM-CPUFreq-driver-for-OMAP3.patch;patch=1 \
file://0095-OMAP3-PM-Update-the-min-defconfig-for-3430sdp.patch;patch=1 \
file://0096-OMAP3-SRF-Fix-crash-on-non-3430SDP-platforms-with-D.patch;patch=1 \
file://0097-Save-sram-context-after-changing-MPU-DSP-or-core-cl.patch;patch=1 \
file://0098-PM-Added-suspend-target-state-control-to-debugfs-fo.patch;patch=1 \
file://0099-OMAP2-3-PM-system_rev-omap_rev.patch;patch=1 \
file://0100-OMAP3-PM-Prevent-PER-from-going-OFF-when-CORE-is-g.patch;patch=1 \
file://0101-OMAP3-PM-Update-SSI-omapdev-record.patch;patch=1 \
file://0102-PM-OMAP3-Change-omap3_save_secure_ram-to-be-called.patch;patch=1 \
file://0103-OMAP3-PM-MPU-and-CORE-should-stay-awake-if-there-i.patch;patch=1 \
file://0104-OMAP3-PM-Scale-VDD2-OPP-for-VDD1-OPP3-and-higher.patch;patch=1 \
file://0105-OMAP3-GPIO-fixes-for-off-mode.patch;patch=1 \
file://0106-PM-OMAP3-Refreshed-DVFS-VDD1-control-against-lates.patch;patch=1 \
file://0107-ARM-MMU-add-a-Non-cacheable-Normal-executable-memo.patch;patch=1 \
file://0108-OMAP3-SRAM-mark-OCM-RAM-as-Non-cacheable-Normal-mem.patch;patch=1 \
file://0109-OMAP3-SRAM-add-ARM-barriers-to-omap3_sram_configure.patch;patch=1 \
file://0110-OMAP3-clock-add-interconnect-barriers-to-CORE-DPLL.patch;patch=1 \
file://0111-OMAP3-SRAM-clear-the-SDRC-PWRENA-bit-during-SDRC-fr.patch;patch=1 \
file://0112-OMAP3-SDRC-Add-166MHz-83MHz-SDRC-settings-for-the.patch;patch=1 \
file://0113-OMAP3-SDRC-initialize-SDRC_POWER-at-boot.patch;patch=1 \
file://0114-OMAP3-SRAM-renumber-registers-to-make-space-for-arg.patch;patch=1 \
file://0115-OMAP3-clock-only-unlock-SDRC-DLL-if-SDRC-clk-83MH.patch;patch=1 \
file://0116-OMAP3-clock-use-pr_debug-rather-than-pr_info-in.patch;patch=1 \
file://0117-OMAP3-clock-remove-wait-for-DPLL3-M2-clock-to-stabi.patch;patch=1 \
file://0118-OMAP3-clock-initialize-SDRC-timings-at-kernel-start.patch;patch=1 \
file://0119-OMAP3-clock-add-a-short-delay-when-lowering-CORE-cl.patch;patch=1 \
file://0120-OMAP3-clock-SDRC-program-SDRC_MR-register-during-SD.patch;patch=1 \
file://0121-OMAP3-SRAM-add-more-comments-on-the-SRAM-code.patch;patch=1 \
file://0122-OMAP3-SRAM-convert-SRAM-code-to-use-macros-rather-t.patch;patch=1 \
file://0123-OMAP3-Add-support-for-DPLL3-divisor-values-higher-t.patch;patch=1 \
file://0124-OMAP3-PM-Fixed-VDD2-control-to-work-from-both-sysf.patch;patch=1 \
file://0125-OMAP3-PM-Added-DVFS-OPP-locking-interface-for-VDD1.patch;patch=1 \
file://0126-OMAP3-Fix-rate-calculation-bug-in-omap3_select_tabl.patch;patch=1 \
file://0127-OMAP3-PM-Prevented-DVFS-state-switches-when-enabli.patch;patch=1 \
file://0128-OMAP3-PM-Enable-VDD2-OPP1.patch;patch=1 \
file://0129-OMAP3-PM-Fix-linker-error-without-CONFIG_PM-option.patch;patch=1 \
file://0130-PM-OMAP3-Removed-a-couple-of-unused-variables-from.patch;patch=1 \
file://0131-PM-OMAP3-Added-support-for-possibly-failing-clk_se.patch;patch=1 \
file://0132-Fix-omap_getspeed.patch;patch=1 \
file://0133-Make-sure-omap-cpufreq-driver-initializes-after-cpuf.patch;patch=1 \
file://0134-OMAP3-PM-fix-bug-where-UART0-and-1-were-not-resumi.patch;patch=1 \
file://0135-OMAP3-PM-Add-D2D-clocks-and-auto-idle-setup-to-PRC.patch;patch=1 \
file://0136-OMAP3-PM-D2D-clockdomain-supports-SW-supervised-tr.patch;patch=1 \
file://0137-OMAP3-PM-Ensure-MUSB-block-can-idle-when-driver-no.patch;patch=1 \
file://0138-PM-debug-Fix-problems-with-PM-timers.patch;patch=1 \
file://0139-OMAP3-PM-add-common-OPP-definitions-and-use-them-o.patch;patch=1 \
file://0140-OMAP3-PM-Wait-for-SDRC-ready-iso-a-blind-delay.patch;patch=1 \
file://0141-OMAP3-PM-Don-t-scale-voltage-in-C1-state.patch;patch=1 \
file://0142-OMAP3-McSPI-Adds-context-save-restore.patch;patch=1 \
file://0143-OMAP-I2C-Include-OMAP_I2C_SYSC_REG-in-save-and-rest.patch;patch=1 \
file://0144-OMAP2-3-GPIO-remove-recursion-in-IRQ-wakeup-path.patch;patch=1 \
file://0145-OMAP2-3-GPIO-generalize-prepare-for-idle.patch;patch=1 \
file://0146-OMAP3-GPIO-disable-GPIO-debounce-clocks-on-idle.patch;patch=1 \
file://0147-OMAP2-3-clockdomains-make-virt_opp_clkdm-available.patch;patch=1 \
file://0148-Revert-OMAP3-McSPI-Adds-context-save-restore.patch;patch=1 \
file://0149-OMAP3-PM-Save-and-restore-also-CM_CLKSEL1_PLL_IVA2.patch;patch=1 \
file://0150-OMAP3-McSPI-Adds-context-save-restore.patch;patch=1 \
file://0151-Add-support-for-OMAP35x-processors.patch;patch=1 \
file://0152-Runtime-check-for-OMAP35x.patch;patch=1 \
file://0153-Board-specific-updates.patch;patch=1 \
file://0154-DSS-New-display-subsystem-driver-for-OMAP2-3.patch;patch=1 \
file://0155-DSS-OMAPFB-fb-driver-for-new-display-subsystem.patch;patch=1 \
file://0156-DSS-Add-generic-DVI-panel.patch;patch=1 \
file://0157-DSS-support-for-Beagle-Board.patch;patch=1 \
file://0158-DSS-Sharp-LS037V7DW01-LCD-Panel-driver.patch;patch=1 \
file://0159-Signed-off-by-Tomi-Valkeinen-tomi.valkeinen-nokia.patch;patch=1 \
file://0160-DSS-Support-for-OMAP3-EVM-board.patch;patch=1 \
file://0161-DSS-Hacked-N810-support.patch;patch=1 \
file://0162-DSS-OMAPFB-allocate-fbmem-only-for-fb0-or-if-spes.patch;patch=1 \
file://0163-OMAPFB-remove-extra-omapfb_setup_overlay-call.patch;patch=1 \
file://0164-OMAPFB-fix-GFX_SYNC-to-be-compatible-with-DSS1.patch;patch=1 \
file://0165-DSS-Add-comments-to-FAKE_VSYNC-to-make-things-more.patch;patch=1 \
file://0166-OMAPFB-remove-extra-spaces.patch;patch=1 \
file://0167-DSS-fix-clk_get_usecount.patch;patch=1 \
file://0168-OMAPFB-remove-debug-print.patch;patch=1 \
file://0169-VRFB-testing.patch;patch=1 \
file://0170-OMAPFB-more-VRFB-hacks.patch;patch=1 \
file://0171-OMAPFB-still-more-VRFB-hacking.patch;patch=1 \
file://0172-V4L2-driver-added.patch;patch=1 \
file://0173-Only-made-copmipled-with-Hariks-changes.patch;patch=1 \
file://0174-VRFB-rotation-at-compile-time-supported.patch;patch=1 \
file://0175-Minor-Fixes-to-V4L2-driver.patch;patch=1 \
file://0176-Minor-Fixes-in-DSS-library.patch;patch=1 \
file://0177-Kconfig-option-added-to-select-overlay-manager.patch;patch=1 \
file://0178-Merged-Latest-Tomi-s-changes.patch;patch=1 \
#file://0179-file-mode-restored-back.patch;patch=1 \
file://0180-old-FBDEV-made-working.patch;patch=1 \
file://0181-DVI-720P-and-480P-support-added.patch;patch=1 \
file://0182-VRFB-address-calculation-bug-fixed.patch;patch=1 \
file://0183-Changed-V4L2-file-operations-according-to-2.6.29.patch;patch=1 \
file://0184-Bug-Solved-Rotation-Bug.patch;patch=1 \
file://0185-Bug-Solved-Get-rotation-not-working-for-90-and-270.patch;patch=1 \
file://0186-Bug-Solved-Compile-time-option-to-select-TV-mode.patch;patch=1 \
file://0187-Changed-the-compile-time-option-to-select-LCD-or-TV.patch;patch=1 \
file://0188-Bug-Solved-VRFB-rotation-not-working-on-DVI-output.patch;patch=1 \
file://0189-Bug-solved-Colors-not-coming-proper-on-DVI-output.patch;patch=1 \
file://0190-V4L-Int-if-Dummy-slave.patch;patch=1 \
file://0191-v4l2-int-device-add-support-for-VIDIOC_QUERYMENU.patch;patch=1 \
file://0192-V4L2-Add-COLORFX-user-control.patch;patch=1 \
file://0193-OMAP34XX-CAM-Resources-fixes.patch;patch=1 \
file://0194-OMAP-CAM-Add-ISP-user-header-and-register-defs.patch;patch=1 \
file://0195-OMAP-CAM-Add-ISP-gain-tables.patch;patch=1 \
file://0196-OMAP-CAM-Add-ISP-Front-end.patch;patch=1 \
file://0197-OMAP-CAM-Add-ISP-Back-end.patch;patch=1 \
file://0198-OMAP-CAM-Add-ISP-SCM.patch;patch=1 \
file://0199-OMAP-CAM-Add-ISP-CSI2-API.patch;patch=1 \
file://0200-OMAP-CAM-Add-ISP-Core.patch;patch=1 \
file://0201-OMAP34XXCAM-Add-driver.patch;patch=1 \
file://0202-OMAP-CAM-Add-MT9P012-Sensor-Driver.patch;patch=1 \
file://0203-OMAP-CAM-Add-DW9710-Lens-Driver.patch;patch=1 \
file://0204-OMAP34XX-CAM-Add-Sensors-Support.patch;patch=1 \
file://0205-V4L-Query-slave-info.patch;patch=1 \
file://0206-OMAP3ISP-REGS-Fix-ISPCCDC_SDOFST_FOFST-definition.patch;patch=1 \
file://0207-OMAP3ISP-REGS-Add-CCDC-SBL-status-regs.patch;patch=1 \
file://0208-OMAP3ISP-REGS-Add-configuration-id-counters.patch;patch=1 \
file://0209-OMAP3ISP-Gain-Tables-Better-cfa-coefficient-table.patch;patch=1 \
file://0210-OMAP3ISP-Frontend-Add-API-for-CCDC-SBL-busy.patch;patch=1 \
file://0211-OMAP3ISP-Frontend-Now-using-video-port-for-RAW-cap.patch;patch=1 \
file://0212-OMAP3ISP-Frontend-fix-ISPCCDC_SDOFST_FOFST-clearin.patch;patch=1 \
file://0213-OMAP3ISP-Frontend-fix-colors-bayer-phase-in-raw.patch;patch=1 \
file://0214-OMAP3ISP-Frontend-Sensor-pattern-and-VP-fix-for-YU.patch;patch=1 \
file://0215-OMAP3ISP-Frontend-Fix-output-horizontal-pixel-coun.patch;patch=1 \
file://0216-OMAP3ISP-Frontend-Remaining-Syncup-with-Nokia-Code.patch;patch=1 \
file://0217-OMAP3ISP-Frontend-Small-cleanups.patch;patch=1 \
file://0218-OMAP3ISP-Frontend-Change-default-DC-substraction-v.patch;patch=1 \
file://0219-OMAP3ISP-Backend-Use-correct-number-of-lines-in-pr.patch;patch=1 \
file://0220-OMAP3ISP-Backend-Fix-for-default-WB-coeficients-fo.patch;patch=1 \
file://0221-OMAP3ISP-Backend-Better-preview-default-values.patch;patch=1 \
file://0222-OMAP3ISP-Backend-Resizer-cleanup.patch;patch=1 \
file://0223-OMAP3ISP-Backend-Always-do-workaround.patch;patch=1 \
file://0224-OMAP3ISP-Backend-Correct-applying-of-RGB2RGB-RGB2.patch;patch=1 \
file://0225-OMAP3ISP-SCM-Add-configuration-id-counters.patch;patch=1 \
file://0226-OMAP3ISP-SCM-H3a-Aewb-first-frame-statistics-fix.patch;patch=1 \
file://0227-OMAP3ISP-SCM-WB-coefficients-update-via-h3a-for-co.patch;patch=1 \
file://0228-OMAP3ISP-SCM-Allow-unloading-the-module-without-a.patch;patch=1 \
file://0229-OMAP3ISP-Core-Fix-crop.patch;patch=1 \
file://0230-OMAP3ISP-Core-Rewrite-ISR-and-buff-mgmt.patch;patch=1 \
file://0231-OMAP3ISP-Core-Flush-buffers-also-when-queueing.patch;patch=1 \
file://0232-OMAP3ISP-Core-Fix-isp_s_fmt_cap-crop-for-raw-captu.patch;patch=1 \
file://0233-OMAP3ISP-Core-Enable-Preview-Callback.patch;patch=1 \
file://0234-OMAP3ISP-Core-Do-idle-mode-settings-in-the-ISP-dri.patch;patch=1 \
file://0235-OMAP3ISP-Core-Move-clk_gets-to-isp_init.patch;patch=1 \
file://0236-OMAP3ISP-Core-Clean-up-temporary-buffer-workaround.patch;patch=1 \
file://0237-OMAP3ISP-Core-Make-isp_interface_config-as-part-of.patch;patch=1 \
file://0238-OMAP3ISP-Core-Remove-isp_request_interface-and-dum.patch;patch=1 \
file://0239-OMAP3ISP-Core-Remove-isp_configure_interface_bridg.patch;patch=1 \
file://0240-OMAP3ISP-Core-Move-temporary-buffer-stuff-to-struc.patch;patch=1 \
file://0241-OMAP3ISP-Core-More-cleanups.patch;patch=1 \
file://0242-OMAP3ISP-Core-Remove-isp_get_xclk-and-make-isp_-_c.patch;patch=1 \
file://0243-OMAP3ISP-Core-compile-fix.patch;patch=1 \
file://0244-OMAP3ISP-Core-Fix-error-checking-for-isp_addr-in-i.patch;patch=1 \
file://0245-OMAP3ISP-Core-MMU-Small-cleanup.patch;patch=1 \
file://0246-OMAP3ISP-Core-Remove-idle-mode-settings-from-mmu.patch;patch=1 \
file://0247-omap3isp-Add-interface-type-ISP_NONE-for-preview.patch;patch=1 \
file://0248-OMAP34XXCAM-Implement-VIDIOC_ENUM_SLAVES.patch;patch=1 \
file://0249-omap34xxcam-Get-format-from-the-sensor-in-the-begin.patch;patch=1 \
file://0250-omap34xxcam-Handle-s_fmt-from-multiple-sources-prop.patch;patch=1 \
file://0251-omap34xxcam-Requeue-faulty-buffers.patch;patch=1 \
file://0252-omap34xxcam-isp-updates.patch;patch=1 \
file://0253-omap34xxcam-Start-ISP-after-sensor.patch;patch=1 \
file://0254-omap34xxcam-Don-t-do-ISP-idle-mode-settings.patch;patch=1 \
file://0255-omap34xxcam-do-consult-isp_vbq_setup.patch;patch=1 \
file://0256-omap34xxcam-Power-down-slaves-at-streamoff-unless-v.patch;patch=1 \
file://0257-omap34xxcam-Remove-isp_buf_init.patch;patch=1 \
file://0258-omap34xxcam-Fix-module-author-e-mail.patch;patch=1 \
file://0259-omap34xxcam-Get-rid-of-hw-resources.patch;patch=1 \
file://0260-OMAP3430SDP-MT9P012-Get-rid-of-vdint-01-_timing.patch;patch=1 \
file://0261-OMAP-CAM-Add-OV3640-Sensor-Driver.patch;patch=1 \
file://0262-OMAP34XX-CAM-Add-OV3640-Sensor-Support.patch;patch=1 \
file://0263-OMAP3430SDP-CAM-Add-wait_hs_vs-field-in-isp-if-con.patch;patch=1 \
file://0264-Pad-configuration-for-OMAP3EVM-Multi-Media-Daughter.patch;patch=1 \
file://0265-OMAP3EVM-Multi-Media-Daughter-Card-Support.patch;patch=1 \
file://0266-OMAP3-ISP-Camera-Added-BT656-support-ontop-of-Nokia.patch;patch=1 \
file://0267-MMDC-patch-made-it-work-with-new-ISP-Camera-Nokia-fi.patch;patch=1 \
file://0268-Camera-Kconfig-option-changed-from-V4L2-DSS.patch;patch=1 \
file://0269-Bug-SDOCM00053646-YUYV-support-in-Capture-is-broken.patch;patch=1 \
file://0270-Put-support-for-TPS6235x-support.patch;patch=1 \
file://0271-Build-TPS6235x-based-PR785-board-support.patch;patch=1 \
file://0272-Fix-the-MMC-SD-hotplug-issue.patch;patch=1 \
file://0273-ALSA-ASOC-Added-OMAP3EVM-support.patch;patch=1 \
file://0274-ASoC-OMAP-Initialize-XCCR-and-RCCR-registers-in-Mc.patch;patch=1 \
file://0275-usb-defconfig-for-musb-and-ehci.patch;patch=1 \
file://0276-usb-defconfig-for-usb-audio.patch;patch=1 \
file://0277-usb-defconfig-for-usb-video.patch;patch=1 \
file://0278-usb-ehci-fix-companion-port-ownership-issue.patch;patch=1 \
file://0279-usb-musb-disable-ping-token-in-status-phase-of-con.patch;patch=1 \
file://0280-enable-Audio-as-part-of-OMAP3EVM-default-configurati.patch;patch=1 \
file://0281-Bug-SDOCM00053650-fixed-issue-of-g_input-tied-to-CV.patch;patch=1 \
file://0282-Backlight-driver-for-omap3evm.patch;patch=1 \
file://0283-Resizer-and-Previewer-driver-added-to-commit.patch;patch=1 \
file://0284-Resizer-bug-fixes-on-top-of-1.0.2-release.patch;patch=1 \
file://0285-backlight-driver-made-worked-with-DSS2.patch;patch=1 \
file://0286-defconfig-updated-for-video.patch;patch=1 \
file://0287-twl-API-moved-to-comiple-time-macro.patch;patch=1 \
file://0288-Audio-ASOC-OMAP3-EVM-support-added.patch;patch=1 \
file://0289-Enable-Video-driver-as-part-of-omap3evm-default-conf.patch;patch=1 \
file://0290-Add-macros-to-identify-Si-versions.patch;patch=1 \
file://0291-DSS2-library-enabled-from-v4l2-driver.patch;patch=1 \
file://0292-Interdependancy-between-ISP-and-Camera-removed.patch;patch=1 \
file://0293-PM-Fix-compile-error-with-CPU-Idle-enabled.patch;patch=1 \
file://0294-SMC911x-temporary-workaround-remove-phy-power-do.patch;patch=1 \
file://0295-Added-support-for-Background-color-in-DSS-library.patch;patch=1 \
file://0296-Added-Background-color-support-to-V4L2-driver.patch;patch=1 \
file://0297-Source-color-keying-support-in-DSS-library.patch;patch=1 \
file://0298-Added-src-color-keying-support-in-V4L2-driver.patch;patch=1 \
file://0299-Added-Alpha-blending-support-in-DSS-Library.patch;patch=1 \
file://0300-Alpha-blending-support-for-V4L2-driver.patch;patch=1 \
file://0301-Alpha-blending-support-for-frame-buffer-driver.patch;patch=1 \
file://0302-Merge-Conflict-fixed-in-arch-arm-plat-omap-dss-dpi.c.patch;patch=1 \
file://0303-Get-alpha-blending-support-added-in-DSS-Library.patch;patch=1 \
file://0304-Get-alpha-blending-support-added-in-V4L2-driver.patch;patch=1 \
file://0305-Added-WAIT_FOR_VSYNC-custom-ioctl-in-Frame-buffer-dr.patch;patch=1 \
file://0306-Minor-Fixes-in-V4L2-driver.patch;patch=1 \
file://0307-Bug-Solved-SDOCM00054702-Global-Alpha-value-not-r.patch;patch=1 \
file://0308-bug-solved-Rotation-ioctl-was-not-throwing-error-fo.patch;patch=1 \
file://0309-Bug-Solved-V4L2-display-driver-crash.patch;patch=1 \
file://0310-Added-bit-for-SRC_COLOR_KEY.patch;patch=1 \
file://0311-Color-Keying-added-to-V4L2-display-driver.patch;patch=1 \
file://0312-Bug-fix-in-alphablending-and-colorkeying-code.patch;patch=1 \
file://0313-Initialized-global-alpha-to-255-in-graphics-driver.patch;patch=1 \
file://0314-SDOCM00054583-G_CMAP-failure-issue-resolved.patch;patch=1 \
file://0315-Default-buffer-size-increased-to-support-720P-and-pa.patch;patch=1 \
file://0316-Cleaned-up-for-warning-massages.patch;patch=1 \
file://0317-SDOCM00053743-kernel-crash-hang-issue-on-wrong-rota.patch;patch=1 \
file://0318-DOCM00053743-rotation-with-720P-issue-fixed.patch;patch=1 \
file://0319-SDOCM00053743-issues-when-setting-rotation-if-rotat.patch;patch=1 \
file://0320-Issues-with-rotation-fixed.patch;patch=1 \
file://0321-Support-of-Power-Management-for-FBDEV.patch;patch=1 \
file://0322-720x576-50Hz-added-to-modeb.c-file.patch;patch=1 \
file://0323-OMAP3-PM-fix-compile-warning-when-CONFIG_SUSPEND.patch;patch=1 \
file://0324-OMAP3-PM-CPUfreq-support-for-OMAP3EVM-board.patch;patch=1 \
file://0325-Power-Management-working-with-FBDEV.patch;patch=1 \
file://0326-Power-Management-working-with-V4L2-Display.patch;patch=1 \
file://0327-Power-management-support-tested-with-both-FBDEV-and.patch;patch=1 \
file://0328-MMC-clock-enable-disable-for-pwoer-management.patch;patch=1 \
file://0329-Cleanup-PM-support-implementation.patch;patch=1 \
file://0330-Some-more-cleanup-of-PM-support-in-V4L2-and-FBDEV.patch;patch=1 \
file://0331-Isp-section-mismatch-warning-fixed.patch;patch=1 \
file://0332-More-Fixes-to-FBDEV-power-management.patch;patch=1 \
file://0333-Crash-fixed-in-FBDEV-power-managent.patch;patch=1 \
file://0334-PM-cpuidle-Revert-temporary-changes.patch;patch=1 \
file://0335-Audio-Adding-PM-code-in-audio-driver.patch;patch=1 \
file://0336-OMAP3-Fixed-crash-bug-with-serial-suspend.patch;patch=1 \
file://0337-Add-PSP-version-string-and-export-through-PROCFS.patch;patch=1 \
file://0338-usb-ehci-fix-EHCI-MSC-and-VIDEO-issue.patch;patch=1 \
file://0339-Enable-CPUIDLE-in-default-configuration.patch;patch=1 \
file://0340-Fix-for-compilation-issue-for-NAND-built-as-module.patch;patch=1 \
file://0341-Fix-compilation-issues-when-CONFIG_PM_SRF-is-enabled.patch;patch=1 \
file://0342-Fixed-minor-issue-in-FB-timer-resume-path.patch;patch=1 \
file://0343-Fixed-Suspend-Resume-path-in-dpi.c.patch;patch=1 \
file://0344-Suspend-Resume-support-added-to-FBDEV.patch;patch=1 \
file://0345-Suspend-Resume-moved-to-DSS2-Library.patch;patch=1 \
file://0346-omap34xxcam.c-cleaned-up.patch;patch=1 \
file://0347-OMAP3-PM-Fix-INTC-context-save-restore.patch;patch=1 \
file://0348-V4L2-Display-Added-query-control-for-Background-col.patch;patch=1 \
file://0349-DSS-Library-Suppressed-on-wrong-error-message.patch;patch=1 \
file://0350-OMAP3-PM-per-board-prm-timings.patch;patch=1 \
file://0351-OMAP-clock-export-notifier-register-functions.patch;patch=1 \
file://0352-OMAP3-PM-minor-cleanup-of-PRM-register-definitions.patch;patch=1 \
file://0353-OMAP3-PM-Update-voltage-levels-for-OPP1-2-on-VDD1.patch;patch=1 \
file://0354-OMAP3-PM-Fixed-glitches-in-GPIO-outputs-during-off.patch;patch=1 \
file://0355-OMAP3-GPIO-Removed-a-couple-of-unneeded-registers.patch;patch=1 \
file://0356-OMAP-clock-missing-list_del-for-clk_notifier_unreg.patch;patch=1 \
file://0357-PM-Fix-compile-warnings-with-CONFIG_OMAP_PM_SRF.patch;patch=1 \
file://0358-OMAP3-PM-CPUidle-Add-new-lower-latency-C1-state.patch;patch=1 \
file://0359-OMAP3-PM-idle-Remove-fclk-check-for-idle-loop.patch;patch=1 \
file://0360-OMAP3-PM-CPUidle-Start-C-state-definitions-from-b.patch;patch=1 \
file://0361-PM-cpuidle-Update-statistics-for-correct-state.patch;patch=1 \
file://0362-PM-cpuidle-fix-compile-warnings.patch;patch=1 \
file://0363-Transition-to-C2-via-omap3_idle_bm_check.patch;patch=1 \
file://0364-Change-default-configuration-for-OMAP3-EVM.patch;patch=1 \
file://0365-Fbdev-timeout-bug-fixed.patch;patch=1 \
file://0366-V4L2-Added-support-for-the-static-allocation-of-VRF.patch;patch=1 \
file://0367-V4L2-Added-support-to-pass-the-physicall-address-to.patch;patch=1 \
file://0368-DSS2-DVI-480P-Timing-parameters-changed.patch;patch=1 \
file://0369-V4L2-V4L2-was-breaking-when-two-framebuffer-interfa.patch;patch=1 \
file://0370-V4L2-Pixel-format-was-selected-wrong-when-two-frame.patch;patch=1 \
file://0371-Update-PSP-version-string.patch;patch=1 \
file://0372-Update-PSP-version-string-for-02.01.01.07-build.patch;patch=1 \
file://0373-Update-psp-version-string-for-release-02.01.01.08.patch;patch=1 \
file://0374-OMAP35x-Migrate-to-smsc911x-ethernet-driver-for-OM.patch;patch=1 \
file://0375-smsc911x-ethernet-driver-save-MAC-address-before-s.patch;patch=1 \
file://0376-DSS2-FBDEV-Panning-supported-with-rotation-on-Frame.patch;patch=1 \
file://0377-DSS2-Timing-parameters-of-LCD-changed-to-get-exact.patch;patch=1 \
file://0378-DSS2-Changed-omap3_evm_deconfig-to-select-DSI-as-th.patch;patch=1 \
file://0379-Audio-Rebasing-the-include-sound-folder-from-l-o-ti.patch;patch=1 \
file://0380-Audio-Rebasing-the-sound-folder-from-l-o-tip.patch;patch=1 \
file://0381-Audio-Rebasing-misc-kernel-files-from-l-o-tip.patch;patch=1 \
file://0382-ASoC-TWL4030-Fix-for-the-constraint-handling.patch;patch=1 \
file://0383-ASoC-TWL4030-Add-support-Voice-DAI.patch;patch=1 \
file://0384-ASoC-TWL4030-Add-VDL-path-support.patch;patch=1 \
file://0385-ASoC-TWL4030-Add-4-channel-TDM-support.patch;patch=1 \
file://0386-ASoC-TWL4030-Add-VDL-analog-bypass.patch;patch=1 \
file://0387-ASoC-TWL4030-Add-voice-digital-loopback-sidetone.patch;patch=1 \
file://0388-ASoC-TWL4030-Add-VIBRA-output.patch;patch=1 \
file://0389-ASoC-TWL4030-Fix-typo-in-twl4030_codec_mute-functi.patch;patch=1 \
file://0390-ASoC-TWL4030-change-DAPM-for-analog-microphone-sel.patch;patch=1 \
file://0391-ASoC-TWL4030-Enable-disable-voice-digital-filters.patch;patch=1 \
file://0392-ASoC-TWL4030-Fix-Analog-capture-path-for-AUXR.patch;patch=1 \
file://0393-ASoC-TWL4030-Add-control-for-selecting-codec-opera.patch;patch=1 \
file://0394-ASoC-TWL4030-Change-DAPM-routings-and-controls-for.patch;patch=1 \
file://0395-ASoC-TWL4030-Move-the-Headset-pop-attenuation-code.patch;patch=1 \
file://0396-ASoC-TWL4030-Add-support-for-platform-dependent-co.patch;patch=1 \
file://0397-ASoC-TWL4030-Differentiate-the-playback-streams.patch;patch=1 \
file://0398-ASoC-TWL4030-Handsfree-pop-removal-redesign.patch;patch=1 \
file://0399-ASoC-Display-return-code-when-failing-to-add-a-DAPM.patch;patch=1 \
file://0400-ASoC-Factor-out-application-of-power-for-generic-wi.patch;patch=1 \
file://0401-ASoC-Support-DAPM-events-for-DACs-and-ADCs.patch;patch=1 \
file://0402-ASoC-Factor-out-generic-widget-power-checks.patch;patch=1 \
file://0403-ASoC-Factor-out-DAPM-power-checks-for-DACs-and-ADCs.patch;patch=1 \
file://0404-ASoC-Make-the-DAPM-power-check-an-operation-on-the.patch;patch=1 \
file://0405-ASoC-Add-power-supply-widget-to-DAPM.patch;patch=1 \
file://0406-ASoC-Split-DAPM-power-checks-from-sequencing-of-pow.patch;patch=1 \
file://0407-ASoC-Make-DAPM-sysfs-entries-non-optional.patch;patch=1 \
file://0408-ASoC-Integrate-bias-management-with-DAPM-power-mana.patch;patch=1 \
file://0409-ASoC-Add-debug-trace-for-bias-level-transitions.patch;patch=1 \
file://0410-ASoC-SDP3430-Connect-twl4030-voice-DAI-to-McBSP3.patch;patch=1 \
file://0411-ASoC-SDP4030-Use-the-twl4030_setup_data-for-headse.patch;patch=1 \
file://0412-ASoC-Provide-core-support-for-symmetric-sample-rate.patch;patch=1 \
file://0413-ASoC-add-SOC_DOUBLE_EXT-macro.patch;patch=1 \
file://0414-AsoC-Make-snd_soc_read-and-snd_soc_write-functi.patch;patch=1 \
file://0415-ASoC-Remove-unused-DAI-format-defines.patch;patch=1 \
file://0416-ASoC-Remove-redundant-codec-pointer-from-DAIs.patch;patch=1 \
file://0417-ASoC-OMAP-Add-checking-to-detect-bufferless-pcms.patch;patch=1 \
file://0418-ASoC-TWL4030-Add-shadow-register.patch;patch=1 \
file://0419-ASoC-TWL4030-HandsfreeL-R-mute-DAPM-switch.patch;patch=1 \
file://0420-ASoC-TWL4030-Use-reg_cache-in-twl4030_init_chip.patch;patch=1 \
file://0421-ASoC-TWL4030-Check-the-interface-format-for-4-chan.patch;patch=1 \
file://0422-ASoC-Fix-build-error-in-twl4030.c.patch;patch=1 \
file://0423-ASoC-OMAP3EVM-Use-the-twl4030_setup_data-for-heads.patch;patch=1 \
file://0424-Audio-Adding-PM-code-in-audio-driver.patch;patch=1 \
file://0425-Update-psp-version-string-for-release-02.01.02.09.patch;patch=1 \
file://0426-smsc911x-ethernet-driver-save-MAC-address-before-s.patch;patch=1 \
"
