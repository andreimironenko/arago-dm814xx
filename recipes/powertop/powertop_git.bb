require recipes/powertop/powertop.inc

SRCREV = "a1bf653fb3825f50effb3cd987ad0b0775516957"
PV = "1.13"
PR = "${INC_PR}.0"
PR_append = "+gitr${SRCPV}"

SRC_URI = "git://gitorious.org/meego-developer-tools/powertop.git;protocol=git"
S = "${WORKDIR}/git"

CFLAGS += "-DVERSION=\\"${PV}\\" -Wall -Wshadow -W -Wformat -Wimplicit-function-declaration -Wimplicit-int"

do_configure() {
    # We do not build ncurses with wide char support
    sed -i -e "s/lncursesw/lncurses/" ${S}/Makefile
}
