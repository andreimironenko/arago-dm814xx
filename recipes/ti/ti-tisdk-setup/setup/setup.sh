#!/bin/sh

echo
echo "--------------------------------------------------------------------------------"
echo "DVSDK setup script"
echo
echo "This script will set up your development host for dvsdk development."
echo "Parts of this script require administrator priviliges (sudo access)."
echo "--------------------------------------------------------------------------------"

cwd=`dirname $0`

$cwd/bin/setup-host-check.sh

$cwd/bin/setup-package-install.sh

$cwd/bin/setup-targetfs-nfs.sh

$cwd/bin/setup-tftp.sh

$cwd/bin/setup-minicom.sh

$cwd/bin/setup-uboot-env.sh

echo
echo "DVSDK setup completed!"
echo "Please continue reading the Software Developer's Guide for more information on"
echo "how to develop software on the EVM"
