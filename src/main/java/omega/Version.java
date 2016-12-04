package omega;

// DO NO CHANGE HERE

public class Version {
    static public String getVersion() {
	return getVersion(null);
    }

    static public String getVersion(String s) {
	String ver = "\u03a9, ver liu.1.3.2.509 2016-11-30 21:32:28";
	if (s != null)
	    return s + ' ' + ver;
	else
	    return ver;
    }
}

// DO NO CHANGE HERE
