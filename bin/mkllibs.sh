#!/bin/sh

FILE=$1
shift

if [ "x$FILE" = "x" ]; then
   echo "Usage:  $0 <arago sdk tarball>"
   exit 0
fi

ARAGO_VER=`echo $FILE | sed "s%^.*arago-\(....\...\)-\(arm.*\)-linux.*%\1%"`
ARM_VER=`echo $FILE | sed "s%^.*arago-\(....\...\)-\(arm.*\)-linux.*%\2%"`

LLDIR=./linuxlibs-${ARAGO_VER}-${ARM_VER}
ARAGO_DIR=./arago_sdk-${ARAGO_VER}-${ARM_VER}
mkdir ${LLDIR} ${ARAGO_DIR}

tar xzvf $FILE -C ${ARAGO_DIR}
#tar xzvf ../archives/arago-2009.09/arago-2009.09-armv7a-linux-gnueabi-sdk.tar.gz -C ${ARAGO_DIR}

rsync -av ${ARAGO_DIR}/opt/arago-*/include                          ${LLDIR}/
rsync -av ${ARAGO_DIR}/opt/arago-*/arm-*-linux-gnueabi/usr/include/ ${LLDIR}/include/
rsync -av ${ARAGO_DIR}/opt/arago-*/arm-*-linux-gnueabi/usr/lib      ${LLDIR}/
rsync -av ${ARAGO_DIR}/opt/arago-*/share                            ${LLDIR}/
rsync -av ${ARAGO_DIR}/opt/arago-*/arm-*-linux-gnueabi/etc/terminfo ${LLDIR}/share/
rsync -av ${ARAGO_DIR}/opt/arago-*/arm-*-linux-gnueabi/etc/terminfo ${LLDIR}/lib/

# installed=yes
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%^installed=no%installed=yes%"

# libdir=$LINUXLIBS_INSTALL_DIR
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%^libdir='/usr%libdir=\$LINUXLIBS_INSTALL_DIR'%"
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%^libdir='%libdir=\$LINUXLIBS_INSTALL_DIR'%"

# Remove $TOOLCHAIN_PATH
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%-L\$TOOLCHAIN_PATH/\$TARGET_SYS/libc/lib\ %%"
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%-L\$TOOLCHAIN_PATH/\$TARGET_SYS/libc/lib'%'%"

# Remove SDK_PATH.../usr/lib
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%-L\$SDK_PATH/\$TARGET_SYS/usr/lib\ %%"
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%-L\$SDK_PATH/\$TARGET_SYS/usr/lib'%'%"

# Remove SDK_PATH.../usr/lib/lib (?)
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%-L\$SDK_PATH/\$TARGET_SYS/usr/lib/lib\ %%"
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%-L\$SDK_PATH/\$TARGET_SYS/usr/lib/lib'%'%"

# Replace SDK_PATH.../usr/lib/libxyz.la with -lxyz
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%\$SDK_PATH/\$TARGET_SYS/usr/lib/lib\(.*\)\.la%-l\1%"

# Replace dependency_libs=' ' with dependency_libs=''
# Shouldn't be needed after cleaning up the above
find ${LLDIR} -name "*.la" -type f | xargs sed -i "s%^dependency_libs=' '%dependency_libs=''%"

tar cvf ${LLDIR}.tar ${LLDIR}
gzip --best ${LLDIR}.tar

rm -rf ${ARAGO_DIR}
rm -rf ${LLDIR}
