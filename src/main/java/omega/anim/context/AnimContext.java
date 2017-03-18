package omega.anim.context;

import fpdo.xml.Element;
import omega.Context;
import omega.anim.appl.AnimEditor;
import omega.anim.appl.AnimRuntime;
import omega.anim.canvas.AnimCanvas;
import omega.anim.tool.timeline.MasterTimeLine;
import omega.anim.tool.timeline.TimeLinePlayer;

import javax.swing.*;

//import omega.anim.config.*;

public class AnimContext extends Context {
    public AnimCanvas anim_canvas;
    public TimeLinePlayer tl_player;
    public MasterTimeLine mtl;
    static public AnimEditor ae;
    public AnimRuntime arun;
    public static JFrame top_frame;

    public double anim_speed = 1.0;

    public AnimContext(AnimEditor ae) {
        this.ae = ae;
        this.arun = null;
    }

    public AnimContext(AnimRuntime arun) {
        this.ae = null;
        this.arun = arun;
    }

    public void fillElement(Element el) {
        anim_canvas.fillElement(el);
        mtl.fillElement(el);
    }

//      public void save(XML_PW xmlpw) {
//  	anim_canvas.save(xmlpw);
//  	mtl.save(xmlpw);
//      }
}
