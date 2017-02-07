package omega.media.video;

import java.io.File;

/**
 * Created by lars on 2017-02-07.
 */
public class VideoUtil {

    /**
     * Try with supported movie types
     *
     * @param fname
     * @return
     */
    public static boolean exist(String fname) {
	if ( fname.endsWith(".mpg") ) {
	    String fBase = fname.replaceAll("\\.mpg$", "");
	    File f = new File(fBase + ".mp4");
	    if ( f.exists() && f.canRead() )
		return true;
	}
	return true;
    }

    public static String supportedFname(String fname) {
	if ( fname.endsWith(".mpg") ) {
	    String fBase = fname.replaceAll("\\.mpg$", "");
	    String supported = fBase + ".mp4";
	    File f = new File(supported);
	    if ( f.exists() && f.canRead() )
		return supported;
	}
	return fname;
    }
}
