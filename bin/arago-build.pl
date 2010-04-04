#!/usr/bin/perl -w

################################################################################
# Arago build script
################################################################################
my $script_version = "0.7";

my @no_machines = ("arago", "include");
my @packages;

my $bsp_source = "task-arago-tisdk-bsp-host";
my $bsp_binary = "task-arago-tisdk-bsp-target";
my $multimedia_source = "task-arago-tisdk-multimedia-host";
my $multimedia_binary = "task-arago-tisdk-multimedia-target";
my $dsp_source = "task-arago-tisdk-dsp-host";
my $dsp_binary = "task-arago-tisdk-dsp-target";

my $image;
my $machine;

my $sdkpath_default = "sdk-cdrom";
my $machine_default = "dm6446-evm";

my %machines = (
    "dm365-evm"     => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
    },
    "dm6446-evm"    => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "yes",
    },
    "dm355-evm"     => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "no",
    },
    "da830-omapl137-evm"     => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "yes",
    },
    "dm6467-evm"     => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "yes",
    },
    "da850-omapl138-evm"     => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "yes",
    },
    "omap3evm"     => {
        bsp_default         => "yes",
        multimedia_default  => "yes",
        dsp_default         => "yes",
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
my $arago_images_output_dir = "$arago_dir/arago-tmp/deploy/images";
my $arago_image_dir = "$arago_dir/arago/recipes/images";
my $arago_ipk_dir = "$arago_dir/arago-tmp/deploy/ipk";
my $arago_machine_dir = "$arago_dir/arago/conf/machine";
my $arago_tmp = "$arago_dir/arago-tmp";
my $arago_staging = "$arago_dir/arago-tmp/staging/x86_64-linux";

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

    print "\nBuilding meta-toolchain-base for $machine";
    $cmd = "MACHINE=$machine META_SDK_PATH=/opt/arago-sdk bitbake meta-toolchain-arago";
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
        $machine =~ m/am3517-evm/) {
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

    $cmd = "rm -rf $sdkpath/deploy/ipk/usr";
    $result = system($cmd);

    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    # TODO: Even if build for omap or davinci platform, opkg.conf may have 
    # entries for both armv5te and armv7a arch. creating temporary directory.
    $cmd = "mkdir -p $sdkpath/deploy/ipk/armv5te $sdkpath/deploy/ipk/armv7a";
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
    
        $cmd = "touch $sdkpath/deploy/ipk/$dir/Packages; $arago_staging/usr/bin/ipkg-make-index -r $sdkpath/deploy/ipk/$dir/Packages -p $sdkpath/deploy/ipk/$dir/Packages -l $sdkpath/deploy/ipk/$dir/Packages.filelist -m $sdkpath/deploy/ipk/$dir";

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
    
    # copy install script
    print "\nCopying $arago_dir/arago/bin/install.sh ...";
    $cmd = "cp $arago_dir/arago/bin/install*.sh $sdkpath";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy opkg and fakeroot command on sdk cdrom, these commands will be
    # used by install.sh during installation.
    print "\nCopying $arago_dir/arago/bin/install-tools.tgz  ...";
    $cmd = "cp $arago_dir/arago/bin/install-tools.tgz  $sdkpath";
    
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy opkg.conf needed during opkg installation.
    print "\nCopying $arago_staging/etc/opkg.conf  ...";
    $cmd = "cp $arago_staging/etc/opkg.conf $sdkpath/config/$machine/";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    $cmd = "$arago_dir/arago/bin/mkllibs.sh $arago_dir/arago-tmp/deploy/sdk/arago*$march-*sdk.tar.gz";    
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to execute command $cmd\n";
        exit 1;
    }

    $cmd = "mv linuxlib*.tar.gz $sdkpath/devel/ ";    
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
sub compatible_machine
{
    my @lines;
    my $found = 1;

    open IMAGEFILE, "<$_[0]" or die "Failed to open $_[0] for reading\n";
    @lines = <IMAGEFILE>;
    close IMAGEFILE;

    for (my $cnt = 0; $cnt < scalar @lines; $cnt++) {
        if ($lines[$cnt] =~ m/.*COMPATIBLE_MACHINE(.*)/) {
            if (not $lines[$cnt] =~ m/COMPATIBLE_MACHINE.*$_[1]/) {
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
    my $index = 0;

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

    if ($sdkpath =~ m/default/i) {
        $sdkpath = "$arago_dir/$sdkpath_default";
    }

    if ($bsp =~ m/yes/i) {
        $packages[$index++] = $bsp_source;
        $packages[$index++] = $bsp_binary;
    }

    if ($multimedia =~ m/yes/i) {
        $packages[$index++] = $multimedia_source;
        $packages[$index++] = $multimedia_binary;
    }

    if ($dsp =~ m/yes/i) {
        $packages[$index++] = $dsp_source;
        $packages[$index++] = $dsp_binary;
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

        if ($ARGV[0] eq '-p' || $ARGV[0] eq '--sdkpath') {
            shift(@ARGV);
            $sdkpath = shift(@ARGV);
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
    print "Usage: perl arago-build.pl [options]\n\n";
    print "    -h | --help         Display this help message.\n";
    print "    -m | --machine      Machine type to build for.\n";
    print "    -i | --image        Image to build.\n";
    print "    -b | --bsp          Add Board Support Package in SDK.\n";
    print "    -e | --multimedia   Add Multimedia packages in SDK.\n";
    print "    -d | --dsp          Add DSP packages in SDK.\n";
    print "    -p | --sdkpath      Where to generate the Arago SDK\n";
    print "\nIf an option is not given it will be queried interactively.\n";
    print "If the value \"default\" is given for any parameter, the\n";
    print "corresponding default value will be used to build the image\n\n";
}
