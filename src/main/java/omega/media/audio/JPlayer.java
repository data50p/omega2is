package omega.media.audio;

//åäö

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import javazoom.jl.player.advanced.jlap;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class JPlayer implements LineListener {
    String fn;
    AudioInputStream ais;
    AudioFormat aformat;
    SourceDataLine sdataline;
    Object lock = new Object();

    boolean started = false;
    boolean eom = false;
    boolean opened = false;

    boolean done = false;

    static int wah = 2;
    static boolean silent = !true;
    static boolean o = false;
    static int N = 4096 * 16;

    boolean use_mp3 = false;

    String realy_name;

    private static void s_pe(String s) {
	if (o)
	    System.err.println(s);
    }

    private static void s_pe_(String s) {
	if (o)
	    System.err.print(s);
    }

    static {
	String bs = omega.appl.Settings.getSettings().getString("audio-bufsize");
	if (bs != null) {
	    int bs_i = 1024 * Integer.parseInt(bs);
	    N = bs_i;
	}

	bs = omega.appl.Settings.getSettings().getString("audio-write-ahead");
	if (bs != null) {
	    wah = Integer.parseInt(bs);
	}

	if (omega.appl.Settings.getSettings().getBoolean("audio-silent"))
	    silent = true;
	else
	    silent = false;

	if (omega.appl.Settings.getSettings().getBoolean("audio-debug"))
	    o = true;
	else
	    o = false;

	omega.Context.sout_log.getLogger().info("ERR: " + "" + omega.appl.Settings.getSettings().getSettingsHashMap());
    }

    JPlayer(String fn) {
	omega.Context.sout_log.getLogger().info("ERR: " + "JPlayer: fn = " + fn);

	this.fn = fn;
	realy_name = fn;
	if (fn.endsWith(".mp3")) {
	    initMp3(fn);
	    return;
	}

	if (true) {
	    String fn3 = fn.replaceAll("\\.wav", ".mp3");
	    File file3 = new File(fn3);
	    File file2 = new File(fn);
	    if (file3.exists() && !file2.exists()) {
		omega.Context.sout_log.getLogger().info("ERR: " + "JPlayer: fn -> " + fn3);
		initMp3(fn3);
		realy_name = fn3;
		return;
	    }
	}

	try {
	    File file = new File(fn);
	    ais = AudioSystem.getAudioInputStream(file);
	    aformat = ais.getFormat();
	    sdataline = getSourceDataLine(aformat);
	    sdataline.addLineListener(this);

	    //omega.Context.sout_log.getLogger().info("ERR: " + "JPlayer: " + ais + ' ' + aformat + ' ' + sdataline);
	} catch (Exception ex) {
	    ais = null;
	    aformat = null;
	    sdataline = null;
	    done = true;
	    omega.Context.sout_log.getLogger().info("ERR: " + "JPlayer: " + ex);
	}
    }

    private void initMp3(String fn) {
	use_mp3 = true;
    }


    public static SourceDataLine getSourceDataLine(AudioFormat format) throws LineUnavailableException {
	DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

	return (SourceDataLine) AudioSystem.getLine(info);
    }

    Object w_w = new Object();

    void waitAudio() {
	synchronized (lock) {
	    try {
		while (!done)
		    lock.wait();
	    } catch (InterruptedException ex) {
	    }
	}
    }

    static final byte[] silent_buf = new byte[4096 * 4];

    void play() {
	if (use_mp3) {
	    //	if ( jlp_player != null ) {
	    Thread th = new Thread(new Runnable() {
		public void run() {
		    try {
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
			InfoListener lst = new InfoListener();
			String filename = realy_name;
			jlap.playMp3(new File(filename), lst);
			//omega.Context.sout_log.getLogger().info("ERR: " + "mp3 play running " + filename);
		    } catch (javazoom.jl.decoder.JavaLayerException ex) {
			ex.printStackTrace(System.err);
			done = true;
		    } catch (IOException ex) {
			ex.printStackTrace(System.err);
			done = true;
		    }
		}
	    });
	    th.start();
	    return;
	}

	try {
	    Thread th = new Thread(new Runnable() {
		public void run() {
		    try {
			Thread.currentThread().setPriority(Thread.NORM_PRIORITY + 3);

			sdataline.open(aformat);
			waitOpen();
			sdataline.start();

			byte[] ba = new byte[N];
			for (int i = 0; ; i++) {
			    int n = ais.read(ba);
			    if (n < 0)
				break;
			    sdataline.write(ba, 0, n);
			    if (i < wah)
				s_pe_("{w}");
			    else
				s_pe_("{W}");

			    if (wah > 0 && i == wah) {
				s_pe("-w-");
				waitStart();
				s_pe("-W-");
			    }
			}
			s_pe(">d>");
			sdataline.drain();
			done = true;
			synchronized (lock) {
			    lock.notifyAll();
			}
			if (silent) {
			    sdataline.write(silent_buf, 0, silent_buf.length);
			    s_pe(">D>");
			    sdataline.drain();
			}

			ais.close();
			ais = null;

			s_pe(">S>");
			sdataline.stop();
			s_pe(">C>");
			sdataline.close();
		    } catch (Exception ex) {
			omega.Context.sout_log.getLogger().info("ERR: " + "JPlayer2: " + ex);
			ex.printStackTrace();
		    } finally {
			//			    sdataline.removeLineListener(JPlayer.this);
			sdataline = null;
			synchronized (lock) {
			    lock.notifyAll();
			}
		    }
		}
	    });
	    th.start();
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "JPlayer3: " + ex);
	}
    }


    private void waitOpen() {
	synchronized (this) {
	    while (!opened) {
		try {
		    wait(5000);
		    return;
		} catch (InterruptedException ie) {
		}
	    }
	}
    }

    private void waitStart() {
	synchronized (this) {
	    if (!started) {
		while (!started) {
		    try {
			wait(5000);
		    } catch (InterruptedException ie) {
		    }
		}
	    }
	}
    }

    private void waitEOM() {
	synchronized (this) {
	    while (!eom) {
		try {
		    wait(5000);
		    return;
		} catch (InterruptedException ie) {
		}
	    }
	}
    }

    public synchronized void update(LineEvent le) {
	LineEvent.Type t = le.getType();

	if (t == LineEvent.Type.OPEN) {
	    opened = true;
	    s_pe("+O+");
	} else if (t == LineEvent.Type.START) {
	    started = true;
	    s_pe("+s+");
	} else if (t == LineEvent.Type.CLOSE) {
	    eom = true;
	    s_pe("+C+");
	} else if (t == LineEvent.Type.STOP) {
	    s_pe("+S+");
	}

	notifyAll();
    }

    public class InfoListener extends PlaybackListener {
	public void playbackStarted(PlaybackEvent evt) {
	    omega.Context.sout_log.getLogger().info("Play started from frame " + evt.getFrame());
	}

	public void playbackFinished(PlaybackEvent evt) {
	    omega.Context.sout_log.getLogger().info("Play completed at frame " + evt.getFrame());
	    done = true;
	    synchronized (lock) {
		lock.notifyAll();
	    }
	}
    }
}

