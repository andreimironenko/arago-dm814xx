# Define target platform.
PLATFORM=dm814x-evm

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

# Where the IPC package is installed.
IPC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__ipc__>

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__codec-engine__>

# Where the SYS Link package is installed.
SYSLINK_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__syslink__>

# Where the EDMA3 LLD package is installed.
EDMA3_LLD_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__edma3lld__>
EDMA3LLD_INSTALL_DIR=$(EDMA3_LLD_INSTALL_DIR)

# Where the Framework Components package is installed.
FC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__framework-components__>

# Where the MFC Linux Utils package is installed.
LINUXUTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__linuxutils__>
CMEM_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR)

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__xdais__>

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/dsp-devkit/<__xdctools__>

# Where the Code Gen is installed.
CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/dsp-devkit/<__cgt6x__>

# Where the PSP is installed.
PSP_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/board-support/<__kernel__>
KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR)

# Where the development headers and libs are installed.
LINUX_DEVKIT_DIR=$(DVSDK_INSTALL_DIR)/linux-devkit

# Where the dsp image library is installed
IMGLIB_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__imglib-c64plus__>

# Where the dsp library is installed
DSPLIB_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__dsplib-c674x__>

# Where the dsp math library is installed
MATHLIB_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__mathlib-c67x__>

# Where c6accel package is installed.
C6ACCEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__c6accel__>

# Where c6run package is installed.
C6RUN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__c6run__>

# Where opengl graphics package is installed.
GRAPHICS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__libgles-omap3__>

# Where signal analyzer package is installed
SADEMO_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/example-applications/<__signal-analyzer-demo__>

# Where the Unified Instrumentation Architecture is installed.
UIA_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__uia__>

# Where OMX package is installed
OMX_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__omx__>

# Where OMTB package is installed
OMTB_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/example-applications/<__omtb__>

# Where SLOG package is installed
SLOG_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/component-sources/<__slog__>

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=<__CROSS_COMPILER_PATH__>
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-
CSTOOL_PATH=$(CSTOOL_DIR)/bin

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)


