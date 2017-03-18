/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package omega.appl;

/**
 * @author lars
 */
public class Starter extends OmegaAppl {

    public Starter() {
        super("Starter");
    }

    public static void main(String[] argv) {
        Starter s = new Starter();
        int what = 2;

        switch (what) {
            case 1:
                omega.appl.lesson.Runtime.main(argv);
                break;
            case 2:
                omega.appl.lesson.Editor.main(argv);
                break;
            case 3:
                omega.appl.animator.Editor.main(argv);
                break;
            case 4:
                omega.ShowLicense.main(argv);
                break;
        }
    }
}
