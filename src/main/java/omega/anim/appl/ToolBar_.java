package omega.anim.appl;

import omega.swing.ToolBar;

import java.util.HashMap;

public abstract class ToolBar_ extends ToolBar {
    HashMap jb;

    ToolBar_(Object o) {
	super();
	jb = new HashMap();
	init(o);
    }

    ToolBar_(Object o, int orientation) {
	super(orientation);
	jb = new HashMap();
	init(o);
    }

    abstract protected void init(Object o);

    public void populate() {
	populate("default");
    }

    public void populate(String id) {
    }

    public void enable_path(int mask) {
    }
}
