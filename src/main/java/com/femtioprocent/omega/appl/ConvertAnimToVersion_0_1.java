package com.femtioprocent.omega.appl;

import com.femtioprocent.omega.adm.assets.TargetCombinations;
import com.femtioprocent.omega.lesson.helper.PathHelper;
import com.femtioprocent.omega.util.SundryUtils;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lars on 2017-05-22.
 */
public class ConvertAnimToVersion_0_1 {
    static HashMap flags;
    static List argl;

    public static void main(String[] args) {
	System.err.println("Started");
	flags = SundryUtils.flagAsMap(args);
	argl = SundryUtils.argAsList(args);
	ConvertAnimToVersion_0_1 c01 = new ConvertAnimToVersion_0_1();
	c01.start();
    }

    public void start() {
	Set<TargetCombinations.TCItem> dep_set = new HashSet<>();
	fill(dep_set);
	PathHelper ph = new PathHelper(dep_set);
	ph.perform();
    }

    private void fill(Set<TargetCombinations.TCItem> dep_set) {
	File dir = new File(".");
	fill(dep_set, dir);
    }

    private void fill(Set<TargetCombinations.TCItem> dep_set, File dir) {
	final File[] files = dir.listFiles();
	for(File f : files) {
	    if ( f.isDirectory() ) {
	        fill(dep_set, f);
	    } else {
	        if (f.getName().endsWith(".omega_anim") ) {
		    TargetCombinations.TCItem tci = createTCI(f);
		    dep_set.add(tci);
		}
	        else
		    System.err.println("Ignore " + f.getAbsolutePath());
	    }
	}
    }

    private TargetCombinations.TCItem createTCI(File f) {
	System.out.println("convert " + f.getAbsolutePath());
	TargetCombinations.TCItem tci = new TargetCombinations.TCItem(f.getAbsolutePath());
	return tci;
    }
}
