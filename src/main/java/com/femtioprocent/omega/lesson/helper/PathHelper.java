package com.femtioprocent.omega.lesson.helper;

import com.femtioprocent.omega.OmegaContext;
import com.femtioprocent.omega.adm.assets.TargetCombinations;
import com.femtioprocent.omega.lesson.repository.Restore;
import com.femtioprocent.omega.lesson.repository.Save;
import com.femtioprocent.omega.util.Log;
import com.femtioprocent.omega.xml.Element;

import java.util.Set;

/**
 * Created by lars on 2017-05-21.
 */
public class PathHelper {
    Set<TargetCombinations.TCItem> dep_set;

    public PathHelper(Set<TargetCombinations.TCItem> dep_set) {
        this.dep_set = dep_set;
    }

    public void perform() {
        for(TargetCombinations.TCItem tci : dep_set) {
	    String aFname = OmegaContext.omegaAssets(tci.fn);
            if ( !aFname.endsWith(".omega_anim") ) {
		Log.getLogger().info("Skip: wrong file name " + aFname);
		continue;
	    }
	    Log.getLogger().info("Fix path: " + aFname);
	    Element el = Restore.restore(OmegaContext.omegaAssets(aFname));
	    String version = el.findAttr("version");
	    String clazz = el.findAttr("class");
	    if ( !"Animation".equals(clazz) ) {
		Log.getLogger().info("Skip: class " + clazz);
		continue;
	    }
	    if ( "0.1".equals(version) ) {
		Log.getLogger().info("Skip: version " + version);
		continue;
	    }
	    Log.getLogger().info("Loaded: " + el);
	    el.addAttr("version", "0.1");
	    Save.saveWithBackup(aFname, ".0.0", el);
	    Log.getLogger().info("Saved: " + el);
	}
    }
}
