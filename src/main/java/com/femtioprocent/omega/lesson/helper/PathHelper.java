package com.femtioprocent.omega.lesson.helper;

import com.femtioprocent.omega.OmegaContext;
import com.femtioprocent.omega.adm.assets.TargetCombinations;
import com.femtioprocent.omega.anim.tool.path.Path;
import com.femtioprocent.omega.lesson.repository.Restore;
import com.femtioprocent.omega.lesson.repository.Save;
import com.femtioprocent.omega.util.DelimitedStringBuilder;
import com.femtioprocent.omega.util.Log;
import com.femtioprocent.omega.xml.Element;

import java.awt.geom.Point2D;
import java.util.Arrays;
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
	    if ( false && "0.1".equals(version) ) {
		Log.getLogger().info("Skip: version " + version);
		continue;
	    }
	    Log.getLogger().info("Loaded: " + el);
	    fixIt(el);
	    el.addAttr("version", "0.1");
	    Save.saveWithBackup(aFname, ".0.0", el);
	    Log.getLogger().info("Saved: " + el);
	}
    }

    private void fixIt(Element el) {
	Element el_ac = el.findFirstElement("AnimCanvas");
	Element el_ap = el_ac.findFirstElement("AllPath");
	for (int i = 0; i < 10; i++) {
	    Element el_tp = el.findElement("TPath", i);
	    if ( el_tp == null )
	        continue;
	    Log.getLogger().info("fix: q " + el_tp.findAttr("nid"));
	    for (int j = 0; j < 100; j++) {
		Element el_q = el.findElement("q", j);
		if ( el_q == null )
		    continue;
		Log.getLogger().info("fix: q " + el_tp.findAttr("ord") + ' ' + el_q);
	    }
	    Path p = new Path(el_tp);
	    Log.getLogger().info("fix: Path " + p);
	    double[] lenArr = p.getLenA();
	    Point2D[] point2d = p.getPoint2D();
	    Log.getLogger().info("          " + Path.format(lenArr));
	    Log.getLogger().info("          " + Path.format(point2d));
	    Element el_i = el_tp.findElement("info", 0);
	    if ( el_i == null ) {
		el_i = new Element("info");
		el_tp.add(el_i);
	    }
	    el_i.addAttr("len", Path.format(lenArr));
	    el_i.addAttr("seg", Path.format(point2d));
	}
    }
}
