#
# convert lines of summary info to an html table...
#
BEGIN {
  print "<table border=\"1\">\n  <tbody>";
  print "<tr><th>Date</th><
}

{
  print $1 " " $2;
}

END {
  print "  </tbody>\n</table>";
}
