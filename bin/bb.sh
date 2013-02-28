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

#Build mode. If it's set then all built binaries will be copied to release 
#and not user personal folder on amuxi. Only build-manager should use it as 
#it will require super-user access level on amuxi.
declare BUILD_MODE=""

#Postfix mode. Is used in the tasks/image recipes for defining what type of the
# package is required: dev, dbg or release. dev is used for SDK builds, dbg for
# dev images and empty line for releases.
declare POSTFIX="" 

#Debug mode. If't set bitbake will produce a lot of log with debug information.
#Use it to pin in build issue with your component
declare DEBUG=""

#Type of the build: image or SDK. If it's not provided then by default -image.
declare TYPE=""



#bb.sh possible errors
declare BB_ERR_SWITCH_NO_SUPPORT=-192
declare BB_ERR_EXTRA_ARGUMENT=-193
declare BB_ERR_SANITY_FAILED=194



declare -rx SCRIPT=${0##*/}

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

declare USER=`whoami`

# Process command line...
while [ $# -gt 0 ]; do
  case $1 in
    --help | -h) 
    printf "%s\n"             
    printf "%s\n" "This script is a wrapper for bitbake command to simplify"
	printf "%s\n" "product build procedure in the OE"
	printf "%s\n" 
	printf "%s\n" "To build certain product:"
	printf "%s\n" "$SCRIPT -p sr1106 -r r01 -m dm814x-z3"
	printf "%s\n"
	printf "%s\n" "To build one package use -b option:"
	printf "%s\n" "$SCRIPT -p sr1106 -r r01 -m dm814x-z3 -b ipled"
	printf "%s\n"
	printf "%s\n" "To clean from previous build:"
	printf "%s\n" "$SCRIPT -p sr1106 -r r01 -m dm814x-z3 -c clean"
	printf "\n%s\n" "Or to clean only one package:"
	printf "%s\n" "$SCRIPT -p sr1106 -r r01 -m dm814x-z3 -b ipled -c clean"
	printf "%s\n"
	printf "%s\n" "Available options:"
	printf "%s\t%s\n" "-p, --product" "Mandatory. Product ID, i.e. SR1106"
	printf "%s\t%s\n" "-r, --release" "Mandatory. Product release, i.e. r01, r02, dbg..."
	printf "%s\t%s\n" "-t, --type"    "Optional. Type of the build: image or sdk. Default: image"
	printf "%s\t%s\n" "-m, --machine" "Mandatory. Machine ID, i.e dm814x-z3, dm365-htc ..."
	printf "%s\t%s\n" "-b, --bb" "Optional. Build a OE bitbake package, not a whole product"
	printf "%s\t%s\n" "-c, --command" "Optional. Run only one command, i.e. -c clean"
	printf "%s\t%s\n" "-a, --admin" "Optinal. Only for build-manager making official releases " 
	printf "%s\t%s\n" "-h, --help" "This help"
	printf "%s\n"
	exit 0
    ;;
    --product | -p)     shift; PRODUCT=$1; shift; ;;
    --release | -r)     shift; PR=$1;      shift; ;;
    --type    | -t)     shift; TYPE=$1;    shift; ;;
    --machine | -m)     shift; MACHINE=$1; shift; ;;
    --bb |      -b)     shift; BB=$1;      shift; ;;
    --command | -c)     shift; CMD=$1;     shift; ;;
    --admin   | -a)     shift; BUILD_MODE="release";  shift; ;;
    --debug   | -d)     shift; DEBUG="1";    shift; ;;
    _*)                 printf "%s\n" "Switch not supported" >&2; exit $BB_ERR_SWITCH_NO_SUPPORT ;;
    *)                 printf "%s\n" "Extra argument or missing switch" >&2; exit $BB_ERR_EXTRA_ARGUMENT ;;  
esac
done


printf "%s" "Sanity check for parameters and build targets  ..."
SANITY_CHECK_STATUS=1

#Check mandatory parameters first
if [ -z "$PRODUCT" ] ; then
   printf "%s\n" "Mandatory parameter -p, --product is missed" >&2
   SANITY_CHECK_STATUS=0
fi

if [ -z "$PR" ] ; then
   printf "%s\n" "Mandatory parameter -r, --release is missed" >&2
   SANITY_CHECK_STATUS=0
fi

if [ -z "$MACHINE" ] ; then
   printf "%s\n" "Mandatory parameter -m, --machine is missed" >&2
   SANITY_CHECK_STATUS=0
fi

declare RECIPE=""

#Check either the product/release recipe exist
if [ "$TYPE" = "" -o "$TYPE" = "image" ] ; then
	if [ $PR = "dbg" ] ; then 
		if [ ! -f $OEBASE/hanover-system-dev/recipes/images/$PRODUCT/$PRODUCT-image.dbg.bb ] ; then
			SANITY_CHECK_STATUS=0
			printf "%s\n" "Product image $PRODUCT-image.dbg.bb is not found"
		else
			RECIPE="$PRODUCT-image.dbg"
			POSTFIX="-dbg"
		fi
	else
		if [ ! -f $OEBASE/hanover-system/recipes/images/$PRODUCT/$PRODUCT-image.bb ] ; then
			SANITY_CHECK_STATUS=0
			printf "%s\n" "Product image $PRODUCT-image.bb is not found"
		else
			RECIPE="$PRODUCT-image"
			POSTFIX=""
		fi
	fi

else
	if [ ! -f $OEBASE/hanover-system/recipes/meta/$PRODUCT/$PRODUCT-sdk.bb ] ; then
	SANITY_CHECK_STATUS=0
	printf "%s\n" "Product image $PRODUCT-sdk.bb is not found"
	else
		RECIPE="$PRODUCT-sdk"
		POSTFIX="-dev"
	fi
fi


if [ "$SANITY_CHECK_STATUS" == 1 ] ; then
  printf "%s\n" "Ok"
else
 printf "%s\n" "Failed"
 exit $BB_ERR_SANITY_FAILED
fi

#Non-admin user mode build
if [ -z "$BUILD_MODE" ] ; then
	BUILD_MODE=${USER}
fi


declare COMMAND_LINE="MACHINE=$MACHINE PRODUCT=$PRODUCT PRODUCT_RELEASE=$PR BUILD_MODE=$BUILD_MODE POSTFIX=$POSTFIX bitbake"

#Check either it is going a complete product or just one component build
if [ -z "$BB" ] ; then
	COMMAND_LINE+=" $RECIPE";
else
	COMMAND_LINE+=" -b $BB$POSTFIX"
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

eval "$COMMAND_LINE"

exit 0