package omega.lesson.repository;

import omega.OmegaContext;
import omega.util.Log;
import omega.util.SundryUtils;
import omega.xml.Element;
import omega.xml.XML_PW;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Save {
    public static void save(String fname, Element el) {
	List stories = el.find("story");
	if (stories != null && stories.size() > 0) {
	    Element sel = (Element) stories.get(0);
	    String isfirst = sel.findAttr("isfirst");
	    if (isfirst != null) {
		if (fname.contains("active")) {
		    addStoryFileIndicator(fname);
		}
	    }
	}

	try (XML_PW xmlpw = new XML_PW(SundryUtils.createPrintWriterUTF8(fname), false)) {
	    xmlpw.put(el);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void addStoryFileIndicator(String fname) {
        Log.getLogger().fine("ADD story file");
        File file = new File(OmegaContext.omegaAssets(fname));
        File dir = file.getParentFile().getParentFile();
        File storyFile = new File(dir, "story");
        if ( ! storyFile.exists() ) {
	    try {
		boolean b = storyFile.createNewFile();
		Log.getLogger().info("ADDED story file " + storyFile + ' ' + b);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
