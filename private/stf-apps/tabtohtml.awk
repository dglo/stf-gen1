BEGIN {
   print "<html>";
   print "<head>";
   print "<title>Results: " "</title>";
   print "</head>";
   print "<body>";
   
   print "<h3>""</h3>";
   print "<table border=\"1\">";
   print "<tbody>";
   
   print "<tr>";
   print "<th>Parameter</th><th>Value</th>";
   print "</tr>";
}

{
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

