package omega.adm.register.data;

import fpdo.sundry.S;
import fpdo.xml.Element;
import fpdo.xml.XML_PW;

public class SaveRestore {
    public boolean save(String fname, Result rslt) {
	Element el = rslt.getElement();
	omega.Context.sout_log.getLogger().info("ERR: " + "saving SaveRestore el " + el);
	try {
	    XML_PW xmlpw = new XML_PW(S.createPrintWriterUTF8(fname), false);
	    xmlpw.put(el);
	    xmlpw.close();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	}
	return true;
    }

    public Result restore(String fname) {
	return null;
    }
}
