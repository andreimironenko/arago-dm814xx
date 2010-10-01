# Define target platform.
PLATFORM=c6a816x-evm

# The installation directory of the SDK.
EZSDK_INSTALL_DIR=/home/dvsdkval/ti-ezsdk_c6a816x-evm_5_00_00_20100930

# For backwards compatibility
DVSDK_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# For backwards compatibility
DVEVM_INSTALL_DIR=$(EZSDK_INSTALL_DIR)

# Where SYS/BIOS is installed.
SYSBIOS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/sysbios_6_30_03_43_eng

# Where the OSAL package is installed.
OSAL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/osal_1_20_00_08_eng

# Where the IPC package is installed.
IPC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/ipc_1_21_03_24_eng

# Where the Codec Engine package is installed.
CE_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/codec-engine_3_20_00_13_eng

# Where the SYS Link package is installed.
SYSLINK_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/syslink_02_00_00_53_alpha2

# Where the EDMA3 LLD package is installed.
EDMA3_LLD_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/edma3lld_01_11_00_03
EDMA3LLD_INSTALL_DIR=$(EDMA3_LLD_INSTALL_DIR)

# Where the Framework Components package is installed.
FC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/framework-components_3_20_00_15_eng

# Where the MFC Linux Utils package is installed.
LINUXUTILS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/linuxutils_3_20_00_01_eng
CMEM_INSTALL_DIR=$(LINUXUTILS_INSTALL_DIR)

# Where the XDAIS package is installed.
XDAIS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/xdais_7_20_00_04_eng

# Where the RTSC tools package is installed.
XDC_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/xdctools_3_20_03_63

# Where the Code Gen is installed.
CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/cgt6x_7_2_0

# Where the Code Gen is installed.
TMS470_CODEGEN_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/cgt-tms470_4_6_1

# Where the PSP is installed.
PSP_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/psp

# The directory that points to your kernel source directory.
LINUXKERNEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/psp/linux-2.6.34-psp4.0.0.6
KERNEL_INSTALL_DIR=$(LINUXKERNEL_INSTALL_DIR)

# Where the local power manager is installed.
LPM_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/<__local-power-manager__>

# Where the development headers and libs are installed.
LINUXLIBS_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/linux-devkit/arm-none-linux-gnueabi/usr

# Where c6accel package is installed.
C6ACCEL_INSTALL_DIR=$(DVSDK_INSTALL_DIR)/c6accel_2_01_00_02

# The prefix to be added before the GNU compiler tools (optionally including # path), i.e. "arm_v5t_le-" or "/opt/bin/arm_v5t_le-".
CSTOOL_DIR=/home/dvsdkval/CodeSourcery/Sourcery_G++_Lite/
CSTOOL_PREFIX=$(CSTOOL_DIR)/bin/arm-none-linux-gnueabi-
CSTOOL_PATH=$(CSTOOL_DIR)/bin

MVTOOL_DIR=$(CSTOOL_DIR)
MVTOOL_PREFIX=$(CSTOOL_PREFIX)

# Where to copy the resulting executables
EXEC_DIR=$(HOME)/install/$(PLATFORM)

