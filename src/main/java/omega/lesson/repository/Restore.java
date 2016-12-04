package omega.lesson.repository;

import fpdo.xml.Element;
import fpdo.xml.SAX_node;

public class Restore {
    public static Element restore(String fname) {
	try {
	    omega.Context.sout_log.getLogger().info("ERR: " + "Restore from " + fname);
	    Element el = SAX_node.parse(fname, false);
	    return el;
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "Exception! omega.lesson.repository.Restore.restore(): " + ex);
	    return null;
	}
    }
}
