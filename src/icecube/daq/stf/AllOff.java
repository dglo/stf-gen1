import icecube.daq.domhub.common.*;

import java.rmi.Naming;
import java.util.Iterator;

public class AllOff {
   public static void main(String [] args) {
      if (args.length!=1) {
	 System.out.println("usage: AllOff domhub_host");
	 return;
      }

      String rmiHost = args[0];
      String dhUrl = "rmi://" + rmiHost + "/domhubapp";
      
      try {
	 DOMHubCom dhc = (DOMHubCom)Naming.lookup(dhUrl);
	 
	 CabledChannelStatusSet ccsSet = dhc.getCabledChannelStatusSet();
	 
	 Iterator it = ccsSet.iterator();
	 while (it.hasNext()){
	    CabledChannelStatus cs = (CabledChannelStatus) it.next();
	    
	    if (cs.isPowered()) {
	       cs = dhc.disableChannelPwr(cs.getCard(), cs.getChannel());
	    }
	 }
      } catch (Exception e) {
	 e.printStackTrace(System.out);
      }
   }
}
