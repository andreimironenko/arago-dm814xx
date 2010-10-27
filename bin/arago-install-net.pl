#!/usr/bin/perl -w

################################################################################
# Arago network installation script
################################################################################
my $script_version = "0.7";

my $toolchain = "";
my $toolchain_default = "$ENV{'HOME'}/arm-2009q1";

my $installdir = "";
my $installdir_default = "$ENV{'HOME'}/arago-install";

my $protocol = "";
my $protocol_default = "git";

my $aragocommit_default        = "HEAD";
my $aragogitrepository_default = "arago-project.org/git/people/siddharth/arago-c6a8.git";

my $auto_install = 0;

my %git_repositories = (
    "arago"         => [ "", "" ],
    "arago-bitbake" => [ "", "arago-project.org/git/arago-bitbake.git" ],
    "arago-oe-dev"  => [ "", "arago-project.org/git/arago-oe-dev.git" ],
);

my %host_tools = (
    "git"    => [ "git-core", "http://git-scm.com" ],
    "wget"   => [ "wget",     "http://www.gnu.org/software/wget" ],
    "unzip"  => [ "unzip",    "http://www.info-zip.org/UnZip.html" ],
    "tar"    => [ "tar",      "http://www.gnu.org/software/tar" ],
    "cvs"    => [ "cvs",      "http://www.nongnu.org/cvs" ],
    "sed"    => [ "sed",      "ftp://ftp.gnu.org/gnu/sed" ],
    "python" => [ "python",   "http://www.python.org/download" ],
    "flex"   => [ "flex",     "http://www.gnu.org/software/flex" ],
    "bison"  => [ "bison",    "http://www.gnu.org/software/bison" ],
);

my $http_proxy;
my $ftp_proxy;
my $git_proxy;

################################################################################
# main
################################################################################

if ($ENV{'http_proxy'}) {
    $http_proxy = "$ENV{'http_proxy'}";
}
else {
    $http_proxy = "N/A";
}

if ($ENV{'ftp_proxy'}) {
    $ftp_proxy = "$ENV{'ftp_proxy'}";
}
else {
    $ftp_proxy = "N/A";
}

if ($ENV{'GIT_PROXY_COMMAND'}) {
    $git_proxy = "$ENV{'GIT_PROXY_COMMAND'}";

}
else {
    $git_proxy = "N/A";
}

print "********************************** INSTALL SCRIPT ******************************\n";
print "Arago install script version $script_version\n\n";
print "This script assumes you already have installed the CodeSourcery tool ";
print "chain, see:\n\n";
print "http://arago-project.org/wiki/index.php/Getting_CodeSourcery_Toolchain\n\n";
print "If you are behind a firewall the \"http_proxy\" and \"ftp_proxy\" ";
print "environment variables need to be set correctly, see:\n\n";
print "http://arago-project.org/wiki/index.php/Proxy_Settings\n\n";
print "Currently these variables are set to:\n";
print "http_proxy = $http_proxy\n";
print "ftp_proxy = $ftp_proxy\n\n";
print "In addition you may want to set the \"GIT_PROXY_COMMAND\" environment ";
print "variable to enable git to work through the firewall (not necessary, ";
print "but faster). This variable is currently set to:\n\n";
print "GIT_PROXY_COMMAND = $git_proxy\n\n";

if ($git_proxy ne "N/A") {
    if (open FILE, "<$git_proxy") {
        print "The file pointed to by \"GIT_PROXY_COMMAND\" contains:\n\n";
        print <FILE>;
        close FILE;
        print "\n\n";
    }
    else {
        print "Warning: Failed to open $git_proxy\n";
    }
}

parse_args();

if ($update) {
    update_arago();
}

validate_host_tools();

get_input();

validate_input();

print "\n******************************** END INSTALL SCRIPT ****************************\n\n";

fetch_arago();

print "\n********************************** INSTALL SCRIPT ******************************\n\n";

create_env();

print "\nArago successfully installed in $installdir.\n";
print "\n******************************** END INSTALL SCRIPT ****************************\n\n";

################################################################################
# execute_cmd
################################################################################
sub execute_cmd
{
    print "Executing '$_[0]'\n";
    my $result = system($_[0]);
    if ($result) {
        print "Failed to execute $_[0] ($result)\n";
        exit 1;
    }
}

################################################################################
# update_arago
################################################################################
sub update_arago
{
    my $cmd;

    if (! exists $ENV{'OEBASE'}) {
        print "ERROR: Arago environment variables not set, did you ";
        print "'source arago-setenv'?\n";
        exit 1;
    }

    my $installdir = $ENV{'OEBASE'};

    print "Updating arago..\n";

    print "Changing directory to $installdir/arago\n";
    chdir "$installdir/arago" or die
        "Failed to change directory to $installdir/arago\n";
 
    execute_cmd("git checkout master");
    execute_cmd("git pull");
    execute_cmd("git checkout install");
    execute_cmd("git merge master");

    print "Updating arago-oe-dev..\n";
    my $file="$installdir/arago/bin/arago-oe-dev-commitid";
    open FILE, "<$file" or die "Failed to open $file for reading\n";
    chomp($git_repositories{ "arago-oe-dev" }[0] = <FILE>);
    close FILE;

    print "Changing directory to $installdir/arago-oe-dev\n";
    chdir "$installdir/arago-oe-dev" or die
        "Failed to change directory to $installdir/arago-oe-dev\n";
 
    execute_cmd("git checkout master");
    execute_cmd("git branch -D install");
    execute_cmd("git pull");
    execute_cmd("git checkout " . $git_repositories{"arago-oe-dev"}[0] . " -b install");

    print "Updating arago-bitbake..\n";
    $file="$installdir/arago/bin/arago-bitbake-commitid";
    open FILE, "<$file" or die "Failed to open $file for reading\n";
    chomp($git_repositories{ "arago-bitbake" }[0] = <FILE>);
    close FILE;

    print "Changing directory to $installdir/arago-bitbake\n";
    chdir "$installdir/arago-bitbake" or die
        "Failed to change directory to $installdir/arago-bitbake\n";
 
    execute_cmd("git checkout master");
    execute_cmd("git branch -D install");
    execute_cmd("git pull");
    execute_cmd("git checkout " . $git_repositories{"arago-bitbake"}[0] . " -b install");

    chdir $installdir or die "Failed to change directory to $installdir\n";

    print "\nArago updated\n";

    exit 0;
}

################################################################################
# test_ubuntu
################################################################################
sub test_ubuntu
{
    my @lines;
    my $file = "/etc/issue";

    if (-e $file) {
        if (not open FILE, "<$file") {
            print "Warning: Failed to open $file\n";
        }
        else {
            @lines = <FILE>;
            for (my $cnt = 0; $cnt < scalar @lines; $cnt++) {
                if ($lines[$cnt] =~ m/.*Ubuntu.*8\.04.*/) {
                    close FILE;
                    return 1;
                }
            }
            close FILE;
        }
    }

    return 0;
}

################################################################################
# validate_host_tools
################################################################################
sub validate_host_tools
{
    my $result;
    my $cmd;
    my $supported;

    print "Checking if you are using supported Ubuntu 8.04 LTS.. ";
    $supported = test_ubuntu();
    if ($supported) {
        print "Yes\n";
    }
    else {
        print "No\n";
    }
    print "Validating that required host tools are in your \$PATH. ";
    print "These should be available from your linux distribution:\n\n";

    foreach $x (keys %host_tools) {
        $cmd = "which $x";
        print "$x found at: ";
        $result = system($cmd);
        if ($result) {
            print "not found!\n";
            if ($supported) {
                if ($auto_install) {
                    execute_cmd("sudo apt-get install $host_tools{ $x }[0]");
                }
                else {
                    print "Do you want to install $x ";
                    print "('$host_tools{ $x }[0]' package)? ";
                    print "This requires sudo access!\n[ Yes ] ";
                    my $input = <STDIN>;
                    $input =~ s/\s+$//;

                    if (!$input || $input =~ m/y/i) {
                        ubuntu_install($host_tools{ $x }[0]);
                    }
                    else {
                        print "Error: $x is not found in your \$PATH. You can ";
                        print "install it manually using 'sudo apt-get ";
                        print "install $host_tools{ $x }[0]' or download and ";
                        print "compile it from $host_tools{ $x }[1].\n";
                        exit;
                    }
                }
            }
            else {
                print "Error: $x is not found in your \$PATH. If this ";
                print "tool is not part of your linux distribution you ";
                print "can download and compile it from ";
                print "$host_tools{ $x }[1].\n";
                exit;
            }
        }
    }

    print "\nFound all host tools successfully, continuing..\n\n";
}

################################################################################
# create_env
################################################################################
sub create_env
{
    my @lines;
    my $line;
    my $file = "$installdir/arago-setenv";
    open FILE, ">$file" or die "Failed to create $file\n";

    print FILE "export PATH=\"$toolchain/bin:$installdir/arago/bin:\$PATH\"\n";
    print FILE "export META_SDK_PATH=\"$toolchain\"\n";
    print FILE "source $installdir/arago/setenv\n";

    close FILE;

    $file = "$installdir/arago/setenv.sample";

    open ENVFILE, "<$file" or die "Failed to open $file for reading\n";
    @lines = <ENVFILE>;
    close ENVFILE;

    $file = "$installdir/arago/setenv";

    open ENVFILE, ">$file" or die "Failed to open $file for writing\n";
    for (my $cnt = 0; $cnt < scalar @lines; $cnt++) {
        $lines[$cnt] =~ s/export OEBASE=.*/export OEBASE=$installdir/;
        print ENVFILE "$lines[$cnt]";
    }
    close ENVFILE;

    $file = "$installdir/arago/conf/local.conf.sample";

    open ENVFILE, "<$file" or die "Failed to open $file for reading\n";
    @lines = <ENVFILE>;
    close ENVFILE;

    $file = "$installdir/arago/conf/local.conf";

    open ENVFILE, ">$file" or die "Failed to open $file for writing\n";
    for (my $cnt = 0; $cnt < scalar @lines; $cnt++) {
        print ENVFILE "$lines[$cnt]";
    }
    close ENVFILE;

    print "IMPORTANT!\n";
    print "\nA script setting up the Arago environment has been saved to ",
          "'$installdir/arago-setenv' which can be loaded in bash using ",
          "'source $installdir/arago-setenv'. This needs to be set in every ",
          "shell you work with Arago. It's suggested you add this command to ",
          "e.g. .bashrc.\n";
}

################################################################################
# fetch_arago
################################################################################
sub fetch_arago
{
    if (! -d $installdir) {
        mkdir "$installdir", 0777 or die
            "Failed to create directory '$installdir'\n";
    }
    elsif (! -w $installdir) {
        print "Error: You don't have write access to $installdir\n";
        exit;
    }

    print "Changing directory to $installdir\n";
    chdir "$installdir" or die
        "Failed to change directory to $installdir";

    fetch_git("arago");

    my $file="$installdir/arago/bin/arago-oe-dev-commitid";
    open FILE, "<$file" or die "Failed to open $file for reading\n";
    chomp($git_repositories{ "arago-oe-dev" }[0] = <FILE>);
    close FILE;

    fetch_git("arago-oe-dev");

    $file="$installdir/arago/bin/arago-bitbake-commitid";
    open FILE, "<$file" or die "Failed to open $file for reading\n";
    chomp($git_repositories{ "arago-bitbake" }[0] = <FILE>);
    close FILE;

    fetch_git("arago-bitbake");
}

################################################################################
# fetch_git
################################################################################
sub fetch_git
{
    my $result;
    my $cmd;

    $cmd = "git clone $protocol://$git_repositories{ $_[0] }[1] $_[0]";
    print "Executing '$cmd'\n";

    $result = system($cmd);
    if ($result) {
        print "'$cmd' failed ($result)\n";
        if ($protocol eq "git") {
            print "Trying http...\n";
            $protocol = "http";
            execute_cmd("git clone $protocol://$git_repositories{ $_[0] }[1] $_[0]");
        }
        else {
            exit 1;
        }
    }

    print "Changing directory to $installdir/$_[0]\n";
    chdir "$installdir/$_[0]" or die
        "Failed to change directory to $installdir/$_[0]";
    execute_cmd("git checkout $git_repositories{ $_[0] }[0] -b install");

    print "Changing directory to $installdir\n";
    chdir "$installdir" or die
        "Failed to change directory to $installdir";
}

################################################################################
# validate_input
################################################################################
sub validate_input
{
    my $toolchain_gcc = "$toolchain/bin/arm-none-linux-gnueabi-gcc";

    if (! -e $toolchain_gcc) {
        print "Error: $toolchain_gcc not found\n";
        exit;
    }

    if ($protocol ne "git" && $protocol ne "http" && $protocol ne "ssh") {
        print "Error: $protocol is not a supported (http, ssh, git) protocol\n";
        exit;
    }
}

################################################################################
# get_input
################################################################################
sub get_input
{
    my $input;

    if (!$toolchain) {
        print "Where is Code Sourcery installed?\n";
        print "[ $toolchain_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $toolchain = $input;
        }
        else {
            $toolchain = $toolchain_default;
        }
    }

    if (!$installdir) {
        print "Where do you want to install Arago?\n";
        print "[ $installdir_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $installdir = $input;
        }
        else {
            $installdir = $installdir_default;
        }
    }

    if (!$git_repositories{ "arago" }[1]) {
        print "From which git repository do you want to get Arago?\n";
        print "[ $aragogitrepository_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            if ($input =~ m/(\w+):\/\//) {
                $input =~ s/(\w+):\/\///;
                $protocol_default = $1;
            }
            $git_repositories{ "arago" }[1] = $input;
        }
        else {
            $git_repositories{ "arago" }[1] = $aragogitrepository_default;
        }
    }

    if (!$protocol) {
        print "Use the http, ssh or git protocol to fetch Arago?\n";
        print "[ $protocol_default ] ";
        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $protocol = $input;
        }
        else {
            $protocol = $protocol_default;
        }
    }

    if (!$git_repositories{ "arago" }[0]) {
        print "Which 'commit id/tags' do you want to use for the Arago recipes?";
        print " ('arago' directory)\n";
        print "[ $aragocommit_default ] ";

        $input = <STDIN>;
        $input =~ s/\s+$//;

        if ($input) {
            $git_repositories{ "arago" }[0] = $input;
        }
        else {
            $git_repositories{ "arago" }[0] = $aragocommit_default;
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
            exit;
        }

        if ($ARGV[0] eq '-t' || $ARGV[0] eq '--toolchain') {
            shift(@ARGV);
            $toolchain = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-p' || $ARGV[0] eq '--protocol') {
            shift(@ARGV);
            $protocol = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-i' || $ARGV[0] eq '--installdir') {
            shift(@ARGV);
            $installdir = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '-g' || $ARGV[0] eq '--aragogit') {
            shift(@ARGV);
            my $repo = shift(@ARGV);
            if ($repo =~ m/(\w+):\/\//) {
                $repo =~ s/(\w+):\/\///;
                $protocol_default = $1;
            }
            $git_repositories{ "arago" }[1] = $repo;
            next;
        }

        if ($ARGV[0] eq '--arago-commit') {
            shift(@ARGV);
            $git_repositories{ "arago" }[0] = shift(@ARGV);
            next;
        }

        if ($ARGV[0] eq '--install') {
            shift(@ARGV);
            $auto_install = 1;
            next;
        }

        if ($ARGV[0] eq '--update') {
            shift(@ARGV);
            $update = 1;
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
    print "Usage: perl arago-install-net.pl [options]\n\n";
    print "    -h | --help         Display this help message.\n";
    print "    -t | --toolchain    Path to the CodeSourcery tool chain.\n";
    print "    -p | --protocol     Protocol to use for fetching files using\n";
    print "                        git (git, http or ssh available).\n";
    print "    -i | --installdir   Where to install Arago.\n";
    print "    -g | --aragogit     The URL of the Arago GIT repository.\n";
    print "         --arago-commit The commit ID (SHA1 or tag) to set\n";
    print "                        arago to after checkout.\n";
    print "         --install      Install missing dependencies on Ubuntu\n";
    print "                        8.04 LTS without asking (but sudo may\n";
    print "                        still block and ask for a password).\n";
    print "         --update       Update your Arago installation. You need\n";
    print "                        to 'source arago-setenv' from a previous\n";
    print "                        install before using this option\n";
    print "\nIf an option is not given it will be queried interactively.\n\n";
}
