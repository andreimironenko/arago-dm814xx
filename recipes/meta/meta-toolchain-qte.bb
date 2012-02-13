# Qt Embedded toolchain
PR = "r4"
TOOLCHAIN_HOST_TASK = "task-qte-toolchain-host"
TOOLCHAIN_TARGET_TASK = "task-qte-toolchain-target"

require meta-toolchain-arago.bb

SDK_SUFFIX = "qte-sdk"

QT_DIR_NAME = "qtopia"

do_populate_sdk_append() {
       script="${SDK_OUTPUT}/${SDKPATH}/environment-setup"
       touch $script
       echo 'export OE_QMAKE_CC=${TARGET_SYS}-gcc' >> $script
       echo 'export OE_QMAKE_CXX=${TARGET_SYS}-g++' >> $script
       echo 'export OE_QMAKE_LINK=${TARGET_SYS}-g++' >> $script
       echo 'export OE_QMAKE_LIBDIR_QT=$SDK_PATH/$TARGET_SYS${libdir}' >> $script
       echo 'export OE_QMAKE_INCDIR_QT=$SDK_PATH/$TARGET_SYS${includedir}/${QT_DIR_NAME}' >> $script
       echo 'export OE_QMAKE_MOC=$SDK_PATH/bin/moc4' >> $script
       echo 'export OE_QMAKE_UIC=$SDK_PATH/bin/uic4' >> $script
       echo 'export OE_QMAKE_UIC3=$SDK_PATH/bin/uic34' >> $script
       echo 'export OE_QMAKE_RCC=$SDK_PATH/bin/rcc4' >> $script
       echo 'export OE_QMAKE_QDBUSCPP2XML=$SDK_PATH/bin/qdbuscpp2xml4' >> $script
       echo 'export OE_QMAKE_QDBUSXML2CPP=$SDK_PATH/bin/qdbusxml2cpp4' >> $script
       echo 'export OE_QMAKE_QT_CONFIG=$SDK_PATH/$TARGET_SYS${datadir}/${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
       echo 'export QMAKESPEC=$SDK_PATH/$TARGET_SYS${datadir}/${QT_DIR_NAME}/mkspecs/linux-g++' >> $script

       #Adds qt.conf file that points qmake to properly locate Qt library and header files.
       #This enables Qt Creator to work properly

       qt_conf="${SDK_OUTPUT}/${SDKPATH}/bin/qt.conf"
       touch $qt_conf
       echo '[Paths]' >> $qt_conf
       echo 'Prefix = $(SDK_PATH)' >> $qt_conf
       echo 'Libraries = $(OE_QMAKE_LIBDIR_QT)' >> $qt_conf
       echo 'Headers = $(OE_QMAKE_INCDIR_QT)' >> $qt_conf

       # Repack SDK with new environment-setup
       cd ${SDK_OUTPUT}
       fakeroot tar cfz ${SDK_DEPLOY}/${TOOLCHAIN_OUTPUTNAME}.tar.gz .
}
