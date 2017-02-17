package omega;

// DO NO CHANGE HERE

public class Version {
    static public String getOmegaVersion() {
	return getOmegaVersion(null);
    }

    static public String getOmegaVersion(String s) {
	if (s != null)
	    return s + ' ' + "Ω, ver " + getDetailedVersion();
	else
	    return "Ω, ver " + getDetailedVersion();
    }

    static public String getDetailedVersion() {
	String ver = getVersion() + " 2017-02-18 00:03:42";
	return ver;
    }

    static public String getVersion() {
	String ver = "2.0.0";
	return ver;
    }

    public static String getCWD() {
        return System.getProperty("user.dir");
    }

    public static String getJavaHome() {
	return System.getProperty("java.home");
    }

    public static String getJavaVendor() {
	return System.getProperty("java.vendor");
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }
}

// DO NO CHANGE HERE
