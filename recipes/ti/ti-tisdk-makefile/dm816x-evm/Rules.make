# Define target platform.
PLATFORM=dm816x-evm
KERNEL_VERSION=2.6.37
DEFAULT_LINUXKERNEL_CONFIG=ti8168_evm_defconfig
DEFAULT_UBOOT_CONFIG=ti8168_evm_config
MIN_UBOOT_CONFIG=ti8168_evm_min_sd
SYSLINK_PLATFORM=TI816X
GRAPHICS_PLATFORM=6.x
OMX_PLATFORM=ti816x-evm
OMTB_PLATFORM=ti816x-evm
MEDIA_CONTROLLER_UTILS_PLATFORM=ti816x
MATRIX_PLATFORM=ti816x
EDMA3_LLD_TARGET=edma3_lld_ti816x_dsp_libs

# The installation directory of the SDK.
EZSDK_INSTALL_DIR=<__SDK__INSTALL_DIR__>

# For backwards compatibility
DVSDK_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# For backwards compatibility
DVEVM_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# Where SYS/BIOS is installed.
SYSBIOS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__sysbios__>

# Where the OSAL package is installed.
OSAL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__osal__>

# OSAL build variables
OSAL_BUILD_VARS = CROSS_COMPILE=$(CSTOOL_PREFIX) \
		OSAL_INSTALL_DIR=$(OSAL_INSTALL_DIR)

# Where the IPC package is installed.
IPC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__ipc__>

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__codec-engine__>

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

# Where the Framework Components package is installed.
FC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__framework-components__>

# Where the MFC Linux Utils package is installed.
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
	KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR) 

# Where the Unified Instrumentation Architecture is installed.
UIA_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__uia__>

# Where OMX package is installed
OMX_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__omx__>

# OMX build variables
OMX_BUILD_VARS = ROOTDIR=$(OMX_INSTALL_DIR) \
		EXTERNAL_SW_ROOT=$(DVSDK_INSTALL_DIR)/component-sources \
		INTERNAL_SW_ROOT=$(OMX_INSTALL_DIR)/src \
        kernel_PATH=${LINUXKERNEL_INSTALL_DIR} \
        bios_PATH=${SYSBIOS_INSTALL_DIR} \
		fc_PATH=$(FC_INSTALL_DIR) \
		osal_PATH=$(OSAL_INSTALL_DIR) \
        xdais_PATH=${XDAIS_INSTALL_DIR} \
		linuxutils_PATH=$(LINUXUTILS_INSTALL_DIR) \
        ce_PATH=${CE_INSTALL_DIR} \
        aaclcdec_PATH=${AACLCDEC_INSTALL_DIR} \
		ipc_PATH=$(IPC_INSTALL_DIR) \
		syslink_PATH=$(SYSLINK_INSTALL_DIR) \
		xdc_PATH=$(XDC_INSTALL_DIR) \
		uia_PATH=$(UIA_INSTALL_DIR) \
        slog_PATH=${SLOG_INSTALL_DIR} \
        lindevkit_PATH=${LINUX_DEVKIT_DIR}/arm-arago-linux-gnueabi/usr \
		PLATFORM=$(OMX_PLATFORM) \
        EXAMPLES_ROOT=${OMX_INSTALL_DIR}/examples \
		CODEGEN_PATH_A8=$(CSTOOL_DIR) \
        CODEGEN_PATH_DSPELF=${CODEGEN_INSTALL_DIR}

# Where OMTB package is installed
OMTB_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/example-applications/<__omtb__>

# OMTB build variables
OMTB_BUILD_VARS = ROOTDIR=$(OMTB_INSTALL_DIR) \
		PLATFORM=$(OMTB_PLATFORM) \
		OMTB_ROOT=$(OMTB_INSTALL_DIR) \
		EZSDK_INSTALL_DIR=$(DVSDK_INSTALL_DIR) \
		DEST_ROOT=$(OMTB_INSTALL_DIR)/bin \
		OMX_INSTALL_DIR=$(OMX_INSTALL_DIR)/src \
		fc_PATH=$(FC_INSTALL_DIR) \
		uia_PATH=$(UIA_INSTALL_DIR) \
		ce_PATH=$(CE_INSTALL_DIR) \
		osal_PATH=$(OSAL_INSTALL_DIR) \
		linuxutils_PATH=$(LINUXUTILS_INSTALL_DIR) \
		ipc_PATH=$(IPC_INSTALL_DIR) \
		syslink_PATH=$(SYSLINK_INSTALL_DIR) \
		xdc_PATH=$(XDC_INSTALL_DIR) \
		lindevkit_PATH=$(LINUX_DEVKIT_DIR)/arm-arago-linux-gnueabi/usr \
		CODEGEN_PATH_A8=$(CSTOOL_DIR)

# Where AAC-LC Decoder package is installed
AACLCDEC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__c674x-aaclcdec__>

# Where SLOG package is installed
SLOG_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__slog__>

# Where Media Controller Utils package is installed
MEDIA_CONTROLLER_UTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support/<__media-controller-utils__>

# Meda Controller Utils build variables
MEDIA_CONTROLLER_UTILS_BUILD_VARS = PLATFORM=$(MEDIA_CONTROLLER_UTILS_PLATFORM) \
		SYSLINK_INSTALL_DIR=$(SYSLINK_INSTALL_DIR) \
		CC=$(CSTOOL_PREFIX)gcc \
		DESTDIR=$(EXEC_DIR) prefix=/usr \
        KERNEL_VERSION=$(KERNEL_VERSION) \
        LINUXKERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR) \
        MAKE_ENV=ARCH=arm CROSS_COMPILE=$(CSTOOL_PREFIX)


GST_OMX_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__gst-openmax-ti__>

GST_OMX_BUILD_VARS = BIOS_INSTALL_DIR=$(SYSBIOS_INSTALL_DIR) \
		 IPC_INSTALL_DIR=$(IPC_INSTALL_DIR) \
		 XDC_INSTALL_DIR=$(XDC_INSTALL_DIR) \
		 FC_INSTALL_DIR=$(FC_INSTALL_DIR) \
		 CE_INSTALL_DIR=$(CE_INSTALL_DIR) \
		 OSAL_INSTALL_DIR=$(OSAL_INSTALL_DIR) \
		 UIA_INSTALL_DIR=$(UIA_INSTALL_DIR) \
		 SYSLINK_INSTALL_DIR=$(SYSLINK_INSTALL_DIR) \
		 LINUXUTILS_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR) \
		 OMX_INSTALL_DIR=$(OMX_INSTALL_DIR) \
		 LINUXKERNEL=$(LINUXKERNEL_INSTALL_DIR) \
		 KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR) \
		 OMX_PLATFORM=$(OMX_PLATFORM) \
		 CROSS_COMPILE=$(CSTOOL_PREFIX)

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=<__CROSS_COMPILER_PATH__>
CSTOOL_LONGNAME=bin/arm-arago-linux-gnueabi-gcc
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-arago-linux-gnueabi-
CSTOOL_PATH=$(CSTOOL_DIR)/bin

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where the devkits are located
LINUX_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/linux-devkit
DSP_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/dsp-devkit

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)
