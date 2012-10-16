#!/bin/sh

echo
echo "--------------------------------------------------------------------------------"
echo "Verifying Linux host distribution"
host=`lsb_release -a 2>/dev/null | grep Codename: | awk {'print $2'}`
if [ "$host" == "lucid" ]; then
    echo "Ubuntu 10.04 LTS found successfully, continuing.."
    echo "--------------------------------------------------------------------------------"
    echo
elif [ "$host" == "oneiric" ]; then
    echo "Ubuntu 11.10 found successfully, continuing.."
    echo "--------------------------------------------------------------------------------"
    echo
elif [ "$host" == "precise" ]; then
    echo "Ubuntu 12.04 found successfully, continuing.."
    echo "--------------------------------------------------------------------------------"
    echo
else
    echo "Unsupported host machine, only Ubuntu 10.04 LTS, Ubuntu 11.10 and Ubuntu 12.04 supported"
    exit 1
fi
