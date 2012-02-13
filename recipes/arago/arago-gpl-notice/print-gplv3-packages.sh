#!/bin/sh

gplv3_packages=""

# find all files with GPLv3 in them that do not also have RLE
# exception listed.
files=`grep License: /usr/lib/opkg/info/*.control | grep -i GPLv3 | grep -v RLE | cut -d: -f1 | sort -u`

for i in $files
do
    package=`basename $i | sed s/\.control//`
    gplv3_packages="$gplv3_packages""\t$package\n"
done

if [ "$gplv3_packages" != "" ]
then
    echo "***************************************************************"
    echo "***************************************************************"
    echo "NOTICE: This file system contains the followin GPLv3 packages:"
    echo -e $gplv3_packages
    echo "If you do not wish to distribute GPLv3 components please remove"
    echo "the above packages prior to distribution.  This can be done using"
    echo "the opkg remove command.  i.e.:"
    echo "    opkg remove <package>"
    echo "Where <package> is the name printed in the list above"
    echo ""
    echo "NOTE: If the package is a dependency of another package you"
    echo "      will be notified of the dependent packages.  You should"
    echo "      use the --force-removal-of-dependent-packages option to"
    echo "      also remove the dependent packages as well"
    echo "***************************************************************"
    echo "***************************************************************"
fi
