
package omega;

import fpdo.sundry.PreferenceUtil;
import fpdo.sundry.S;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author lars
 */
public class LicenseShow {
    private static String licShow = "licShow";
    private static String yes = "yes";
    private static String no = "no";


    LicenseShow() {
    }

    boolean showAndAccept() {

	LicenseFrame lf = new LicenseFrame();
	lf.setVisible(true);
	for (; ; ) {
	    if (lf.isVisible())
		S.m_sleep(500);
	    else
		break;
	}
	return lf.result;
    }

    public static boolean showAndAccepted() {
	PreferenceUtil pu = new PreferenceUtil(LicenseShow.class);
	String answer = (String) pu.getObject(licShow, no);

	if (yes.equals(answer))
	    return true;
	ShowLicense dialog = new ShowLicense();
	dialog.pack();
	dialog.setVisible(true);

	while (dialog.accepted == null)
	    try {
		Thread.sleep(200);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	if ( ! dialog.accepted )
	    System.exit(1);

	pu.save(licShow, yes);
	return true;
    }
}
