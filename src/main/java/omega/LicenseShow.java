
package omega;

import fpdo.sundry.PreferenceUtil;

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

    public static boolean showAndAccepted() {
	PreferenceUtil pu = new PreferenceUtil(LicenseShow.class);
	String answer = (String) pu.getObject(licShow, no);

	if (Context.isDeveloper() || yes.equals(answer))
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
