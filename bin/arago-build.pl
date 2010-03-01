#!/usr/bin/perl -w

################################################################################
# Arago build script
################################################################################
my $script_version = "0.4";

my @no_machines = ("arago", "include");
my @images;

my $filesystem_image = "arago-base-image";
my $psp_source = "task-tisdk-psp-sourcetree";
my $multimedia_source = "task-tisdk-multimedia-sourcetree";
my $dsp_source = "task-tisdk-dsp-sourcetree";

my $psp_default = "no";
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
my $arago_images_dir = "$arago_dir/arago-deploy/images";
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

	foreach (@images) {
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
# copy_output
################################################################################
sub copy_output
{
    my $result;
    my $cmd;

	print "\nCopying files ...";
	foreach (@images) {

		if ($_ =~ m/image/) {
			my $image_file = "$arago_images_dir/$machine/$_-$machine.tar.gz";
			print "\n + $_-$machine.tar.gz -> lsp";

			$cmd = "mkdir -p  $sdkpath/$machine/lsp";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command\n";
        		exit 1;
    		}

			$cmd = "cp $image_file $sdkpath/$machine/lsp";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command\n";
        		exit 1;
    		}
		}
		elsif($_ =~ m/task/)  {
			my @recommends;
			my $category;

			if ($_ =~ m/lsp/) {
				$category = "lsp";
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
	
    		$cmd = "dpkg -I $arago_dir/arago-deploy/ipk/$machine/$_\_*.ipk  | grep Recommends | cut -f2 -d:";

			open (CMD, "$cmd |");
			while (<CMD>) {
				(@recommends) = split(/,/,$_);
			}
			close(CMD);
			
			foreach (@recommends) {
			 	$_ =~ s/^\s+//;
			 	$_ =~ s/\s+$//;
		
				my $ipk = "$arago_dir/arago-deploy/ipk/$machine/$_*.ipk";

				$cmd = "mkdir -p  $sdkpath/$machine/$category";
    			$result = system($cmd);
    			if ($result) {
        			print "\nERROR: Failed to execute command\n";
        			exit 1;
    			}
				
				print "\n + $_ ipk -> $category";

				$cmd = "cp $ipk $sdkpath/$machine/$category";
    			$result = system($cmd);
    			if ($result) {
        			print "\nERROR: Failed to execute command\n";
        			exit 1;
    			}
			}
		}
		else {
			my $ipk = "$arago_dir/arago-deploy/ipk/$machine/$_\_*.ipk";
			
			print "\n + $_ ipk";

			$cmd = "mkdir -p  $sdkpath/$machine";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command\n";
        		exit 1;
    		}

			$cmd = "cp $ipk $sdkpath/$machine";
    		$result = system($cmd);
    		if ($result) {
        		print "\nERROR: Failed to execute command\n";
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

		$images[$index++] = $filesystem_image;

    	if (!$psp) {
        	print "\nDo you want to add PSP packages in SDK? \n";
        	print "[ $psp_default ] ";
        	$input = <STDIN>;
        	$input =~ s/\s+$//;

        	if ($input) {
            	if ($input =~ m/y/i) {
                	$psp = "yes";
            	}
            	else {
                	$psp = "no";
            	}
        	}
        	else {
            	$psp = $psp_default;
        	}

          	if ($psp =~ m/yes/i) {
				$images[$index++] = $psp_source;
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
				$images[$index++] = $multimedia_source;
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
				$images[$index++] = $dsp_source;
          	}
    	}

    	if (!$sdkpath) {
        	$sdkpath_default = "sdk-cdrom";
        	print "\nWhere do you want to copy the Arago binaries ?\n";
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

			$images[$index++] = "ti-tisdk-tools";
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

        if ($ARGV[0] eq '-p' || $ARGV[0] eq '--psp') {
            shift(@ARGV);
            $psp = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-m' || $ARGV[0] eq '--multimedia') {
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
    print "    -p | --sdkpath      Where to generate the Arago SDK\n";
    print "\nIf an option is not given it will be queried interactively.\n\n";
}
