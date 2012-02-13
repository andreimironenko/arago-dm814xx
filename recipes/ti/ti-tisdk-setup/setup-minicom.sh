#!/bin/sh

cwd=`dirname $0`
. $cwd/common.sh


minicomcfg=${HOME}/.minirc.dfl

echo
echo "--------------------------------------------------------------------------------"
echo "This step will set up minicom (serial communication application) for"
echo "SDK development"
echo
echo
echo "For boards that contain a USB-to-Serial converter on the board (BeagleBone), "
echo "the port used for minicom will be automatically detected. By default Ubuntu "
echo "will not recognize this device. Setup will add will add a udev rule to "
echo "/etc/udev/ so that from now on it will be recognized as soon as the board is "
echo "plugged in."
echo
echo "For other boards, the serial will defualt to /dev/ttyS0. Please update based "
echo "on your setup."

echo "--------------------------------------------------------------------------------"
echo

portdefault=/dev/ttyS0

echo ""
echo "NOTE: For boards with a built-in USB to Serial adapter please press"
echo "      ENTER at the prompt below.  The correct port will be determined"
echo "      automatically at a later step.  For all other boards select"
echo "      the serial port that the board is connected to"
echo "Which serial port do you want to use with minicom?"
read -p "[ $portdefault ] " port

if [ ! -n "$port" ]; then
    port=$portdefault
fi

if [ -f $minicomcfg ]; then
    cp $minicomcfg $minicomcfg.old
    echo
    echo "Copied existing $minicomcfg to $minicomcfg.old"
fi

echo "pu port             $port
pu baudrate         115200
pu bits             8
pu parity           N
pu stopbits         1
pu minit
pu mreset
pu mdialpre
pu mdialsuf
pu mdialpre2
pu mdialsuf2
pu mdialpre3
pu mdialsuf3
pu mconnect
pu mnocon1          NO CARRIER
pu mnocon2          BUSY
pu mnocon3          NO DIALTONE
pu mnocon4          VOICE
pu rtscts           No" | tee $minicomcfg > /dev/null
check_status

echo
echo "Configuration saved to $minicomcfg. You can change it further from inside"
echo "minicom, see the Software Development Guide for more information."
echo "--------------------------------------------------------------------------------"
