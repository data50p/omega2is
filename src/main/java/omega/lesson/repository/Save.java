package omega.lesson.repository;

import fpdo.sundry.S;
import fpdo.xml.Element;
import fpdo.xml.XML_PW;

public class Save {
    public static void save(String fname, Element el) {
	XML_PW xmlpw = new XML_PW(S.createPrintWriterUTF8(fname), false);
	xmlpw.put(el);
	xmlpw.close();
//log	omega.Context.sout_log.getLogger().info("ERR: " + "# saved " + el + " " + fname);
    }
}
