package omega.adm.register.data;

import fpdo.sundry.S;
import fpdo.xml.Element;
import fpdo.xml.XML_PW;
import omega.OmegaContext;

public class SaveRestore {
    public boolean save(String fname, Result rslt) {
        Element el = rslt.getElement();
        OmegaContext.sout_log.getLogger().info("ERR: " + "saving SaveRestore el " + el);
        try (XML_PW xmlpw = new XML_PW(S.createPrintWriterUTF8(fname), false)) {
            xmlpw.put(el);
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
