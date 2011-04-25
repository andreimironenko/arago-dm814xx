require ti-hdvpss.inc

PV = "01_00_01_26"

SRC_URI[hdvpss.md5sum] = "3c97e83e7400470f638f2edcd711315f"
SRC_URI[hdvpss.sha256sum] = "5d61f77358a790edbdca8a80768d2a40ae91e8710f4bd1a89f0ad0be0e62552e"

SRC_URI =+ "file://0001-hdvpss-Generic-platform-files.patch"
SRC_URI =+ "file://0002-hdvpss-TI814x-platform-files.patch"
SRC_URI =+ "file://0003-hdvpss-TI-16x-platform-files.patch"
