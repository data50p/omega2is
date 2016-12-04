package omega.anim.canvas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter {
    AnimCanvas anim_canvas;

    Key(AnimCanvas anim_canvas) {
	this.anim_canvas = anim_canvas;
	anim_canvas.addKeyListener(this);
    }

    public void keyTyped(KeyEvent k) {
	omega.Context.sout_log.getLogger().info("ERR: " + "key " + k);
    }
}

