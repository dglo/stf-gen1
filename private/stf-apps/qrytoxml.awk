#
# convert lines of query string into an xml file
#
BEGIN { 
  FS="\t";
  print "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
  print 
    "<stf:setup xmlns:stf=\"http://glacier.lbl.gov/icecube/daq/stf\""
    "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
    "  xsi:schemaLocation=\"http://glacier.lbl.gov/icecube/daq/stf stf.xsd\">";

  print "<" test ">";
  print "  <parameters>";
}

!/^test\t/ {
  if ( $1 != "" ) {
    print "  <" $1 ">" $2 "</" $1 ">";
  }
}

END {
  print "  </parameters>";
  print "</" test ">";
  print "</stf:setup>";
}
