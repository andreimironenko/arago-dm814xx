#!/usr/bin/perl -w

################################################################################
# Arago build script
################################################################################
my $script_version = "0.5";

my @no_machines = ("arago", "include");
my @packages;

my $bsp_source = "task-tisdk-bsp-sourcetree";
my $multimedia_source = "task-tisdk-multimedia-sourcetree";
my $dsp_source = "task-tisdk-dsp-sourcetree";

my $bsp_default = "yes";
my $multimedia_default = "yes";
my $dsp_default = "no";

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
my $arago_images_output_dir = "$arago_dir/arago-deploy/images";
my $arago_image_dir = "$arago_dir/arago/recipes/images";
my $arago_ipk_dir = "$arago_dir/arago-deploy/ipk";
my $arago_machine_dir = "$arago_dir/arago/conf/machine";
my $arago_tmp = "$arago_dir/arago-tmp";

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
}

################################################################################
# copy_task_recommended
################################################################################
sub copy_task_recommended
{
    my $result;
    my $cmd;
	my $task_name = $_[0];
	my $category = $_[1];

	# TODO: figure out a way to use opkg command to query information from
	# .ipk. until then we will use dpkg to query the ipk information.
	
   	$cmd = "dpkg -I $task_name\_*.ipk  | grep Recommends | cut -f2 -d:";
	open (CMD, "$cmd |");
		while (<CMD>) {
		(@recommends) = split(/,/,$_);
	}
	close(CMD);

	# go through the recommended packages and copy them individually on sdk
	foreach (@recommends) {
		$_ =~ s/^\s+//;
	 	$_ =~ s/\s+$//;
		
		my $ipk = "$arago_ipk_dir/$machine/$_*.ipk";

		print "\n + $_";
		$cmd = "cp $ipk $sdkpath/$machine/$category";
    	$result = system($cmd);
    	if ($result) {
        	print "\nERROR: Failed to execute command $cmd\n";
        	exit 1;
    	}
	}
}

################################################################################
# copy_output
################################################################################
sub copy_output
{
    my $result;
    my $cmd;

	print "\nCopying ...";
	foreach (@packages) {
		# If it's an image then copy image tarball in bsp directory
		if ($_ =~ m/image/) {
			my $image_file = "$arago_images_output_dir/$machine/$_-$machine.tar.gz";
			print "\n + $_-$machine.tar.gz";

			$cmd = "mkdir -p  $sdkpath/$machine/bsp";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command $cmd\n";
        		exit 1;
    		}

			$cmd = "cp $image_file $sdkpath/$machine/bsp";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command $cmd\n";
        		exit 1;
    		}

			print "\n + Downloading linux-davinci-staging.tar.gz\n";
			# TODO: we don't have recipe for generating linux kernel source
			# until then wget linux-davinci tar ball
			$cmd = "wget http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvsdk/DVSDK_3_10/latest/exports/linux-davinci-staging.tar.gz -P ${sdkpath}/${machine}/bsp";
			$result = system($cmd);
			if ($result) {
				print "\nERROR: Failed to execute command $cmd\n";
				exit 1;
			}

			print "\n + Downloading /arago-2009.11-armv5te-linux-gnueabi-sdk.tar.gz\n";
			# TODO: we don't have recipe to generate linuxlibs tarball yet, 
			# until then wget arago sdk tarball and use script to convert arago
			# in linuxlib format
			$cmd = "wget http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/dvsdk/DVSDK_3_10/latest/exports/arago-2009.11-armv5te-linux-gnueabi-sdk.tar.gz -P ${sdkpath}/${machine}/bsp";
			$result = system($cmd);
			if ($result) {
				print "\nERROR: Failed to execute command $cmd";
				exit 1;
			}			
		}
		elsif($_ =~ m/task/)  {
			my @recommends;
			my $category;

			if ($_ =~ m/bsp/) {
				$category = "bsp";
			}
			elsif ($_ =~ m/multimedia/) {
				$category = "multimedia";
			}
			elsif ($_ =~ m/dsp/) {
				$category = "dsp";
			}
			elsif ($_ =~ m/graphics/) {
				$category = "graphics";
			}
			else {
				$category = "";
			}
	
			$cmd = "mkdir -p  $sdkpath/$machine/$category";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command $cmd\n";
        		exit 1;
    		}
			
			print "\n + $_";
			$cmd = "cp $arago_ipk_dir/$machine/$_\_*.ipk $sdkpath/$machine/$category";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command $cmd\n";
        		exit 1;
    		}

			# if its a task then copy all the recommended packages
			copy_task_recommended("$arago_ipk_dir/$machine/$_", "$category");

		}
		else {
			my $ipk = "$arago_ipk_dir/$machine/$_\_*.ipk";
			
			print "\n + $_";

			$cmd = "mkdir -p  $sdkpath/$machine";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command $cmd\n";
        		exit 1;
    		}

			$cmd = "cp $ipk $sdkpath/$machine";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command $cmd\n";
        		exit 1;
    		}
		}
	}

    # copy install.sh in top label directory
    $cmd = "cp $arago_dir/arago/bin/install.sh $sdkpath";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
        exit 1;
    }

    # copy platform directory top label directory
    $cmd = "cp $arago_dir/arago/bin/$machine/* $sdkpath/$machine/";
    $result = system($cmd);

    if ($result) {
        print "\n ERROR: failed to execute $cmd\n";
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
                print "    $cnt: $xs \n";
                $machine_hash{ $cnt++ } = $xs;
            }
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


    	if (!$image) {
        	print "\nAvailable Arago images:\n";
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

			$packages[$index++] = $image;
    	}

    	if (!$bsp) {
        	print "\nDo you want to add BSP packages in SDK? \n";
        	print "[ $bsp_default ] ";
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
            	$bsp = $bsp_default;
        	}

          	if ($bsp =~ m/yes/i) {
				$packages[$index++] = $bsp_source;
          	}
    	}

    	if (!$multimedia) {
        	print "\nDo you want to add Multimedia packages in SDK? \n";
        	print "[ $multimedia_default ] ";
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
            	$multimedia = $multimedia_default;
        	}

          	if ($multimedia =~ m/yes/i) {
				$packages[$index++] = $multimedia_source;
          	}
    	}

    	if (!$dsp) {
        	print "\nDo you want to add DSP packages in SDK? \n";
        	print "[ $dsp_default ] ";
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
            	$dsp = $dsp_default;
        	}

          	if ($dsp =~ m/yes/i) {
				$packages[$index++] = $dsp_source;
          	}
    	}

    	if (!$sdkpath) {
        	$sdkpath_default = "sdk-cdrom";
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

			$packages[$index++] = "ti-tisdk-tools";
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

        if ($ARGV[0] eq '-o' || $ARGV[0] eq '--outpath') {
            shift(@ARGV);
            $sdkpath = shift(@ARGV);
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
    print "    -i | --image        Image to build.\n";
    print "    -b | --bsp          Add Board Support Package in SDK.\n";
    print "    -e | --multimedia   Add Multimedia packages in SDK.\n";
    print "    -d | --dsp          Add DSP packages in SDK.\n";
    print "    -p | --sdkpath      Where to generate the Arago SDK\n";
    print "\nIf an option is not given it will be queried interactively.\n\n";
}
