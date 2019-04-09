#!/usr/bin/perl
use strict;
use warnings;
 
buildjavafile();
 
sub buildjavafile{

	my $srcFilename = $ARGV[0];

	#read original file
	if (open(IN,"$srcFilename")  or die "Couldn't open srcFilename input $srcFilename" ){
		print "this srcFilename:$srcFilename success to open\n";	
		}
	else{	
		print "this srcFilename:$srcFilename failed to open";
		die "end at $srcFilename";
	}
	#tell the system to handle the string with binary mode, or else perl runtime will add the addtional carriage return when it need to new line in text mode.
	binmode IN;			
	open(OUT, ">$srcFilename.bak") or die "Couldn't open file $srcFilename.bak,$!";
	#this is what we need to add at the top of the file.
	my $templine;
	my $startclass=0;
	my $matchfunc = 0;
	while(<IN>){
		binmode OUT;
		$templine=$_;
		#print "this line:$templine\n";
		#should escape replace(/[-[\]{}()*+?.,\\^$|#\s]/g, '\\$&')
		if($_=~ m/\s*public\s+class\s+(\w+)[\s|\{].*/){			
			#print OUT $templine;
			print  "\nCONTENT: get class:$1\n";
			print OUT "public class $1 {\n";
			#print  "\nCONTENT: added\n $allGrantStatements";
			$startclass = 1;
		}elsif( $_=~ m/\s*public\s+\w+\s+(\w+).*\(.*/){# TODO: match the general function signature, think more cases, the function signature may code into multiple lines
			#print  "\nCONTENT: get function:$1\n";
			print OUT $templine;
		}
		else {#TODO: other cases need to stub

		}
	}
	
	if($startclass == 1){
		print OUT "}\n";
	}

	close(IN);
	close(OUT);
	# my $tmpfilename=$srcFilename;

	# rename "$tmpfilename", "$tmpfilename.original";
	# rename "$tmpfilename.bak", "$tmpfilename";
	
	# unlink "$tmpfilename.original";
	# unlink "$tmpfilename.bak";

}