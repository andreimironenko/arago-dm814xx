#! /bin/sh

echo "Running Loop Sample App"
./loopgpp loop.out 1024 2000 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

echo "Running Message Sample App"
./messagegpp message.out 10000 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

echo "Running Message Multi Sample App"
./messagemultigpp messagemulti.out 128 1 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

echo "Running MultiProcessor List Sample App"
./mplistgpp mplist.out 128 128 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

echo "Running MultiProcessorCriticalSection Transfer Sample App" 
./mpcsxfergpp mpcsxfer.out 128 1000 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

echo "Running Ringio Sample App"
./ringiogpp ringio.out 2048 128 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

echo "Running Scale Sample App"
./scalegpp scale.out 128 500 0

/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
/usr/share/ti/ti-lpm-utils/lpmON.xv5T

#echo "Running Read/Write Sample App"
#./readwritegpp readwrite.out 
#
#/usr/share/ti/ti-lpm-utils/lpmOFF.xv5T
#/usr/share/ti/ti-lpm-utils/lpmON.xv5T
