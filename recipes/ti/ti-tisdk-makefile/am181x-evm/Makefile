-include Rules.make

all: matrix_gui matrix_tui am_benchmarks am_sysinfo


matrix_gui:
	@echo =============================
	@echo      Building Matrix GUI - Release version
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-gui-e*"`; make -f Makefile.build release

matrix_gui_debug:
	@echo =============================
	@echo      Building Matrix GUI - Debug version
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-gui-e*"`; make -f Makefile.build debug

matrix_tui:
	@echo =============================
	@echo      Building Matrix TUI
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-tui*"`; make all

am_benchmarks:
	@echo =============================
	@echo    Building AM Benchmarks
	@echo =============================
	@cd example-applications; cd `find . -name "*am-benchmarks*"`; make

am_sysinfo:
	@echo =============================
	@echo    Building AM Sysinfo
	@echo =============================
	@cd example-applications; cd `find . -name "*am-sysinfo*"`; make

matrix_gui_clean:
	@echo =============================
	@echo      Cleaning Matrix GUI
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-gui-e*"`; \
	 make -f Makefile.build clean; \
	 rm -f Makefile Makefile.Debug Makefile.Release

matrix_tui_clean:
	@echo =============================
	@echo      Cleaning Matrix TUI
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-tui*"`; make clean

am_benchmarks_clean:
	@echo =============================
	@echo    Cleaning AM Benchmarks
	@echo =============================
	@cd example-applications; cd `find . -name "*am-benchmarks*"`; make clean

am_sysinfo_clean:
	@echo =============================
	@echo    Cleaning AM Sysinfo
	@echo =============================
	@cd example-applications; cd `find . -name "*am-sysinfo*"`; make clean

clean: matrix_gui_clean matrix_tui_clean am_benchmarks_clean am_sysinfo_clean

matrix_gui_install:
	@echo =============================
	@echo     Installing Matrix GUI - Release version
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-gui-e*"`; make -f Makefile.build install;

matrix_gui_install_debug:
	@echo =============================
	@echo     Installing Matrix GUI - Debug version
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-gui-e*"`; make -f Makefile.build install_debug;

matrix_tui_install:
	@echo =============================
	@echo     Installing Matrix TUI - Release version
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-tui*"`; make install

matrix_tui_install_debug:
	@echo =============================
	@echo     Installing Matrix TUI - Debug version
	@echo =============================
	@cd example-applications; cd `find . -name "*matrix-tui*"`; make install_debug

am_benchmarks_install:
	@echo =============================
	@echo   Installing AM Benchmarks - Release version
	@echo =============================
	@cd example-applications; cd `find . -name "*am-benchmarks*"`; make install

am_benchmarks_install_debug:
	@echo =============================
	@echo   Installing AM Benchmarks - Debug Version
	@echo =============================
	@cd example-applications; cd `find . -name "*am-benchmarks*"`; make install_debug

am_sysinfo_install:
	@echo =============================
	@echo     Installing AM Sysinfo - Release version
	@echo =============================
	@cd example-applications; cd `find . -name "*am-sysinfo*"`; make install

am_sysinfo_install_debug:
	@echo =============================
	@echo     Installing AM Sysinfo - Debug version
	@echo =============================
	@cd example-applications; cd `find . -name "*am-sysinfo*"`; make install_debug

install: matrix_gui_install matrix_tui_install am_benchmarks_install am_sysinfo_install
install_debug: matrix_gui_install_debug matrix_tui_install_debug am_benchmarks_install_debug am_sysinfo_install_debug

