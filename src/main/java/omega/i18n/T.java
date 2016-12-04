package omega.i18n;

import fpdo.sundry.S;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class T {
    static HashMap hm;
    static HashMap hm_new;
    public static String lang = "en";
    static String lang_country = "en_US";
    static T tt = new T();

    public T() {
	String la = omega.Context.omega_lang;
	omega.Context.lesson_log.getLogger().info("T lang p is from omega_lang " + la);
	if (la == null) {
	    la = System.getProperty("lang");
	    omega.Context.lesson_log.getLogger().info("T lang p is from -Domega_lang " + la);
	}

	omega.Context.lesson_log.getLogger().info("omega gui lang is now " + la);
	Locale loc;
	if (la != null) {
	    lang = before_(la);
	    lang_country = la;
	} else {
	    loc = Locale.getDefault();
	    omega.Context.sout_log.getLogger().info("ERR: " + "locale " + loc);
	    lang = loc.getLanguage();
	    lang_country = loc.getLanguage() + '_' + loc.getCountry();
	    if ("no".equals(lang)) {
		omega.Context.lesson_log.getLogger().info("no -> nb " + loc);
		lang = "nb";
		lang_country = "nb_NO";
	    }
	}

	omega.Context.lesson_log.getLogger().info("FINALLY omega lang " + omega.Context.omega_lang);
	omega.Context.lesson_log.getLogger().info("FINALLY lang " + lang);
	omega.Context.lesson_log.getLogger().info("FINALLY lang_country " + lang_country);
    }

    private String before_(String s) {
	int ix = s.indexOf('_');
	if (ix == -1)
	    return s;
	return s.substring(0, ix);
    }

    private static FileInputStream fopen(String fn, String fn2, int[] who) {
	who[0] = -1;
	FileInputStream in = null;
	try {
	    in = new FileInputStream(fn);
	    omega.Context.sout_log.getLogger().info("ERR: " + "T file is " + fn);
	    who[0] = 0;
	} catch (FileNotFoundException ex) {
	    try {
		if (fn2 != null) {
		    in = new FileInputStream(fn2);
		    omega.Context.sout_log.getLogger().info("ERR: " + "T file is " + fn2);
		    who[0] = 1;
		}
	    } catch (FileNotFoundException ex2) {
		return null;
	    }
	}
	return in;
    }

    static int fillFrom(String fn, String fn2, HashMap hm_) {
	int[] who = new int[1];

	try {
	    FileInputStream in = fopen(fn, fn2, who);
	    if (in == null)
		return -1;
	    InputStreamReader ir = new InputStreamReader(in);
	    BufferedReader br = new BufferedReader(ir);

	    String enc = br.readLine();
	    if ("utf-8".equals(enc))
		omega.Context.sout_log.getLogger().info("ERR: " + "T_ enc is " + fn + ' ' + fn2 + ' ' + enc);
	    else
		enc = null;

	    in = fopen(fn, fn2, who);
	    if (enc == null)
		ir = new InputStreamReader(in);
	    else
		ir = new InputStreamReader(in, enc);
	    br = new BufferedReader(ir);

	    br.readLine();

	    try {
		int cnt = 0;
		for (; ; ) {
		    String s = br.readLine();
		    if (s == null) {
			omega.Context.sout_log.getLogger().info("ERR: " + "rL: null");
			break;
		    }
		    String sa[] = s.split("[\\]\\[]+");
		    if (cnt < 10)
			omega.Context.sout_log.getLogger().info("ERR: " + "got T_ " + sa.length + ' ' + S.a2s(sa));
		    if (sa.length == 3)
			hm_.put(sa[1], sa[2]);
		    else
			omega.Context.sout_log.getLogger().info("ERR: " + "i18n.T.t strange " + S.a2s(sa));
		    cnt++;
		}
		br.close();
	    } catch (IOException ex) {
	    }
	} catch (IOException ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "T_ " + ex);
	}
	return who[0];
    }

    static int fillFromXML(String fn, String fn2, HashMap hm_) {
	try {
	    int[] who = new int[1];

	    FileInputStream in = fopen(fn + ".xml", fn2 + ".xml", who);
	    if (in == null)
		return -1;
	    InputStreamReader ir = new InputStreamReader(in);

	    XMLDecoder d = new XMLDecoder(new BufferedInputStream(in));
	    Object result = d.readObject();
	    d.close();
	    if (!(result instanceof HashMap)) {
		return -1;
	    }
	    omega.Context.sout_log.getLogger().info("ERR: " + "T_xml " + ((HashMap) result).size());

	    hm_.putAll((HashMap) result);
	    return who[0];
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "T_ " + ex);
	}
	return -1;
    }

    static synchronized void init() {
	if (hm == null) {
	    hm = new HashMap();
	    int whox = fillFromXML("T_" + lang_country, "T_" + lang, hm);
	    if (whox == -1) {
		int who = fillFrom("T_" + lang_country, "T_" + lang, hm);
		if (whox == -1 && who != -1)
		    putXML(hm, who == 0 ? "T_" + lang_country : "T_" + lang);
	    }
	}
    }

    static private void putEncoding() {
	File f = new File("T_new");
	if (f.exists() && f.length() > 0)
	    return;
	PrintWriter pw = S.createPrintWriter("T_new");
	pw.println("utf-8");
	pw.close();
    }

    synchronized static private void putXML(HashMap hm, String fn) {
	try {
	    XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fn + ".xml")));
	    e.writeObject(hm);
	    e.close();
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "" + ex);
	}
    }

    static class MyThread extends Thread {
	public void run() {
	    S.m_sleep(5000);
	    putXML(hm_new, "T_new");
	    mythread = null;
	}
    }

    static MyThread mythread = null;

    public static String t(String s) {
	init();
	if (hm == null)
	    return s;

	if (s.length() == 0)
	    return s;

	String ss = (String) hm.get(s);
	if (ss == null) {
	    if (hm_new == null) {
		hm_new = new HashMap();
		fillFromXML("T_new", null, hm_new);
	    }
	    ss = (String) hm_new.get(s);
	    if (ss == null) {
		ss = s;
		hm.put(s, ss);
		hm_new.put(s, ss);
		if (mythread == null) {
		    mythread = new MyThread();
		    mythread.start();
		}
// 		PrintWriter pw = S.createPrintWriter("T_new", true);
// 		pw.println("[" + s + "][" + s + "]");
// 		pw.flush();
// 		pw.close();
//log		omega.Context.sout_log.getLogger().info("ERR: " + "T.t " + s);
	    }
	} else {
	    //	    omega.Context.sout_log.getLogger().info("ERR: " + "Tt " + s + ' ' + ss);
	}
	return ss;
    }
}
