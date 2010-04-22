#!/bin/sh
#
# Run dvsdk loadmodule.sh
#

load_module() {
  /usr/share/ti/dvsdk-demos/loadmodules.sh
}

case "$1" in
      start) 
             echo -n "Launching dvsdk demo... "
             load_module
             cd /usr/share/ti/dvsdk-demos/
             ./interface &
             echo "  done"
             ;;
       stop)
             echo -n "Nothing to do here"
             ;;
        *)
             echo "$0 <start>"
             ;;
esac

