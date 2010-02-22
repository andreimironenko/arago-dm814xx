#!/usr/bin/perl -w

################################################################################
# Arago build script
################################################################################
my $script_version = "0.3";

my $image = "";

my $sdk = "";
my $sdk_default = "yes";

my $keep = "";
my $keep_default = "no";

my $sdktarkeep = "";
my $sdktarkeep_default = "no";

my $sdkpath = "";
my $sdkpath_default = "arago-sdk";

my @no_machines = ("arago", "include");

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
my $arago_image_dir = "$arago_dir/arago/recipes/images";
my $arago_machine_dir = "$arago_dir/arago/conf/machine";
my $arago_tmp = "$arago_dir/arago-tmp";

if (! -d "$arago_image_dir") {
    print "ERROR: $arago_dir/arago/recipes/images not found! Either your ";
    print "\$OE_BASE variable is not pointing correctly or your Arago ";
    print "installation is broken\n";
    exit 1;
}

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

if (!$keep eq "yes") {
    remove_tmp();
}

if ($sdk eq "yes") {
    generate_sdk();
    extract_sdk();

    if (!$sdktarkeep eq "yes") {
        remove_sdktar();
    }
}

print "\nBuild of Arago completed\n";

################################################################################
# remove_tmp
################################################################################
sub remove_tmp
{
    my $result;
    my $cmd;

    print "\nRemoving arago-tmp\n";
    if (-e $arago_tmp) {
        $cmd = "rm -rf $arago_tmp";
        $result = system($cmd);
        if ($result) {
            print "ERROR: Failed to execute $cmd\n";
            exit 1;
        }
    }
    else {
        print "WARNING: $arago_tmp doesn't exist.\n";
    }
}

################################################################################
# remove_sdktar
################################################################################
sub remove_sdktar
{
    my $result;
    my $cmd;

    my @sdk_files = <$arago_dir/arago-deploy/sdk/*> or die
        "Failed to read directory $arago_dir/arago-deploy/sdk\n";

    foreach (@sdk_files) {
        print "\nRemoving $_\n";
        $cmd = "rm -f $_";
        $result = system($cmd);
        if ($result) {
            print "ERROR: Failed to delete $_\n";
            exit 1;
        }
    }
}

################################################################################
# extract_sdk
################################################################################
sub extract_sdk
{
    my $result;
    my $cmd;
    my $del;
    my $del_default = "yes";

    print "\nExtracting SDK\n";
    if (-d "$sdkpath") {
        print "$sdkpath already exists. Do you want to remove this ";
        print "directory and recreated the SDK?";
        print "[ $del_default ] ";
        my $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $del = "yes";
            }
            else {
                $del = "no";
            }
        }
        else {
            $del = $del_default;
        }

        if ($del eq "yes") {
            $cmd = "rm -rf $sdkpath";
            $result = system($cmd);
            if ($result) {
                print "ERROR: Failed to remove $sdkpath";
                exit 1;
            }
        }
        else {
            print "Directory already exists, not extracting SDK\n";
            return;
        }
    }

    my $arago_dir_numdirs = @{[$arago_dir =~ /\//g]};  
    $arago_dir_numdirs += 1;

    my @sdk_files = <$arago_dir/arago-deploy/sdk/*> or die
        "Failed to read directory $arago_dir/arago-deploy/sdk\n";

    foreach (@sdk_files) {
        $cmd = "tar xz --strip $arago_dir_numdirs -C $arago_dir -f $_";
        $result = system($cmd);
        if ($result) {
            print "ERROR: Failed to extract $_\n";
            exit 1;
        }
    }
}

################################################################################
# generate_sdk
################################################################################
sub generate_sdk
{
    my $result;
    my $cmd;
    my $sdk_name;

    $sdk_name = $image;
    $sdk_name =~ s/arago-(.*)/meta-$1/;

    print "\nGenerating SDK $sdk_name\n";
    $cmd = "MACHINE=$machine META_SDK_PATH=$sdkpath bitbake $sdk_name -c clean";

    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to clean SDK $sdk_name\n";
        exit 1;
    }

    $cmd = "MACHINE=$machine META_SDK_PATH=$sdkpath bitbake $sdk_name";

    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to generate SDK $sdk_name\n";
        exit 1;
    }
}

################################################################################
# build_image
################################################################################
sub build_image
{
    my $result;
    my $cmd;

    print "\nBuilding $image for $machine\n";

    $cmd = "MACHINE=$machine bitbake $image";
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to build $image for $machine\n";
        exit 1;
    }

    $cmd = "MACHINE=$machine bitbake board-set";
    $result = system($cmd);
    if ($result) {
        print "\nERROR: Failed to build board-set for $machine\n";
        exit 1;
    }
}

################################################################################
# validate_input
################################################################################
sub validate_input
{
    if (! -e "$arago_image_dir/$image.bb") {
        print "ERROR: Image $arago_image_dir/$image.bb not found\n";
        exit 1;
    }

    if (! -e "$arago_machine_dir/$machine.conf") {
        print "ERROR: Machine $arago_machine_dir/$machine.conf not found\n";
        exit 1;
    }

    if (-e "$sdkpath" && ! -w "$sdkpath") {
        print "ERROR: $sdkpath is not writeable by user\n";
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

    if (!$machine) {
        print "\nAvailable Arago machine types:\n";
        my @machines = <$arago_machine_dir/*> or die
            "Failed to read directory $arago_machine_dir\n";
        my @machine_hash = ();
        my $cnt = 1;
        foreach $x (@machines) {
            my $skip = 0;
            my $xs = $x;
            $xs =~ s/.*\/(.*).conf/$1/;
            foreach $y (@no_machines) {
                if ($xs =~ m/.*$y.*/) {
                    $skip = 1;
                }
            }
            if (not $skip) {
                $machine_hash{ $cnt++ } = $xs;
            }
        }
        foreach $x (sort keys %machine_hash) {
            print "    $x: $machine_hash{ $x }\n";
        }
        print "\nWhich Arago machine type to you want to build for?\n";
        print "[ 1 ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $machine = $machine_hash{ $input };
        }
        else {
            $machine = $machine_hash{ 1 };
        }
    }

    if (!$image) {
        print "\nAvailable Arago images:\n";
        my @images = <$arago_image_dir/*> or die
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
        foreach $x (sort keys %image_hash) {
            print "    $x: $image_hash{ $x }\n";
        }
        print "\nWhich Arago image do you want to build?\n";
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

    if (!$keep) {
        print "\nDo you want to keep arago-tmp after building Arago?\n";
        print "(Deleting it saves space)\n";
        print "[ $keep_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $keep = "yes";
            }
            else {
                $keep = "no";
            }
        }
        else {
            $keep = $keep_default;
        }
    }

    if (!$sdk) {
        print "\nDo you want to generate an Arago SDK?\n";
        print "[ $sdk_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $sdk = "yes";
            }
            else {
                $sdk = "no";
            }
        }
        else {
            $sdk = $sdk_default;
        }
    }

    if ($sdk eq "yes" && !$sdkpath) {
        $sdkpath_default = "$image-sdk-$machine";
        print "\nWhere do you want to generate the Arago SDK?\n";
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

    if ($sdk eq "yes" && !$sdktarkeep) {
        print "\nDo you want to keep the SDK tar ball after extracting it?\n";
        print "(Deleting it saves space, but you may want to reextract it ";
        print "later manually)\n";
        print "[ $sdktarkeep_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/y/i) {
                $sdktarkeep = "yes";
            }
            else {
                $sdktarkeep = "no";
            }
        }
        else {
            $sdktarkeep = $sdktarkeep_default;
        }
    }
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

        if ($ARGV[0] eq '-i' || $ARGV[0] eq '--image') {
            shift(@ARGV);
            $image = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-k' || $ARGV[0] eq '--keep') {
            shift(@ARGV);
            $keep = "yes";
            next;
        }

        if ($ARGV[0] eq '-s' || $ARGV[0] eq '--nosdk') {
            shift(@ARGV);
            $sdk = "no";
            next;
        }

        if ($ARGV[0] eq '-p' || $ARGV[0] eq '--sdkpath') {
            shift(@ARGV);
            $sdkpath = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-d' || $ARGV[0] eq '--sdktarkeep') {
            shift(@ARGV);
            $sdktarkeep = "yes";
            next;
        }

        if ($ARGV[0] =~ /-.*/) {
            print "Warning: Option $ARGV[0] not supported (ignored)\n";
        }
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
    print "    -i | --image        Image to build\n";
    print "    -k | --keep         Don't delete arago-tmp when done.\n";
    print "    -s | --nosdk        Do not generate an Arago SDK\n";
    print "    -d | --sdktarkeep   Do not delete SDK tar.gz after extracting\n";
    print "    -p | --sdkpath      Where to generate the Arago SDK\n";
    print "\nIf an option is not given it will be queried interactively.\n\n";
}
