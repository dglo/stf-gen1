/^\(/ { 
   if ($1 == "(" param ) {
      inParameter = 1;
   }
}

/^\)/ { 
   if ($1 == ")" param ) {
      inParameter = 0;
   }
}

{ 
   if (inParameter>0) {
      if (inParameter>1) { print substr($0, 2); }
      inParameter++; 
   }
}

