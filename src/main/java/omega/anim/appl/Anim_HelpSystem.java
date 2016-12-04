package omega.anim.appl;

import omega.help.HelpSystem;
import omega.swing.HtmlFrame;

public class Anim_HelpSystem extends HelpSystem {
    HtmlFrame html_fr;

    void showManualL() {
	show(mkFileName("lesson_manual"), 800, 600);
    }

    public void showManualAE() {
	show(mkFileName("editor_manual"), 800, 600);
    }
}
