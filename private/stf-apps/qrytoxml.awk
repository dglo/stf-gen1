#
# convert lines of query string into an xml file
#
BEGIN { 
  FS="\t";
  print "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
  print "<test>"
  print "  <name>" test "</name>"
}

!/^test\t/ {
  if ( $1 != "" ) {
    print "  <inputParameter>";
    print "    <name>" $1 "</name>";
    print "    <value>" $2 "</value>";
    print "  </inputParameter>";
  }
}

END {
  print "</test>"
}
