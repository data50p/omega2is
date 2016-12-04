package omega.util;

import fpdo.sundry.S;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ListFilesURL {
    public static String[] getMediaList(String n) {
	String name = omega.Context.FILE_BASE + n;
	File dir = new File(name);
	String[] names = dir.list();
// 	omega.Context.sout_log.getLogger().info("ERR: " + "FILE dir[] name " + name + ' ' + names);
// 	omega.Context.sout_log.getLogger().info("ERR: " + "FILE dir[] names " + S.a2s(names));
	Set li = new HashSet();
	for (int i = 0; i < names.length; i++) {
	    li.add(names[i]);
	    if (names[i].endsWith(".mp3"))
		li.add(names[i].substring(0, names[i].length() - 4) + ".wav");
	}
	String[] sa = (String[]) li.toArray(new String[li.size()]);

	//	omega.Context.sout_log.getLogger().info("ERR: " + "FILE dir[] " + dir + ' ' + S.a2s(names));
//	return names;

	return sa;
    }


    static public void main(String[] args) {
	for (int i = 0; i < 10; i++) {
	    long ct0 = S.ct();
	    omega.Context.sout_log.getLogger().info("ERR: " + "" + S.a2s(getMediaList("actor")));
	    long ct1 = S.ct();
	    omega.Context.sout_log.getLogger().info("ERR: " + "" + (ct1 - ct0));
	    S.m_sleep(200);
	    System.gc();
	}
    }
}
