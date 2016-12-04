package omega.appl;

import java.util.HashMap;

public class OmegaAppl {
    protected String name;
    static HashMap prop = new HashMap();

    static public Splash splash = new Splash();

    public OmegaAppl(String name) {
	this.name = "Omega - " + name;
	prop.put("name", name);
    }

    public static void closeSplash() {
	if (splash == null)
	    return;
	splash.hide();
	splash = null;
    }

    public static HashMap getPropHashMap() {
	return prop;
    }
}
