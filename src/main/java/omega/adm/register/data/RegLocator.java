package omega.adm.register.data;

import fpdo.sundry.S;
import omega.i18n.T;
import omega.lesson.appl.ApplContext;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class RegLocator {
    static String fbase = "register";
    static String PUPIL_SUF = ".p";
    static String RESULT_SUF = ".omega_result";

    public RegLocator() {
    }

    static String[] scanDir(String dir, FilenameFilter fnf) {
//log	omega.Context.sout_log.getLogger().info("ERR: " + "scan " + dir + ' ' + fnf);
	File df = new File(dir);
	File[] fa = df.listFiles(fnf);
	if (fa != null) {
	    String[] sa = new String[fa.length];
	    for (int i = 0; i < fa.length; i++)
		sa[i] = dir + File.separatorChar + fa[i].getName();
	    Arrays.sort(sa);
	    return sa;
	}
	return null;
    }

    String[] removeSuffix(String[] sa, String suf) {
	if (sa == null)
	    return new String[0];
	String nsa[] = new String[sa.length];
	for (int i = 0; i < sa.length; i++)
	    if (sa[i].endsWith(suf))
		nsa[i] = sa[i].substring(0, sa[i].length() - suf.length());
	    else
		nsa[i] = sa[i];
	return nsa;
    }

    String[] removePrefix(String[] sa, String pre) {
	if (sa == null)
	    return new String[0];
	String nsa[] = new String[sa.length];
	for (int i = 0; i < sa.length; i++)
	    if (sa[i].startsWith(pre))
		nsa[i] = sa[i].substring(pre.length());
	    else
		nsa[i] = sa[i];
	return nsa;
    }

    public String[] getAllPupilsName() {
	String sa[] = scanDir(fbase, new FilenameFilterExt(PUPIL_SUF));
	return removePrefix(removeSuffix(sa, PUPIL_SUF), fbase + File.separatorChar);
    }

    public String[] getAllResultsFName(String pupil, String[] with) {
	String sa[] = scanDir(fbase + File.separatorChar + pupil + PUPIL_SUF, new FilenameFilterExt(RESULT_SUF, with));
	return sa;
    }

    public String getFullFName(String pupil, String lesson_name) {
	String s = fbase + File.separatorChar +
		pupil + PUPIL_SUF + File.separatorChar +
	    /*pupil + '-' +*/ lesson_name + RESULT_SUF;
	return s;
    }

    public String getDirPath(String pupil) {
	return fbase + File.separatorChar +
		pupil + PUPIL_SUF + File.separatorChar;
    }

    String getTestSuffix() {
	return RESULT_SUF;
    }

    public String mkResultsFName(String pupil, String name) {
	String s =
		fbase + File.separatorChar +
			pupil + PUPIL_SUF + File.separatorChar +
			name + RESULT_SUF;
	return s;
    }

    public void createPupilName(String name) {
	File f_old = new File("register/" + name + ".deleted");
	File f = new File("register/" + name + ".p");
	if (f.exists()) {
	    JOptionPane.showMessageDialog(ApplContext.top_frame,
		    T.t("Pupil exist already"));
	}
	if (f_old.exists()) {
	    JOptionPane.showMessageDialog(ApplContext.top_frame,
		    T.t("Pupil reinstalled"));
	    f_old.renameTo(f);
	} else {
	    f.mkdir();
	    File ft = new File("register/" + name + ".p/id.png");
	    File ff = new File("media/default/pupil.png");
	    omega.util.Files.fileCopy(ff, ft);
	    ft = new File("register/" + name + ".p/pupil_settings.xml");
	    ff = new File("register/" + "Guest" + ".p/pupil_settings.xml");
	    omega.util.Files.fileCopy(ff, ft);
	}
    }

    public static void main(String[] args) {
	RegLocator l = new RegLocator();
	String sa[] = l.getAllPupilsName();
	omega.Context.sout_log.getLogger().info("ERR: " + "" + S.a2s(sa));
	sa = l.getAllResultsFName("Lars", new String[]{"test"});
	omega.Context.sout_log.getLogger().info("ERR: " + "" + S.a2s(sa));
    }
}