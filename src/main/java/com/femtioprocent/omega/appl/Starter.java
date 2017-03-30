/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femtioprocent.omega.appl;

import com.femtioprocent.omega.ShowLicense;
import com.femtioprocent.omega.appl.animator.Editor;
import com.femtioprocent.omega.appl.lesson.Runtime;

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
                Runtime.main(argv);
                break;
            case 2:
                com.femtioprocent.omega.appl.lesson.Editor.main(argv);
                break;
            case 3:
                Editor.main(argv);
                break;
            case 4:
                ShowLicense.main(argv);
                break;
        }
    }
}
