#!/bin/sh
#
# configure kernel modules for TI DSP based gstreamer plugin
#

# Disable XDM 0.9 elements
export GST_TI_TIViddec_DISABLE=1
export GST_TI_TIAuddec_DISABLE=1
export GST_TI_TIVidenc_DISABLE=1
export GST_TI_TIImgdec_DISABLE=1
export GST_TI_TIImgenc_DISABLE=1

# Disable XDM 1.x audio decode element
export GST_TI_TIAuddec_DISABLE=1

load_module() {
    echo 
    echo -n "Running /usr/share/ti/gst/dm365/loadmodule.sh"
    echo " (skipped)"
    # /usr/share/ti/gst/dm365/loadmodule.sh
}

unload_module() {
   rmmod cmemk 2>/dev/null
   rmmod irqk 2>/dev/null
   rmmod edmak 2>/dev/null
   rmmod dm365mmap 2>/dev/null
}

case "$1" in
      start) 
             echo -n "Loading kernel modules for gstreamer-ti... "
             load_module
             echo "  done"
             ;;
       stop) 
             echo -n "Unloading kernel module ..."
             unload_module
             echo "   done"
             ;;
        restart) 
             echo -n "Unloading kernel module ..."
             unload_module
             echo "   done"
             echo -n "Loading kernel modules for gstreamer-ti... "
             load_module
             echo "  done"
             ;;
        *)
             echo "$0 <start/stop/restart>"
             ;;
esac

