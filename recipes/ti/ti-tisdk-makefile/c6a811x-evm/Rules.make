# Define target platform.
PLATFORM=c6a811x-evm
KERNEL_VERSION=2.6.37
DEFAULT_LINUXKERNEL_CONFIG=ti811x_evm_defconfig
DEFAULT_UBOOT_CONFIG=ti811x_evm_config_sd
MIN_UBOOT_CONFIG=ti811x_evm_min_sd
SYSLINK_PLATFORM=TI811X
GRAPHICS_PLATFORM=6.x
RPE_PLATFORM=ti811x-evm
HDVPSS_PLATFORM=ti811x-evm
MEDIA_CONTROLLER_UTILS_PLATFORM=ti811x-evm
MATRIX_PLATFORM=ti811x
EDMA3_LLD_TARGET=edma3_lld_ti814x_dsp_libs

# The installation directory of the SDK.
EZSDK_INSTALL_DIR=<__SDK__INSTALL_DIR__>

# For backwards compatibility
DVSDK_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# For backwards compatibility
DVEVM_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# Where SYS/BIOS is installed.
SYSBIOS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__sysbios__>

# Where the IPC package is installed.
IPC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__ipc__>

# Where the SYS Link package is installed.
SYSLINK_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__syslink__>

# Syslink build variables
SYSLINK_BUILD_VARS = DEVICE=$(SYSLINK_PLATFORM) \
	GPPOS=Linux \
	LOADER=ELF \
	SDK=EZSDK \
	IPC_INSTALL_DIR="${IPC_INSTALL_DIR}" \
	BIOS_INSTALL_DIR="${SYSBIOS_INSTALL_DIR}" \
	XDC_INSTALL_DIR="${XDC_INSTALL_DIR}" \
	LINUXKERNEL="${LINUXKERNEL_INSTALL_DIR}" \
	CGT_ARM_INSTALL_DIR="${CSTOOL_DIR}" \
	CGT_ARM_PREFIX="${CSTOOL_PREFIX}" \
	CGT_C674_ELF_INSTALL_DIR="${CODEGEN_INSTALL_DIR}" \
	USE_SYSLINK_NOTIFY=0

# Where the EDMA3 LLD package is installed.
EDMA3_LLD_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__edma3lld__>
EDMA3LLD_INSTALL_DIR=$(EDMA3_LLD_INSTALL_DIR)

# EDMA3 LLD build variables
EDMA3_LLD_BUILD_VARS = ROOTDIR=$(EDMA3_LLD_INSTALL_DIR) \
		INTERNAL_SW_ROOT=$(EDMA3_LLD_INSTALL_DIR) \
		EXTERNAL_SW_ROOT=$(DVSDK_INSTALL_DIR) \
		edma3_lld_PATH=$(EDMA3_LLD_INSTALL_DIR) \
		bios_PATH=$(SYSBIOS_INSTALL_DIR) \
		xdc_PATH=$(XDC_INSTALL_DIR) \
		CODEGEN_PATH_DSPELF=$(CODEGEN_INSTALL_DIR) \
		FORMAT=ELF

LINUXUTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__linuxutils__>
CMEM_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR)

# CMEM build variables
CMEM_BUILD_VARS = RULES_MAKE=../../../../../../../../../Rules.make

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__xdais__>

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__xdctools__>

# Where the Code Gen is installed.
CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/dsp-devkit/<__cgt6x__>

# Where the Media Controller Code Gen is installed
MEDIA_CONTROLLER_CODECGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/media-controller-devkit/<__cgt470__>

# Where the PSP is installed.
PSP_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support/<__kernel__>
KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR)

# PSP Kernel/U-Boot build variables
LINUXKERNEL_BUILD_VARS = ARCH=arm CROSS_COMPILE=$(CSTOOL_PREFIX)
UBOOT_BUILD_VARS = CROSS_COMPILE=$(CSTOOL_PREFIX)

# Where opengl graphics package is installed.
GRAPHICS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__libgles-omap3__>

# Graphics build variables
GRAPHICS_BUILD_VARS = BUILD=release \
	OMAPES=$(GRAPHICS_PLATFORM) \
	GRAPHICS_INSTALL_DIR=$(GRAPHICS_INSTALL_DIR) \
	CSTOOL_DIR=$(CSTOOL_DIR) \
	CROSS_COMPILE=$(CSTOOL_PREFIX) \
	KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR) 

# Where the Unified Instrumentation Architecture is installed.
UIA_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__uia__>

# Where AAC-LC Decoder package is installed
AACLCDEC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__c674x-aaclcdec__>

# Where Media Controller Utils package is installed
MEDIA_CONTROLLER_UTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support/<__media-controller-utils__>

# Meda Controller Utils build variables
MEDIA_CONTROLLER_UTILS_BUILD_VARS = PLATFORM=$(MEDIA_CONTROLLER_UTILS_PLATFORM) \
		ROOTDIR=$(MEDIA_CONTROLLER_UTILS_INSTALL_DIR) \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
		ipc_PATH=$(IPC_INSTALL_DIR) \
		syslink_PATH=$(SYSLINK_INSTALL_DIR) \
		xdc_PATH=$(XDC_INSTALL_DIR) \
		DESTDIR=$(EXEC_DIR) prefix=/usr \
		CODEGEN_PATH_A8=$(CSTOOL_DIR) \
        CODEGEN_PATH_DSPELF=${CODEGEN_INSTALL_DIR}

# Where RPE package is installed
RPE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__rpe__>

# RPE build variables
RPE_BUILD_VARS = ROOTDIR=$(RPE_INSTALL_DIR) \
		EXTERNAL_SW_ROOT=$(DVSDK_INSTALL_DIR)/component-sources \
		INTERNAL_SW_ROOT=$(RPE_INSTALL_DIR)/src \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
		linuxutils_PATH=$(LINUXUTILS_INSTALL_DIR) \
        aaclcdec_PATH=${AACLCDEC_INSTALL_DIR} \
		ipc_PATH=$(IPC_INSTALL_DIR) \
		syslink_PATH=$(SYSLINK_INSTALL_DIR) \
		xdc_PATH=$(XDC_INSTALL_DIR) \
		uia_PATH=$(UIA_INSTALL_DIR) \
		mcutils_PATH=$(MEDIA_CONTROLLER_UTILS_INSTALL_DIR) \
        lindevkit_PATH=${LINUX_DEVKIT_DIR}/arm-none-linux-gnueabi/usr \
        RPE_USEAACDEC=1 \
        RPE_USEAACENC=0 \
        RPE_USEMP3DEC=0 \
		PLATFORM=$(RPE_PLATFORM) \
		CODEGEN_PATH_A8=$(CSTOOL_DIR) \
		CROSS_COMPILE=arm-none-linux-gnueabi- \
		TOOLCHAIN_LONGNAME=arm-none-linux-gnueabi \
        CODEGEN_PATH_DSPELF=${CODEGEN_INSTALL_DIR}

TVP5158_INIT_APP_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support/<__j5eco-tvp5158__>/TVP5158_init_app
TVP5158_INIT_APP_BUILD_VARS= CROSS_COMPILE=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi- \
                             KERNEL_DIR=$(LINUXKERNEL_INSTALL_DIR)
                             
HDVPSS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__hdvpss__>
HDVPSS_BUILD_VARS=CODEGEN_PATH_M3=(MEDIA_CONTROLLER_CODECGEN_INSTALL_DIR) \
                    hdvpss_PATH=$(HDVPSS_INSTALL_DIR) \
                    ROOTDIR=$(HDVPSS_INSTALL_DIR) \
                    bios_PATH=${SYSBIOS_INSTALL_DIR} \
                    xdc_PATH=$(XDC_INSTALL_DIR) \
                    ipc_PATH=$(IPC_INSTALL_DIR) \
                    XDCPATH="${SYSBIOS_INSTALL_DIR}/packages;$(XDC_INSTALL_DIR)/packages;$(IPC_INSTALL_DIR)/packages;$(HDVPSS_INSTALL_DIR)/packages" \
                    PLATFORM=$(HDVPSS_PLATFORM) \
                    PROFILE_m3vpss=debug \
                    ISA=m3 \
                    CORE=m3vpss

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=<__CROSS_COMPILER_PATH__>
CSTOOL_LONGNAME=bin/arm-none-linux-gnueabi-gcc
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-
CSTOOL_PATH=$(CSTOOL_DIR)/bin

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where the devkits are located
LINUX_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/linux-devkit
DSP_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/dsp-devkit
MEDIA_CONTROLLER_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/media-controller-devkit

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)
