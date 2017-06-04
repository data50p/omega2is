package com.femtioprocent.omega.appl;

import com.femtioprocent.omega.OmegaConfig;
import com.femtioprocent.omega.adm.assets.TargetCombinations;
import com.femtioprocent.omega.lesson.helper.PathHelper;
import com.femtioprocent.omega.util.Log;
import com.femtioprocent.omega.util.SundryUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by lars on 2017-05-22.
 */
public class ConvertAnimToVersion_0_1 {
    static HashMap<String,String> flags;
    static List<String> argl;
    File baseDir = null;

    static double flatness = OmegaConfig.FLATNESS;

    ConvertAnimToVersion_0_1(File baseDir) {
        this.baseDir = baseDir;
	String flatnessF = (String) flags.get("flatness");
	if ( flatnessF != null) {
	    double flatness = Double.valueOf(flatnessF);
	    OmegaConfig.FLATNESS = flatness;
	}
    }

    public static void main(String[] args) {
        if ( args.length == 0 ) {
	    System.err.println("-b=<backupExt> -d=dir -status -flatness=value");
	    System.exit(99);
	}
	Log.getLogger().info("Started");
	flags = SundryUtils.flagAsMap(args);
	argl = SundryUtils.argAsList(args);
	String baseDir = ".";

	if ( flags.get("X") != null ) {
	    XSSFWorkbook wb = new XSSFWorkbook();
	    System.err.println("WB: " + wb);
	    XSSFSheet sheet = wb.createSheet("my sheet");
	    System.err.println("sheet: " + sheet);
	    XSSFRow row = sheet.createRow(0);
	    System.err.println("row: " + row);
	    XSSFCell cell = row.createCell(0);
	    System.err.println("cell: " + cell);
	    cell.setCellValue("hello world");
	    sheet.autoSizeColumn(0);
	    try {
		FileOutputStream out = new FileOutputStream("ZZ-example.xlsx");
		wb.write(out);
		wb.close();
	    } catch (IOException ex) {
		System.err.println("" + ex);
	    }
	    System.exit(0);
	}

	String dir = flags.get("d");
	if ( dir != null )
	    baseDir = dir;
	ConvertAnimToVersion_0_1 c01 = new ConvertAnimToVersion_0_1(new File(baseDir));
	c01.start();
    }

    public void start() {
	Set<TargetCombinations.TCItem> dep_set = new HashSet<>();
	fill(dep_set);
	keep(dep_set, ".omega_anim");

	PathHelper ph = new PathHelper(dep_set);
	String doBackup = flags.get("b");
	if ( flags.get("status") != null )
	    ph.performStatus();
	else
	    ph.perform(doBackup);
    }

    private void keep(Set<TargetCombinations.TCItem> dep_set, String s) {
	Iterator<TargetCombinations.TCItem> it = dep_set.iterator();
	while (it.hasNext()) {
	    final TargetCombinations.TCItem next = it.next();
	    if ( next.fn.endsWith(s) )
	        continue;
	    it.remove();
	}
    }

    private void fill(Set<TargetCombinations.TCItem> dep_set) {
	fill(dep_set, baseDir);
    }

    private void fill(Set<TargetCombinations.TCItem> dep_set, File dir) {
	final File[] files = dir.listFiles();
	for(File f : files) {
	    if ( f.isDirectory() ) {
	        fill(dep_set, f);
	    } else {
	        if (f.getName().endsWith(".omega_anim") ) {
		    TargetCombinations.TCItem tci = createTCI(f);
		    if ( tci != null )
		        dep_set.add(tci);
		}
	        else
		    Log.getLogger().info("Ignore " + f.getAbsolutePath());
	    }
	}
    }

    private TargetCombinations.TCItem createTCI(File f) {
	try {
	    Log.getLogger().info("convert " + f.getCanonicalPath());
	    TargetCombinations.TCItem tci = new TargetCombinations.TCItem(f.getCanonicalPath());
	    return tci;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
