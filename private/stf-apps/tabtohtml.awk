BEGIN {
   FS="\t";
   print "<html>";
   print "<head>";

   first = 1;
}

{
    if (first) {
	print "<title>Results: " $1 "</title>";
	print "</head>";
	print "<body>";
	
	print "<h3>" $1 "</h3>";
	print "<table border=\"1\">";
	print "<tbody>";
	
	print "<tr>";
	print "<th>Parameter</th><th>Value</th>";
	print "</tr>";

	first = false;
    }

    print "<tr>";
    print "<td>" $2 "</td><td>" $3 "</td";
    print  "</tr";
}

END {
   print "</tbody>";
   print "</table>";
   print "</body>";
   print "</html>";
}

