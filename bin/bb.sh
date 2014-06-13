#! /bin/sh
################################################################################
#This script is a wrapper for bitbake command to simplify product build        # 
#procedure                                                                     #
# Author: Andrei Mironenko <amironenko@hanoverdisplays.com>                    #
#                                                                              #
# Copyright(C) 2012 Hanover Displays Ltd.                                      #
# This file is licensed under the terms of the GNU General Public License      #
# version 2. This program  is licensed "as is" without any warranty of any kind#
# whether express or implied.                                                  #
################################################################################

################################################################################
#  Global variables                                                            #
################################################################################

#Product will be build for this machine
declare MACHINE=""

#Product to build
declare PRODUCT=""

#Product release
declare PR=""

#If it's provided then OE builds a specific package
declare BB=""

#Command to execute
declare CMD=""

#Debug mode. If't set bitbake will produce a lot of log with debug information.
#Use it to pin in build issue with your component
declare DEBUG=""

#Type of the build: image or SDK. If it's not provided then by default -image.
declare BUILD_TYPE="image"

# Library build mode
# This flag is used to separate the image and SDK builds. For the SDK builds
# "-dev" postfix must be added to each of the library. DEV type of the library
# package includes all header files for this library. 
declare LIB_BUILD_MODE="" 

# Default build environment is development, DEV_FLAG=1
declare DEV_FLAG=1 

#bb.sh possible errors
declare BB_ERR_SWITCH_NO_SUPPORT=-192
declare BB_ERR_EXTRA_ARGUMENT=-193
declare BB_ERR_SANITY_FAILED=-194

declare -rx SCRIPT=${0##*/}

declare USER=`whoami`

#Build mode. If it's set then all built binaries will be copied to release 
#and not user personal folder on amuxi. Only build-manager should use it as 
#it will require super-user access level on amuxi.
declare BUILD_PURPOSE="$USER"


execute () 
{
	$* >/dev/null
    if [ $? -ne 0 ]; then
        echo
        echo "ERROR: executing $*"
        echo
        exit 1
    fi
}


# Process command line...
while [ $# -gt 0 ]; do
  case $1 in
    --help | -h) 
    printf "%s\n"             
    printf "%s\n" "This script is a wrapper for bitbake command to simplify"
	printf "%s\n" "product build procedure in the OE"
	printf "%s\n"
	printf "%s\n" "Usage: bb [options]"
	printf "%s\n"
	printf "%s\n" "Available options:"
	printf "%s\t%s\n" "-p, --product" "Mandatory. Product ID, i.e. SR1106"
	printf "%s\t%s\n" "-m, --machine" "Mandatory. Machine ID, i.e dm814x-z3, dm365-htc ..."
	printf "%s\t%s\n" "-b, --bitbake" "Optional.  Build a particular package, not a whole product"
	printf "%s\t%s\n" "-c, --command" "Optional.  Run only one bitbake command, i.e. -c clean"
	printf "%s\t%s\n" "-r, --release" "Optinal.   Make a release build"
	printf "%s\t%s\n" "-f, --freeze"  "Optinal.   For build manager only. Build a final product release"
	printf "%s\t%s\n" "-D, --debug"   "Optinal.   Enable OE extra build information" 
	printf "%s\t%s\n" "-h, --help"    "This help"
	printf "%s\n"
	printf "%s\n" "Some examples:"
	printf "%s\n"
	printf "%s\n" "To build release version of the product:"
	printf "%s\n" "$SCRIPT -p sr1106 -m dm814x-z3 -r"
	printf "%s\n"
	printf "%s\n" "To build  development version of the product:"
	printf "%s\n" "$SCRIPT -p sr1106 -m dm814x-z3"
	printf "%s\n"
	printf "%s\n" "To build one package use -b option:"
	printf "%s\n" "$SCRIPT -p sr1106 -m dm814x-z3 -b hanover-apps-dev/recipes/iptft/iptft_git.bb"
	printf "%s\n"
	printf "\n%s\n" "To clean the same pacakge package:"
	printf "%s\n" "$SCRIPT -p sr1106 -m dm814x-z3 -b hanover-apps-dev/recipes/iptft/iptft_git.bb -c clean"
	printf "%s\n"
	
	exit 0
    ;;
    --product | -p)     shift; PRODUCT=$1; shift; ;;
    --machine | -m)     shift; MACHINE=$1; shift; ;;
    --bitbake | -b)     shift; BB=$1;      shift; ;;
    --command | -c)     shift; CMD=$1;     shift; ;;
    --release | -r)     shift; DEV_FLAG=0;  ;;
    --freeze  | -f)     shift; BUILD_PURPOSE="release"; DEV_FLAG=0;  ;;
    --debug   | -D)     shift; DEBUG=1;    ;;
    
    -*)                 printf "%s\n" "Switch not supported" >&2; exit -1 ;;
    *)                  USER_IMAGE=$1; shift; ;;  
esac
done


#Sanity check for parameters and build targets
SANITY_CHECK_STATUS=1

#Check mandatory parameters first
if [ -z "$PRODUCT" ] ; then
   printf "%s\n" "Mandatory parameter -p, --product is missed" >&2
   SANITY_CHECK_STATUS=0
fi

if [ -z "$MACHINE" ] ; then
   printf "%s\n" "Mandatory parameter -m, --machine is missed" >&2
   SANITY_CHECK_STATUS=0
fi

#Checking either we are dealing with development or release build
if [ "$DEV_FLAG" = "1" ] ; then
	if [ ! -d "$OEBASE/hanover-system-dev" -a -d "$OEBASE/hanover-apps-dev" ]  ; then
		SANITY_CHECK_STATUS=0;
		printf "%s\n" 
		printf "%s\n" "Although hanover-system-dev is present,"
		printf "%s\n" "hanover-apps-dev is not found."
		printf "%s\n" "Run \"bb-get -d\" to get proper development build environment."
		printf "%s\n" 
	elif [ ! -d "$OEBASE/hanover-apps-dev" -a -d "$OEBASE/hanover-system-dev" ]  ; then
		SANITY_CHECK_STATUS=0;
		printf "%s\n" 
		printf "%s\n" "Although hanover-system-dev is present," 
		printf "%s\n" "hanover-apps-dev is not found."
		printf "%s\n" "Run \"bb-get -d\" to get proper development build environment."
		printf "%s\n" 
	fi 
fi	


if [ ! -f $OEBASE/hanover-products/${PRODUCT}/release.inc ] ; then
	printf "%s\n" "release.inc for $PRODUCT is not found"
	SANITY_CHECK_STATUS=0
fi

if [ "$SANITY_CHECK_STATUS" = "1" ] ; then
	printf "%s\n" "Sanity check for parameters and build targets...Ok"
else
	printf "%s\n" "Sanity check for parameters and build targets...Failed"
	exit -1 
fi


#Take a product release from the product folder
PRODUCT_RELEASE_GIT=`git --git-dir=./hanover-products/${PRODUCT}/.git describe`

printf "%s\n" "PRODUCT_RELEASE_GIT=$PRODUCT_RELEASE_GIT"

PRODUCT_RELEASE="${PRODUCT_RELEASE_GIT:0:3}"
PRODUCT_VERSION="${PRODUCT_RELEASE_GIT:4}"

printf "%s\n" "PRODUCT_RELEASE=$PRODUCT_RELEASE"
printf "%s\n" "PRODUCT_VERSION=$PRODUCT_VERSION"

# If there is no commits in the product since the last release PRODUCT_VERSION
# will be empty string. For this case, it's initialised with 0.
if [ -z $PRODUCT_VERSION ] ; then 
	PRODUCT_VERSION="rc00"
fi

if [ "$DEV_FLAG" = "1" ] ; then
	RELDIR="dev"
else
	RELDIR="rel"
fi

if [ -z ${USER_IMAGE} ] ; then 
	IMAGE="hanover-image"; 
elif [ ${USER_IMAGE} = image ] ; then 
	IMAGE="hanover-image";
elif [ ${USER_IMAGE} = sdk ] ; then
	IMAGE="hanover-meta-sdk";
	LIB_BUILD_MODE="-dev";
else
	IMAGE=${USER_IMAGE};
fi


declare COMMAND_LINE="MACHINE=$MACHINE PRODUCT=$PRODUCT \
	PRODUCT_RELEASE=$PRODUCT_RELEASE PRODUCT_VERSION=$PRODUCT_VERSION \
    BUILD_PURPOSE=$BUILD_PURPOSE \
	LIB_BUILD_MODE=$LIB_BUILD_MODE RELDIR=$RELDIR \
    EXPORT_BASE_PATH=$EXPORT_BASE_PATH HTTP_BASE_PATH=$HTTP_BASE_PATH \
	LOCAL_BASE_PATH=$LOCAL_BASE_PATH FTP_BASE_PATH=$FTP_BASE_PATH  TFTP_BASE_PATH=$TFTP_BASE_PATH \
    GITSERVER=$GITSERVER FTP_SERVER=$FTP_SERVER TFTP_SERVER=$TFTP_SERVER \
    HTTP_SERVER=$HTTP_SERVER NFS_SERVER=$NFS_SERVER UPDATESRC=$UPDATESRC \
    bitbake"
	

#Check either it is going a complete product or just one component build
if [ -z "$BB" ] ; then
	COMMAND_LINE+=" $IMAGE";
else
	COMMAND_LINE+=" -b $BB"
fi

#Check either a command has been provided
if [ ! -z "$CMD" ] ; then
	COMMAND_LINE+=" -c $CMD" 
fi

#And finally check if the debug mode has enabled
if [ "$DEBUG" = "1" ] ; then
	COMMAND_LINE+=" -DDD"
fi


printf "%s\n" "Starting bitbake  ..."
printf "%s\n" "Command line: $COMMAND_LINE"

printf "\n"
echo "PRODUCT=${PRODUCT}"
echo "MACHINE=${MACHINE}"
echo "BUILD_TYPE=${BUILD_TYPE}"
echo "BB=${BB}"
echo "CMD=${CMD}"
echo "DEV_FLAG=${DEV_FLAG}"
echo "BUILD_PURPOSE=${BUILD_PURPOSE}"
echo "PRODUCT_RELEASE=${PRODUCT_RELEASE}"
echo "PRODUCT_VERSION=${PRODUCT_VERSION}"
echo "IMAGE=${IMAGE}"
echo "RELDIR=${RELDIR}"


eval "$COMMAND_LINE -k"

exit 0
