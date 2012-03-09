#!/usr/bin/perl -w

use POSIX;

################################################################################
# TISDK Arago build script
################################################################################
my $script_version = "0.9";

my @no_machines = ("arago", "include");
my @packages;

my $bsp_source = "task-arago-toolchain-tisdk-bsp-host";
my $bsp_binary = "task-arago-tisdk-bsp";
my $bsp_sdk_header = "task-arago-toolchain-tisdk-bsp-target";
my $addons_source = "task-arago-toolchain-tisdk-addons-host";
my $addons_binary = "task-arago-tisdk-addons";
my $crypto_binary = "task-arago-tisdk-crypto";
my $crypto_sdk_header = "task-arago-toolchain-tisdk-crypto-target";
my $crypto_source = "task-arago-toolchain-tisdk-crypto-host";
my $multimedia_source = "task-arago-toolchain-tisdk-multimedia-host";
my $multimedia_binary = "task-arago-tisdk-multimedia";
my $multimedia_sdk_header ="task-arago-toolchain-tisdk-multimedia-target";
my $dsp_source = "task-arago-toolchain-tisdk-dsp-host";
my $dsp_binary = "task-arago-tisdk-dsp";
my $dsp_sdk_target = "task-arago-toolchain-tisdk-dsp-target";
my $graphics_binary = "task-arago-tisdk-graphics";
my $graphics_src = "task-arago-toolchain-tisdk-graphics-host";
my $graphics_sdk_target ="task-arago-toolchain-tisdk-graphics-target";

my $image;
my $machine;
my $index = 0;

my $sdkpath_default = "sdk-cdrom";
my $toolchain_default = "codesourcery";
my $machine_default = "dm6446-evm";

my %machines = (
    "dm365-evm"     => {
        SOC_FAMILY          => "dm365",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "no",
        graphics_default    => "yes",
    },
    "dm6446-evm"    => {
        SOC_FAMILY          => "dm6446",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "yes",
        graphics_default    => "yes",
    },
    "dm355-evm"     => {
        SOC_FAMILY          => "dm355",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "no",
        graphics_default    => "yes",
    },
    "da830-omapl137-evm"     => {
        SOC_FAMILY          => "omapl137",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "yes",
        graphics_default    => "yes",
    },
    "dm6467-evm"     => {
        SOC_FAMILY          => "dm6467",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "yes",
        graphics_default    => "no",
    },
    "da850-omapl138-evm"     => {
        SOC_FAMILY          => "omapl138",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "yes",
        graphics_default    => "yes",
    },
    "omap3evm"     => {
        SOC_FAMILY          => "omap3",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "yes",
        graphics_default    => "yes",
    },
    "dm37x-evm"     => {
        SOC_FAMILY          => "omap3",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "no",
        multimedia_default  => "yes",
        dsp_default         => "yes",
        graphics_default    => "yes",
    },
    "am37x-evm"     => {
        SOC_FAMILY          => "omap3",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default    => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
        graphics_default    => "yes",
    },
    "c6a816x-evm"     => {
        SOC_FAMILY          => "ti816x",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "no",
        dsp_default         => "yes",
        graphics_default    => "yes",
    },
    "am389x-evm"     => {
        SOC_FAMILY          => "ti816x",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
        graphics_default    => "yes",
    },
    "dm368-evm"     => {
        SOC_FAMILY          => "dm365",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
        graphics_default    => "yes",
    },
    "c6a816x-evm"     => {
        SOC_FAMILY          => "ti816x",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "no",
        dsp_default         => "yes",
        dvsdk_factory_default => "no",
        graphics_default    => "yes",
    },
    "dm816x-evm"     => {
        SOC_FAMILY          => "ti816x",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default      => "no",
        multimedia_default  => "yes",
        dsp_default         => "no",
        dvsdk_factory_default => "no",
        graphics_default    => "yes",
    },
    "dm816x-custom"     => {
        SOC_FAMILY          => "ti816x",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
        dvsdk_factory_default => "no",
        graphics_default    => "no",
    },
    "c6a814x-evm"     => {
        SOC_FAMILY          => "ti814x",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "no",
        dsp_default         => "yes",
        dvsdk_factory_default => "no",
        graphics_default    => "yes",
    },
    "dm814x-evm"     => {
        SOC_FAMILY          => "ti814x",
        bsp_default         => "yes",
        addons_default      => "yes",
        crypto_default      => "no",
        multimedia_default  => "yes",
        dsp_default         => "no",
        dvsdk_factory_default => "no",
        graphics_default    => "yes",
    },
    "dm814x-custom"     => {
        SOC_FAMILY          => "ti814x",
        bsp_default         => "yes",
        addons_default      => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
        dvsdk_factory_default => "no",
        graphics_default    => "no",
    },
);

################################################################################
# main
################################################################################

print "Arago build script version $script_version\n\n";

if (! exists $ENV{'OEBASE'}) {
    print "ERROR: Arago environment variables not set, did you ";
    print "'source arago-setenv'?\n";
    exit 1;
}

my $arago_dir = "$ENV{'OEBASE'}";
my $arago_images_output_dir = "$arago_dir/arago-tmp/deploy/glibc/images";
my $arago_image_dir = "$arago_dir/arago/recipes/images";
my $arago_ipk_dir = "$arago_dir/arago-tmp/deploy/glibc/ipk";
my $arago_machine_dir = "$arago_dir/arago/conf/machine";
my $arago_tmp = "$arago_dir/arago-tmp";
my ($sysname, $nodename, $release, $version, $mtype) = POSIX::uname();
my $arago_staging = "$arago_dir/arago-tmp/sysroots/$mtype-linux";

if (! -d "$arago_machine_dir") {
    print "ERROR: $arago_dir/arago/conf/machine not found! Either your ";
    print "\$OE_BASE variable is not pointing correctly or your Arago ";
    print "installation is broken\n";
    exit 1;
}

parse_args();

get_input();

validate_input();

build_image();

copy_output();

print "\nBuild of Arago completed\n";

################################################################################
# build_image
################################################################################
sub build_image
{
    my $result;
    my $cmd;

    foreach (@packages) {
        print "\nBuilding $_ for $machine\n";

        $cmd = "MACHINE=$machine bitbake $_";
        $result = system($cmd);
        if ($result) {
            print "\nERROR: Failed to build $_ for $machine\n";
            exit 1;
        }
    }

    if ($graphics =~ m/yes/i) {
        print "\nBuilding  qt4-tools-sdk for $machine\n";
        $cmd = "MACHINE=$machine META_SDK_PATH=/linux-devkit bitbake qt4-tools-sdk";
        $result = system($cmd);
        if ($result) {
            print "\n ERROR: failed to build sdk";
            exit 1;
        }
    }

    print "\nBuilding meta-toolchain-arago-base-tisdk for $machine\n";
    $cmd = "MACHINE=$machine META_SDK_PATH=/linux-devkit bitbake meta-toolchain-arago-base-tisdk";
    $result = system($cmd);
    if ($result) {
        print "\n ERROR: failed to build sdk";
        exit 1;
    }
}

################################################################################
# copy_output
################################################################################
sub copy_output
{
    my $result;
    my $cmd;
    my $march;

    if ($machine =~ m/beagleboard/ || $machine =~ m/omap3evm/ ||
        $machine =~ m/am3517-evm/ || $machine =~ m/dm37x/ ||
        $machine =~ m/am37x/ || $machine =~ m/816x/ || 
        $machine =~ m/am389x/ || $machine =~ m/814x/ ) {
        $march = "armv7a";
    }
    else {
        $march = "armv5te";
    }

    # create directories
    $cmd = "mkdir -p $sdkpath/config/$machine/ $sdkpath/deploy/images/$machine $sdkpath/devel $sdkpath/deploy/ipk $sdkpath/deploy/ipk/usr/lib/opkg";
    $result = system($cmd);
    
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
        exit 1;
    }

    # Copy the depends ipk's
    $cmd = "$arago_staging/usr/bin/opkg-cl -f $arago_staging/etc/opkg.conf -o $sdkpath/deploy/ipk update";
    $result = system($cmd);

    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    $cmd = "$arago_staging/usr/bin/opkg-cl -f $arago_staging/etc/opkg.conf -o $sdkpath/deploy/ipk install --noaction @packages";
    open(OUT, "$cmd |");
    while (<OUT>) {
        if ($_ =~m/Downloading file:/) {
            my $unused;
            my $name;

            ($unused, $name)  = split(/:/, $_);

            $name =~ s/^\s+|\s+$//g;
            chop($name);

            if ($name =~m/_all.ipk/) {
                $dir = "all";
            }
            elsif ($name =~m/_any.ipk/) {
                $dir = "any";
            }
            elsif ($name =~m/_noarch.ipk/) {
                $dir = "noarch";
            }
            elsif ($name =~m/_arm.ipk/) {
                $dir = "arm";
            }
            elsif ($name =~m/_armv4.ipk/) {
                $dir = "armv4";
            }
            elsif ($name =~m/_armv4t.ipk/) {
                $dir = "armv4t";
            }
            elsif ($name =~m/_armv5te.ipk/) {
                $dir = "armv5te";
            }
            elsif ($name =~m/_armv7a.ipk/) {
                $dir = "armv7a";
            }
            elsif ($name =~m/\_$machine.ipk/) {
                $dir = "$machine";
            }

            print "Copying $name. \n";
            my $copycmd = "mkdir -p $sdkpath/deploy/ipk/$dir ; cp $name $sdkpath/deploy/ipk/$dir";
            $result = system($copycmd);
            if ($result) {
                print "Failed to execute $copycmd\n";
                exit 1;
            }
        }
    }

    close(OUT);

    if ($graphics =~ m/yes/i) {
        $cmd = "$arago_staging/usr/bin/opkg-cl -f $arago_staging/etc/opkg-sdk.conf -o $sdkpath/deploy/ipk update";
        $result = system($cmd);

        if ($result) {
            print "\nERROR: Failed to execute command $cmd\n";
            exit 1;
        }

        $cmd = "$arago_staging/usr/bin/opkg-cl -f $arago_staging/etc/opkg-sdk.conf -o $sdkpath/deploy/ipk install --noaction qt4-tools-sdk";
        open(OUT, "$cmd |");
        while (<OUT>) {
            if ($_ =~m/Downloading file:/) {
                my $unused;
                my $name;

                ($unused, $name)  = split(/:/, $_);

                $name =~ s/^\s+|\s+$//g;
                chop($name);

                if ($name =~m/_$mtype-$march-sdk.ipk/) {
                    $dir = "$mtype-$march-sdk";
                }
                print "Copying $name. \n";
                my $copycmd = "mkdir -p $sdkpath/deploy/ipk/$dir ; cp $name $sdkpath/deploy/ipk/$dir";
                $result = system($copycmd);
                if ($result) {
                    print "Failed to execute $copycmd\n";
                   exit 1;
                }
            }
        }
    }

    $cmd = "rm -rf $sdkpath/deploy/ipk/usr";
    $result = system($cmd);

    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    # TODO: Even if build for omap or davinci platform, opkg.conf may have 
    # entries for both armv5te and armv7a arch. creating temporary directory.
    $cmd = "mkdir -p $sdkpath/deploy/ipk/armv5te $sdkpath/deploy/ipk/armv7a  $sdkpath/deploy/ipk/$mtype-armv5te-sdk";
    $result = system($cmd);

    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    # Create the package index files
    opendir(DIR, "$sdkpath/deploy/ipk");
    my @dirs = readdir(DIR);

    for my $dir (@dirs) {
        if ($dir =~m/\.\./) {
            next;
        }
    
        $cmd = "touch $sdkpath/deploy/ipk/$dir/Packages; $arago_staging/usr/bin/opkg-make-index -r $sdkpath/deploy/ipk/$dir/Packages -p $sdkpath/deploy/ipk/$dir/Packages -l $sdkpath/deploy/ipk/$dir/Packages.filelist -m $sdkpath/deploy/ipk/$dir";

        $result = system($cmd);

        if ($result) {
            print "\nERROR: Failed to execute command $cmd\n";
            exit 1;
        }
    }

    # copy image tar
    print "\nCopying $arago_images_output_dir/$machine/$image\-${machine}.tar.gz ...";
    $cmd = "cp $arago_images_output_dir/$machine/$image\-${machine}.tar.gz $sdkpath/deploy/images/$machine";
    $result = system($cmd);
    
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
        exit 1;
    }

    # copy u-boot and kernel binaries
    print "\nCopying u-boot and uImage binaries ...";
    $cmd = "cp $arago_images_output_dir/$machine/u-boot-$machine.bin $sdkpath/deploy/images/$machine";
    $result = system($cmd);
    
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
    }

    $cmd = "cp $arago_images_output_dir/$machine/uImage-$machine.bin $sdkpath/deploy/images/$machine";
    $result = system($cmd);
    
    if ($result) {
        print "\n ERROR: failed to execute $cmd";
    }

    # if MLO (x-load exists) copy it.
    if (-e "$arago_images_output_dir/$machine/MLO") {
        print "\nCopying MLO binary...";
        $cmd = "cp $arago_images_output_dir/$machine/MLO $sdkpath/deploy/images/$machine";
        $result = system($cmd);

        if ($result) {
            print "\n ERROR: failed to execute $cmd";
        }
    }
   
    # copy pre-built .ko tarball.
    print "\nCopying pre-built modules...";
    $cmd = "cp $arago_images_output_dir/$machine/modules-*-$machine.tgz $sdkpath/deploy/images/$machine";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd";
    }
    
    # copy install script
    print "\nCopying $arago_dir/arago/bin/tisdk-install.sh ...";
    $cmd = "cp $arago_dir/arago/bin/tisdk-install*.sh $sdkpath/install.sh";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy opkg and fakeroot command on sdk cdrom, these commands will be
    # used by tisdk-install.sh during installation.
    print "\nCopying $arago_dir/arago/bin/install-tools.tgz  ...";
    $cmd = "cp $arago_dir/arago/bin/install-tools.tgz  $sdkpath";
    
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy opkg.conf needed during opkg installation.
    print "\nCopying $arago_staging/etc/opkg.conf  ...";
    $cmd = "cp $arago_staging/etc/opkg*.conf $sdkpath/config/$machine/";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    print "\nCopying sdk tarball  ...";
    $cmd = "cp $arago_dir/arago-tmp/deploy/glibc/sdk/arago*$march-*sdk.tar.bz2 $sdkpath/devel/ ";    
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }
}

################################################################################
# validate_input
################################################################################
sub validate_input
{
    if (! -e "$arago_machine_dir/$machine.conf") {
        print "ERROR: Machine $arago_machine_dir/$machine.conf not found\n";
        exit 1;
    }
}

################################################################################
# compatible_machine
################################################################################
# $machines{$machine}{'SOC_FAMILY'}
sub compatible_machine
{
    my @lines;
    my $found = 1;
    my $machine = $_[1];
    my $soc_family = $machines{$machine}{'SOC_FAMILY'};

    open IMAGEFILE, "<$_[0]" or die "Failed to open $_[0] for reading\n";
    @lines = <IMAGEFILE>;
    close IMAGEFILE;

    # Check the image to see if it sets COMPATIBLE_MACHINE.  If so then
    # check if the machine we are building for or its SOC_FAMILY is
    # specified in the COMPATIBLE_MACHINE line.  If not then we do not
    # want to build this image.
    for (my $cnt = 0; $cnt < scalar @lines; $cnt++) {
        if ($lines[$cnt] =~ m/.*COMPATIBLE_MACHINE(.*)/) {
            if ((not $lines[$cnt] =~ m/COMPATIBLE_MACHINE.*$machine/) && (not $lines[$cnt] =~ m/COMPATIBLE_MACHINE.*$soc_family/)) {
                $found = 0;
            }
        }
    }

    return $found;
}

################################################################################
# get_input
################################################################################
sub get_input
{
    my $input;

    if (!$machine) {
        print "\nAvailable Arago machine types:\n";
        foreach $x (keys %machines) {
            print "    $x\n";
        }
        print "\nWhich Arago machine type to you want to build for?\n";
        print "[ $machine_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $machine = $input;
        }
        else {
            $machine = $machine_default;
        }
    }

    if ($machine =~ m/default/i) {
        $machine = $machine_default;
    }

    if (! exists $machines{ $machine }) {
        print "ERROR: Machine $machine is not a supported machine type\n";
        exit 1;
    }

    my @images = <$arago_image_dir/*.bb> or die
        "Failed to read directory $arago_image_dir\n";
    my %image_hash = ();
    my $cnt = 1;
    foreach $x (@images) {
        if (compatible_machine($x, $machine)) {
            my $xs = $x;
            $xs =~ s/.*\/(.*).bb/$1/;
            $image_hash{ $cnt++ } = $xs;
        }
    }

    if (!$image) {
        print "\nAvailable Arago images:\n";
        foreach $x (sort keys %image_hash) {
            print "    $x: $image_hash{ $x }\n";
        }
        print "\nWhich Arago image do you want to include in SDK?\n";
        print "[ 1 ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $image = $image_hash{ $input };
        }
        else {
            $image = $image_hash{ 1 };
        }
    }

    if ($image =~ m/default/i) {
        $image = $image_hash{ 1 };
    }

    if (!$bsp) {
        print "\nDo you want to add BSP packages in SDK? \n";
        print "[ $machines{$machine}{'bsp_default'} ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $bsp = "yes";
            }
            else {
                $bsp = "no";
            }
        }
        else {
            $bsp = $machines{$machine}{'bsp_default'};
        }
    }

    if ($bsp =~ m/default/i) {
        $bsp = $machines{$machine}{'bsp_default'};
    }

    if (!$addons) {
        print "\nDo you want to add addons in SDK? \n";
        print "[ $machines{$machine}{'addons_default'} ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $addons = "yes";
            }
            else {
                $addons = "no";
            }
        }
        else {
            $addons = $machines{$machine}{'addons_default'};
        }
    }

    if ($addons =~ m/default/i) {
        $addons = $machines{$machine}{'addons_default'};
    }

    if (!$crypto) {
        print "\nDo you want to add crypto in SDK? \n";
        print "[ $machines{$machine}{'crypto_default'} ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $crypto = "yes";
            }
            else {
                $crypto = "no";
            }
        }
        else {
            $crypto = $machines{$machine}{'crypto_default'};
        }
    }

    if ($crypto =~ m/default/i) {
        $crypto = $machines{$machine}{'crypto_default'};
    }

    if (!$multimedia) {
        print "\nDo you want to add Multimedia packages in SDK? \n";
        print "[ $machines{$machine}{'multimedia_default'} ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $multimedia = "yes";
            }
            else {
                $multimedia = "no";
            }
        }
        else {
            $multimedia = $machines{$machine}{'multimedia_default'};
        }
    }

    if ($multimedia =~ m/default/i) {
        $multimedia = $machines{$machine}{'multimedia_default'};
    }

    if (!$dsp) {
        print "\nDo you want to add DSP packages in SDK? \n";
        print "[ $machines{$machine}{'dsp_default'} ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $dsp = "yes";
            }
            else {
                $dsp = "no";
            }
        }
        else {
            $dsp = $machines{$machine}{'dsp_default'};
        }
    }

    if ($dsp =~ m/default/i) {
        $dsp = $machines{$machine}{'dsp_default'};
    }

    if (!$graphics) {
        print "\nDo you want to add Graphics packages in SDK? \n";
        print "[ $machines{$machine}{'graphics_default'} ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $graphics = "yes";
            }
            else {
                $graphics = "no";
            }
        }
        else {
            $graphics = $machines{$machine}{'graphics_default'};
        }
    }

    if ($graphics =~ m/default/i) {
        $graphics = $machines{$machine}{'graphics_default'};
    }

    if (!$sdkpath) {
        print "\nWhere do you want to copy Arago sdk ?\n";
        print "(Relative to $arago_dir)\n";
        print "[ $sdkpath_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $sdkpath = "$arago_dir/$input";
        }
        else {
            $sdkpath = "$arago_dir/$sdkpath_default";
        }
    }

    if (-d $sdkpath) {
        print "\nERROR: failed to create '$sdkpath' \n";
        exit 1;
    }

    if ($sdkpath =~ m/default/i) {
        $sdkpath = "$arago_dir/$sdkpath_default";
    }

    if (!$toolchain) {
        print "\nWhat kind of toolchain are you using ?\n";
        print "[ $toolchain_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $toolchain = "$input";
        }
        else {
            $toolchain = "$toolchain_default";
        }
    }

    if ($toolchain =~ m/default/i) {
        $toolchain = "$toolchain_default";
    }

    if ($bsp =~ m/yes/i) {
        $packages[$index++] = $bsp_source;
        $packages[$index++] = $bsp_binary;
        $packages[$index++] = $bsp_sdk_header;
    }

    if ($crypto =~ m/yes/i) {
        $packages[$index++] = $crypto_source;
        $packages[$index++] = $crypto_binary;
        $packages[$index++] = $crypto_sdk_header;
    }

    if ($addons =~ m/yes/i) {
        $packages[$index++] = $addons_source;
        $packages[$index++] = $addons_binary;
    }

    if ($multimedia =~ m/yes/i) {
        $packages[$index++] = $multimedia_source;
        $packages[$index++] = $multimedia_binary;
        $packages[$index++] = $multimedia_sdk_header;
    }

    if ($dsp =~ m/yes/i) {
        $packages[$index++] = $dsp_source;
        $packages[$index++] = $dsp_binary;
        $packages[$index++] = $dsp_sdk_target;
    }

    if ($graphics =~ m/yes/i) {
        $packages[$index++] = $graphics_binary;
        $packages[$index++] = $graphics_src;
        $packages[$index++] = $graphics_sdk_target;
    }

    $packages[$index++] = "ti-tisdk-makefile";
    $packages[$index++] = $image;

}

################################################################################
# parse_args
################################################################################
sub parse_args
{   
    while (@ARGV) {
        if($ARGV[0] eq '-h' || $ARGV[0] eq '--help') {
            shift(@ARGV);
            display_help();
            exit 0;
        }

        if ($ARGV[0] eq '-m' || $ARGV[0] eq '--machine') {
            shift(@ARGV);
            $machine = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-b' || $ARGV[0] eq '--bsp') {
            shift(@ARGV);
            $bsp = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-a' || $ARGV[0] eq '--addons') {
            shift(@ARGV);
            $addons = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-c' || $ARGV[0] eq '--crypto') {
            shift(@ARGV);
            $crypto = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-e' || $ARGV[0] eq '--multimedia') {
            shift(@ARGV);
            $multimedia = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-d' || $ARGV[0] eq '--dsp') {
            shift(@ARGV);
            $dsp = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-i' || $ARGV[0] eq '--image') {
            shift(@ARGV);
            $image = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-g' || $ARGV[0] eq '--graphics') {
            shift(@ARGV);
            $graphics = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-p' || $ARGV[0] eq '--sdkpath') {
            shift(@ARGV);
            $sdkpath = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-t' || $ARGV[0] eq '--toolchain') {
            shift(@ARGV);
            $toolchain = shift(@ARGV);
            next;
        }

        print "Warning: Option $ARGV[0] not supported (ignored)\n";
        shift(@ARGV);
    }
}

################################################################################
# display_help
################################################################################
sub display_help
{
    print "Usage: perl tisdk-build.pl [options]\n\n";
    print "    -h | --help         Display this help message.\n";
    print "    -m | --machine      Machine type to build for.\n";
    print "    -i | --image        Image to build.\n";
    print "    -b | --bsp          Add Board Support Package in SDK.\n";
    print "    -a | --addons       Add Addon demo/utility packages in SDK.\n";
    print "    -c | --crypto       Add crypto packages in SDK.\n";
    print "    -e | --multimedia   Add Multimedia packages in SDK.\n";
    print "    -d | --dsp          Add DSP packages in SDK.\n";
    print "    -g | --graphics     Add Graphics packages in SDK.\n";
    print "    -p | --sdkpath      Where to generate the Arago SDK\n";
    print "    -t | --toolchain    The type of toolchain (arago|codesourcery)\n";
    print "\nIf an option is not given it will be queried interactively.\n";
    print "If the value \"default\" is given for any parameter, the\n";
    print "corresponding default value will be used to build the image\n\n";
}
