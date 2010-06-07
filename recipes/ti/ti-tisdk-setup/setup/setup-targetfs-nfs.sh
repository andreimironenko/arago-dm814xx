#!/bin/sh

cwd=`dirname $0`
. $cwd/common.sh

dstdefault="${HOME}/targetfs"
echo "--------------------------------------------------------------------------------"
echo "In which directory do you want to keep your target filesystem?"
read -p "[ $dstdefault ] " dst

if [ ! -n "$dst" ]; then
    dst=$dstdefault
fi
echo "--------------------------------------------------------------------------------"

echo
echo "--------------------------------------------------------------------------------"
echo "This step will extract the target filesystem to $dst"
echo
echo "Note! This command requires you to have administrator priviliges (sudo access) "
echo "on your host."
read -p "Press return to continue"

if [ -d $dst ]; then
    echo "$dst already exists, skipping extraction..."
else
    sudo mkdir -p $dst
    check_status

    fstar=`ls -1 $cwd/../../filesystem/dvsdk*rootfs.tar.gz`
    sudo tar xzf $fstar -C $dst
    check_status
fi
echo $dst > $cwd/../../.targetfs
echo "--------------------------------------------------------------------------------"

echo
echo "--------------------------------------------------------------------------------"
echo "This step will export your target filesystem for NFS access."
echo
echo "Note! This command requires you to have administrator priviliges (sudo access) "
echo "on your host."
read -p "Press return to continue"

grep $dst /etc/exports > /dev/null
if [ "$?" -eq "0" ]; then
    echo "$dst already NFS exported, skipping.."
else
    sudo chmod 666 /etc/exports
    check_status
    sudo echo "$dst *(rw,nohide,insecure,no_subtree_check,async,no_root_squash)" >> /etc/exports
    check_status
    sudo chmod 644 /etc/exports
    check_status
fi

echo
sudo /etc/init.d/nfs-kernel-server restart
check_status
echo "--------------------------------------------------------------------------------"
