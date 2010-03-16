#!/bin/sh
# Script to install packages on rootfs.
# usage
#  ./install_rootfs.sh <install_dir> [packages]
#
# Script assumes that filesystem tarball is present in $1/filesystem directory.

#
# execute command
#
execute ()
{
  echo "$*"
  $*
  if [ $? -ne 0 ]; then
    echo "ERROR: failed to execute $*"
    exit 1;
  fi
}

#
# install packages
#
install ()
{
  test -z $* && echo "nothing to install" && return

  cwd=$PWD
  execute "mkdir -p $rootfs_dir"
  execute "tar zxf $install_dir/filesystem/* -C $rootfs_dir"
  execute "mkdir -p ${rootfs_dir}/usr/lib/opkg"

  # install pkgs
  execute "opkg-cl --cache $rootfs_dir/deploy/cache -o $rootfs_dir -f ${opkg_conf}  update"
  execute "opkg-cl --cache $rootfs_dir/deploy/cache -o $rootfs_dir -f ${opkg_conf} install  $*"

  echo "Running preinst ..."
  for i in ${rootfs_dir}/usr/lib/opkg/info/*.preinst; do
    if [ -f $i ] && ! sh $i; then
      execute "opkg-cl -o ${rootfs_dir} -f ${opkg_conf} flag unpacked `basename $i .preinst`"
    fi
  done 

  echo "Running postinst ..."
  for i in ${rootfs_dir}/usr/lib/opkg/info/*.postinst; do
    if [ -f $i ] && ! sh $i configure; then
      execute "opkg-cl -o ${rootfs_dir} -f ${opkg_conf} flag unpacked `basename $i .postinst`"
    fi
  done 

  execute "cd ${rootfs_dir}"
  tarname="`ls -1 ${install_dir}/filesystem/*`"
  execute "tar zcf $tarname *"
  execute "cd $cwd"
  execute "rm -rf $rootfs_dir"
}

if [ ! -d $1/filesystem ]; then
  echo "ERROR: failed to find filesystem directory"
  exit 1
fi

install_dir=$1
opkg_conf=$1/.opkg.conf
rootfs_dir="$1/.tmp"
export D="${rootfs_dir}"
export OFFLINE_ROOT="${rootfs_dir}"
export IPKG_OFFLINE_ROOT="${rootfs_dir}"
export OPKG_OFFLINE_ROOT="${rootfs_dir}"
export OPKG_CONFDIR_TARGET="${rootfs_dir}/etc/opkg"
shift
install $*

