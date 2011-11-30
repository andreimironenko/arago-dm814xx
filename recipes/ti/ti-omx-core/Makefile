# Makefile to build shared lib
-include Rules.make

MAJOR_NUMBER := 5
MINOR_NUMBER := 2

LIBTARGET := libtiomx.so
OLDLIBTARGET := libOMX_Core

DESTDIR=.
prefix=/usr
libdir=/lib

all:
	@mkdir -p lib
	@echo "  CCLD   lib/$(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER)"
	@$(CROSS_COMPILE)gcc -Wl,-soname,libOMX_Core -shared -Xlinker --start-group \
	$(OMX_INSTALL_DIR)/lib/omxcore.av5T \
	$(OMX_INSTALL_DIR)/lib/domx.av5T \
    $(OMX_INSTALL_DIR)/lib/memcfg.av5T \
    $(OMX_INSTALL_DIR)/lib/timmosal.av5T \
	$(OMX_INSTALL_DIR)/lib/domx_delegates_shmem.av5T \
	$(OMX_INSTALL_DIR)/lib/omxcfg.av5T \
	$(SYSLINK_INSTALL_DIR)/packages/ti/syslink/lib/syslink.a_debug \
	$(FC_INSTALL_DIR)/packages/ti/sdo/rcm/lib/debug/rcm_syslink.av5T \
	$(FC_INSTALL_DIR)/packages/ti/sdo/fc/memutils/lib/release/memutils.av5T \
	$(FC_INSTALL_DIR)/packages/ti/sdo/fc/global/lib/debug/fcsettings.av5T \
	$(OSAL_INSTALL_DIR)/packages/linuxdist/build/lib/osal.a \
	$(OSAL_INSTALL_DIR)/packages/linuxdist/cstubs/lib/cstubs.a \
	$(LINUXUTILS_INSTALL_DIR)/packages/ti/sdo/linuxutils/cmem/lib/cmem.a470MV \
	$(UIA_INSTALL_DIR)/packages/ti/uia/linux/lib/servicemgr.a -Xlinker --end-group \
	-Wl,-u,OMX_Init \
	-Wl,-u,OMX_Deinit \
	-Wl,-u,OMX_ComponentNameEnum \
	-Wl,-u,OMX_GetHandle \
	-Wl,-u,OMX_FreeHandle \
	-Wl,-u,OMX_GetRolesOfComponent \
	-pthread -lrt -o lib/$(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER) -Wl,--fix-cortex-a8 $(CFLAGS) $(LDFLAGS)

clean:
	rm -rf lib

install: all
	install -d $(DESTDIR)$(prefix)$(libdir)
	install lib/$(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER) $(DESTDIR)$(prefix)$(libdir)/$(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER)
	ln -sf $(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER) $(DESTDIR)$(prefix)$(libdir)/$(LIBTARGET).$(MAJOR_NUMBER)
	ln -sf $(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER) $(DESTDIR)$(prefix)$(libdir)/$(LIBTARGET)
	ln -sf $(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER) $(DESTDIR)$(prefix)$(libdir)/$(OLDLIBTARGET).so
	ln -sf $(LIBTARGET).$(MAJOR_NUMBER).$(MINOR_NUMBER) $(DESTDIR)$(prefix)$(libdir)/$(OLDLIBTARGET)

distdir: 
	mkdir -p $(distdir)
	cp -f Makefile $(distdir)

distclean:
