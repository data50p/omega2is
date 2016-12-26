package omega.appl;

import omega.swing.Omega2Is;

import java.util.HashMap;

public class OmegaAppl {
    protected String name;
    static HashMap prop = new HashMap();

    static public Splash splash = new Splash();

    public OmegaAppl(String name) {
	this.name = "Omega - " + name;
	prop.put("name", name);
	Omega2Is.initFx(); // for audio played by JavaFX
    }

    public static void closeSplash() {
	if (splash == null)
	    return;
	splash.setVisible(false);
	splash = null;
    }

    public static HashMap getPropHashMap() {
	return prop;
    }
}
