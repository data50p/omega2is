package omega.util;

import fpdo.sundry.S;
import omega.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Files {

    // urldir, name

    static public String[] splitUrlString(String url_s) {
	File fi = new File(Context.omegaAssets("."));
	String cdu = null;
	try {
	    cdu = fi.toURI().toURL().toString();
	} catch (MalformedURLException ex) {
	    return null;
	}
	cdu = cdu.substring(0, cdu.length() - 2);
	cdu += "media/";
	if (omega.Config.T)
	    omega.Context.sout_log.getLogger().info("ERR: " + "try loading url from\n" + url_s + '\n' + cdu);
	int len_cd = cdu.length();

	String[] sa = new String[2];
	String name = url_s.substring(len_cd);
	if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "+++ " + name);
	sa[0] = cdu;
	sa[1] = name;
	if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "" + S.arrToString(sa));
	return sa;
    }

    static public String rmHead(String fn) {
	try {
	    File fi = new File(".");
	    String cdu = null;
	    try {
		cdu = fi.toURI().toURL().toString();
	    } catch (MalformedURLException ex) {
		return null;
	    }
	    cdu = cdu.substring(0, cdu.length() - 2);
//	cdu += "media/";
	    omega.Context.sout_log.getLogger().info("ERR: " + "rmHead " + fn + " -> " + cdu);
	    int len_cd = cdu.length();

	    String[] sa = new String[2];
	    String name = fn.substring(len_cd);
	    if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "+++ " + name);
	    sa[0] = cdu;
	    sa[1] = name;
	    if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "" + S.arrToString(sa));
	    return sa[1];
	} catch (StringIndexOutOfBoundsException ex) {
	}
	return null;
    }

    static public String mkRelFname(String url_s) {
	File fi = new File(Context.omegaAssets("."));// ".");
	String cdu = null;
	try {
	    cdu = fi.toURI().toURL().toString();
	} catch (MalformedURLException ex) {
	    return null;
	}
	cdu = cdu.substring(0, cdu.length() - 2);
//log	omega.Context.sout_log.getLogger().info("ERR: " + "rmHead\n" + url_s + '\n' + cdu);
	int len_cd = cdu.length();

	String[] sa = new String[2];
	String name = url_s.substring(len_cd);
	if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "+++ " + name);
	sa[0] = cdu;
	sa[1] = name;
	if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "" + S.arrToString(sa));
	return sa[1];
    }

    static public String mkRelFname(String url_s, String prefix) {
	File fi = new File(Context.omegaAssets("."));
	String cdu = null;
	try {
	    cdu = fi.toURI().toURL().toString();
	} catch (MalformedURLException ex) {
	    return null;
	}
	cdu = cdu.substring(0, cdu.length() - 2);
//log	omega.Context.sout_log.getLogger().info("ERR: " + "rmHead\n" + url_s + '\n' + cdu);
	int len_cd = cdu.length();

	String[] sa = new String[2];
	String name = url_s.substring(len_cd);
	if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "+++ " + name);
	sa[0] = cdu;
	sa[1] = name;
	if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "" + S.arrToString(sa));
	if (sa[1].startsWith(prefix))
	    return sa[1].substring(prefix.length() + 1);
	return null;
    }

    public static String toURL(File file) {
	String url_s = null;
//log	omega.Context.sout_log.getLogger().info("ERR: " + "got file " + file);
	try {
	    URL url = file.toURI().toURL();
	    URL url2 = file.toURL();
	    Log.getLogger().warning("URL matter: " + file + ' ' + url + ' ' + url2);
	    url_s = url2.toString();
	} catch (Exception ex) {
	    Context.exc_log.getLogger().throwing(Files.class.getName(), "toURL", ex);
	}
	return url_s;
    }

    public static void fileCopy(File from, File to) {
	try {
	    FileInputStream fr = new FileInputStream(from);
	    FileOutputStream fw = new FileOutputStream(to);
	    byte[] buf = new byte[10240];

	    for (; ; ) {
		int n = fr.read(buf);
		if (n > 0) {
		    fw.write(buf, 0, n);
		} else {
		    break;
		}
	    }
	    fr.close();
	    fw.close();
	} catch (Exception edx) {
	}
    }
}
