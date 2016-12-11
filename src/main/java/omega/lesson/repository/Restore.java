package omega.lesson.repository;

import fpdo.xml.Element;
import fpdo.xml.SAX_node;
import omega.Context;

public class Restore {
    public static Element restore(String fname) {
	try {
	    String aName;
	    if ( fname.startsWith(Context.omegaAssets(""))) {
		System.err.println("Already assets: " + fname);
		aName = fname;
	    } else {
		aName = Context.omegaAssets(fname);
	    }
	    omega.Context.sout_log.getLogger().info("ERR: " + "Restore from (~A) " + fname + " -> (A) " + aName);
	    Element el = SAX_node.parse(aName, false);
	    return el;
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "Exception! omega.lesson.repository.Restore.restore(): " + ex);
	    return null;
	}
    }
}
