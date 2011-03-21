require flash-utility.inc

PR = "${INC_PR}.0"

SRC_URI = "\
    https://gforge.ti.com/gf/download/frsrelease/480/4265/setup.exe;name=setup \
    https://gforge.ti.com/gf/download/frsrelease/480/4264/FlashInstaller.msi;name=flashinstaller \
    "

SRC_URI[setup.md5sum] = "1b1842959830c272a4dce30cbb5ae895"
SRC_URI[setup.sha256sum] = "7789df27165cbff7b3befa0686d74c9ef845cff35fcd315d59029502c5c9adbf"

SRC_URI[flashinstaller.md5sum] = "d0687be1c646d10889fe26f85e324cd3"
SRC_URI[flashinstaller.sha256sum] = "3d348a8193afd8fe94c34a7e9b07bbea109f8d6af0596437e72b24b0556b315a"
