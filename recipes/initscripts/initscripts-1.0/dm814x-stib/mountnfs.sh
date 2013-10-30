#!/bin/sh
if [ -f /usr/share/scripts/mountnfs.sh ] ; then
	nohup /bin/bash /usr/share/scripts/mountnfs.sh  >/dev/null &
fi
exit 0

