#!/usr/bin/perl -w

use HTML::Parser ();

my $url = $ARGV[0];
shift (@ARGV);

foreach my $file (@ARGV) {
    process_page($file);
}

################################################################################
# remove_div
################################################################################
sub remove_div
{
    my $lines = $_[0];
    my $div = $_[1];
    my $found = 0;

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        if ($found) {
            if (@$lines[$cnt] =~ m/<\/div>/i) {
                @$lines[$cnt] =~ s/^.*<\/div>(.*)$/$1/i;
                $found = 0;
            }
            else {
                @$lines[$cnt] = "";
            }
        }
        elsif (@$lines[$cnt] =~ m/<div $div>/i) {
            if (@$lines[$cnt] =~ m/<div $div>.*<\/div>/i) {
                @$lines[$cnt] =~ s/^(.*)<div $div>(?:(?!<\/div>).)*<\/div>(.*)$/$1$2/i;
            }
            else {
                @$lines[$cnt] =~ s/^(.*)<div $div>.*$/$1/i;
                $found = 1;
            }
        }
    }
}

################################################################################
# remove_footer
################################################################################
sub remove_footer
{
    my $lines = $_[0];
    my $found = 0;

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        if (@$lines[$cnt] =~ m/NewPP limit report/) {
            $found = 1;
            @$lines[$cnt] = "--></body></html>";
        }
        elsif ($found) {
            @$lines[$cnt] = "";
        }
    }
}

################################################################################
# remove_cdata
################################################################################
sub remove_cdata
{
    my $lines = $_[0];

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        @$lines[$cnt] =~ s/\/\*<\!\[CDATA\[\*\///i;
    }
}

################################################################################
# remove_link
################################################################################
sub remove_link
{
    my $lines = $_[0];
    my $pattern = $_[1];

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        @$lines[$cnt] =~ s/<link.*$pattern[^>]*>//i;
    }
}

################################################################################
# remove_script
################################################################################
sub remove_script
{
    my $lines = $_[0];
    my $type = $_[1];
    my $found = 0;

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        if ($found) {
            if (@$lines[$cnt] =~ m/<\/script>/i) {
                @$lines[$cnt] =~ s/^.*<\/script>(.*)$/$1/i;
                $found = 0;
            }
            else {
                @$lines[$cnt] = "";
            }
        }
        elsif (@$lines[$cnt] =~ m/<script.*type=.*$type[^>]*>/i) {
            if (@$lines[$cnt] =~ m/<script.*type=.*$type[^>]*>.*<\/script>/i) {
                @$lines[$cnt] =~ s/^(.*)<script.*type=.*$type[^>]*>(?:(?!<\/script>).)*<\/script>(.*)$/$1$2/i;
            }
            else {
                @$lines[$cnt] =~ s/^(.*)<script.*type=.*$type[^>]*>.*$/$1/i;
                $found = 1;
            }
        }
    }
}

################################################################################
# insert_header
################################################################################
sub insert_header
{
    my $lines = $_[0];

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        @$lines[$cnt] =~ s/(.*)<h3(.*)From Texas Instruments Embedded Processors Wiki(.*)/$1<h3$2This is a snapshot generated from the <a href=\"$url\">Texas Instruments Embedded Processors Wiki$3/;
        @$lines[$cnt] =~ s/<h3.*Ap-fpdsp-swapps<\/h3>//;
    }
}

################################################################################
# insert name anchors
################################################################################
sub insert_name_anchors
{
    my $lines = $_[0];

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        if (@$lines[$cnt] =~ m/(.*)class=\"mw-headline\" id=(.*)> (.*)/i) {
            @$lines[$cnt] = "$1class=\"mw-headline\" id=$2> <a name=$2></a>$3\n";
        }
    }
}

################################################################################
# change_title
################################################################################
sub change_title
{
    my $lines = $_[0];

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        if (@$lines[$cnt] =~ m/(.*)<title>(.*) -.*<\/title>(.*)/i) {
            @$lines[$cnt] = "$1<title>$2<\/title>$3\n";
        }
    }
}

################################################################################
# remove_search
################################################################################
sub remove_search
{
    my $lines = $_[0];

    for (my $cnt = 0; $cnt < scalar @$lines; $cnt++) {
        if (@$lines[$cnt] =~ m/http:\/\/processors\.wiki\.ti\.com\/index\.php\/File:Google-16x16.png/i) {
            @$lines[$cnt] = "";
        }
        if (@$lines[$cnt] =~ m/<form action="http:\/\/processors\.wiki\.ti\.com\/index\.php"/i) {
            @$lines[$cnt] = "";
        }
        if (@$lines[$cnt] =~ m/googleSearchFrameborder/i) {
            @$lines[$cnt] = "";
        }
        if (@$lines[$cnt] =~ m/src="http:\/\/processors\.wiki\.ti\.com/i) {
            @$lines[$cnt] = "";
        }
    }
}

################################################################################
# process_page
################################################################################
sub process_page
{
    my $srcfile = "$_[0]";
    my $dstfile = "$_[0]";
    my @lines;

    open FILE, "<$srcfile" or die "Failed to open $srcfile for reading";
    @lines = <FILE>;
    close FILE;

    remove_search(\@lines);

    remove_div(\@lines, "id=\"jump-to-nav\"");
    remove_div(\@lines, "id=\"BreadCrumbsTrail\"");

    remove_link(\@lines, "rel=\"search\"");
    remove_link(\@lines, "title=\"Creative Commons\"");
    remove_link(\@lines, "title=\"Texas Instruments Embedded Processors Wiki RSS Feed\"");
    remove_link(\@lines, "title=\"Texas Instruments Embedded Processors Wiki Atom Feed\"");
    remove_link(\@lines, "rel=\"copyright\"");

    remove_script(\@lines, "\"text/javascript\"");

    remove_footer(\@lines);

    remove_cdata(\@lines);

    insert_header(\@lines);

    change_title(\@lines);

    # This is a workaround for a bug in htmldoc that does not recognize 
    # internal links
    insert_name_anchors(\@lines);

    open FILE, ">$dstfile" or die "Failed to open $dstfile for writing";
    for (my $cnt = 0; $cnt < scalar @lines; $cnt++) {
        print FILE "$lines[$cnt]";
    }
    close FILE;
}
