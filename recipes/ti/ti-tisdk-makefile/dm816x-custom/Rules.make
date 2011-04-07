# Define target platform.
PLATFORM=dm816x-evm

# The installation directory of the SDK.
EZSDK_INSTALL_DIR=<__SDK__INSTALL_DIR__>

# For backwards compatibility
DVSDK_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# For backwards compatibility
DVEVM_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# Where SYS/BIOS is installed.
SYSBIOS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__sysbios__>

# Where the OSAL package is installed.
OSAL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__osal__>

# Where the IPC package is installed.
IPC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__ipc__>

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__codec-engine__>

# Where the SYS Link package is installed.
SYSLINK_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__syslink__>

# Where the EDMA3 LLD package is installed.
EDMA3_LLD_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__edma3lld__>
EDMA3LLD_INSTALL_DIR=$(EDMA3_LLD_INSTALL_DIR)

# Where the Framework Components package is installed.
FC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__framework-components__>

# Where the MFC Linux Utils package is installed.
LINUXUTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__linuxutils__>
CMEM_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR)

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__xdais__>

# Where the codec packages are installed
CODEC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__codecs-dm816x__>

# Where the IVAHD VICP20 API package is installed
IVAHD_HDVICP20API_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__ivahd-hdvicp20api__>

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__xdctools__>

# Where the Code Gen is installed.
CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__cgt6x__>

# Where the TMS470 Code gen is installed
TMS470_CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__cgt470__>

# Where the PSP is installed.
PSP_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/psp

# Where the HDVPSS package is installed.
HDVPSS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__hdvpss__>

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/psp/<__kernel__>
KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR)

# Where the development headers and libs are installed.
LINUX_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/linux-devkit

# Where c6accel package is installed.
C6ACCEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__c6accel__>


# Where opengl graphics package is installed.
GRAPHICS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__libgles-omap3__>

# Where signal analyzer package is installed
SADEMO_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/demos/<__signal-analyzer-demo__>

# Where OMX package is installed
OMX_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__omx__>

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=<__CROSS_COMPILER_PATH__>
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-
CSTOOL_PATH=$(CSTOOL_DIR)/bin

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)


