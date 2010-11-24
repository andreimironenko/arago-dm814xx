#!/bin/sh

echo
echo "--------------------------------------------------------------------------------"
echo "SDK setup script"
echo
echo "This script will set up your development host for sdk development."
echo "Parts of this script require administrator priviliges (sudo access)."
echo "--------------------------------------------------------------------------------"

cwd=`dirname $0`
. $cwd/bin/common.sh

$cwd/bin/setup-host-check.sh
check_status

$cwd/bin/setup-package-install.sh
check_status

$cwd/bin/setup-targetfs-nfs.sh
check_status

$cwd/bin/setup-tftp.sh
check_status

$cwd/bin/setup-minicom.sh
check_status

$cwd/bin/setup-uboot-env.sh
check_status

echo
echo "SDK setup completed!"
echo "Please read the Software Developer's Guide present in the docs folder for more"
echo "information on how to develop software on the EVM"
