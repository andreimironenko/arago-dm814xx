#!/bin/sh

ps | grep bash | grep mountnfs

if [ $? -eq 0 ] ; then
	#Another instance of mountnfs.sh is already running!
	exit 0
fi

nohup /bin/bash /usr/share/scripts/mountnfs.sh  >/dev/null &
exit 0
