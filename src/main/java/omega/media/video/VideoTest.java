package omega.media.video;

//import javax.media.*;

import javax.swing.*;

//  import	javax.swing.event.InternalFrameAdapter;
//  import	javax.swing.event.InternalFrameEvent;

public class VideoTest extends JFrame {
/*    static String args[];

    public static void main( String [] args ) {
	VideoTest.args = args;
	VideoTest	vt	= new VideoTest();
	vt.run();
    }

    static JDesktopPane	dest;

    public VideoTest() {
	setSize(100, 100);
	setVisible(true);

	addWindowListener( new WindowAdapter() {
	    public void windowClosing(WindowEvent we) {
		System.exit(0);
	    }
	} );

//	Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
    }

    void run() {
	URL		url	= null;
        if ( args.length == 0 )
            args = new String[] {"media/MOV01445.mpg"};

	omega.Context.sout_log.getLogger().info("arg 0: " + args[0]);

	try {
	    url = new URL ("file:"+args[0]);

	    try {
		Player player = Manager.createPlayer(url);
		if ( player != null ) {
		    getContentPane().setLayout(new BorderLayout());
		    MpgPlayer mp = new MpgPlayer( player, "null" );
		    setVisible(true);
		    getContentPane().add(mp.visual, BorderLayout.CENTER);
		    pack();
		    mp.start();
		}
	    } catch (NoPlayerException e) {
		omega.Context.sout_log.getLogger().info("NPE Error: " + e);
	    }

	} catch (MalformedURLException e) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "MUE Error:" + e);
	} catch (IOException e) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "IO Error:" + e);
	}

    }*/
}
