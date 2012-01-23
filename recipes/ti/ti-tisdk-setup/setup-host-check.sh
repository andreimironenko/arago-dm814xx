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
else
    echo "Unsupported host machine, only Ubuntu 10.04 LTS and Ubuntu 11.10 supported"
    exit 1
fi
