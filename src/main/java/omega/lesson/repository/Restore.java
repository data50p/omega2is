package omega.lesson.repository;

import omega.OmegaContext;
import omega.util.Log;
import omega.xml.Element;
import omega.xml.SAX_node;

public class Restore {
    public static Element restore(String fname) {
        try {
            String aName;
            if (fname.startsWith(OmegaContext.omegaAssets(""))) {
                Log.getLogger().info("Already assets: " + fname);
                aName = fname;
            } else {
                aName = OmegaContext.omegaAssets(fname);
            }
            OmegaContext.sout_log.getLogger().info("ERR: " + "Restore from (~A) " + fname + " -> (A) " + aName);
            Element el = SAX_node.parse(aName, false);
            return el;
        } catch (Exception ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Exception! omega.lesson.repository.Restore.restore(): " + ex);
            return null;
        }
    }
}
