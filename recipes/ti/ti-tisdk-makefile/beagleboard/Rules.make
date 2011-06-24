#platform
PLATFORM=beagleboard

#Architecture
ARCH=armv7a

#Points to the root of the TI SDK
export TI_SDK_PATH=__SDK__INSTALL_DIR__

#root of the target file system for installing applications
DESTDIR=__DESTDIR__

#Points to the root of the Linux libraries and headers matching the
#demo file system.
export LINUX_DEVKIT_PATH=$(TI_SDK_PATH)/linux-devkit

#Cross compiler prefix
export CROSS_COMPILE=$(LINUX_DEVKIT_PATH)/bin/arm-arago-linux-gnueabi-

#Location of environment-setup file
export ENV_SETUP=$(LINUX_DEVKIT_PATH)/environment-setup

#The directory that points to the SDK kernel source tree
LINUXKERNEL_INSTALL_DIR=$(TI_SDK_PATH)/psp/__KERNEL_NAME__

