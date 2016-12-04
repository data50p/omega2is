package omega.util;

import fpdo.sundry.S;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Log {
    private static class MyFormatter extends Formatter {
	static DateFormat dformat = new SimpleDateFormat("dd/MM HH:mm:ss.SSS");
	long last = S.ct();

	public String format(LogRecord record) {
	    String s = record.getSourceClassName();
	    s = s.substring(s.lastIndexOf('.') + 1);
	    long ms = record.getMillis();
	    Date dt = new Date(ms);
	    String d = dformat.format(dt);
	    int lt = (int) (ms - last);
	    last = ms;
	    return "" +
		    S.padRight("" + record.getLevel(), 10, ' ') +
		    d + ' ' +
		    S.padLeft("" + lt, 5, ' ') + ' ' +
		    S.padRight("" + s, 23, ' ') + ' ' +
		    S.padRight("" + record.getSourceMethodName(), 18, ' ') + ' ' +
		    record.getMessage() + '\n';
	}
    }

    private MyFormatter my_formatter = new MyFormatter();

    public java.util.logging.Logger logger;

    public Log(String name) {
	this(name, omega.Context.logon);
    }

    public Log(String name, boolean on) {
	try {
	    logger = java.util.logging.Logger.getLogger(name);
	    //logger.getParent().setLevel(Level.OFF);
	    logger.setLevel(on ? Level.ALL : Level.OFF);
	    File d = new File("logs");
	    d.mkdir();
	    FileHandler fh = new FileHandler("logs/" + name);
	    fh.setFormatter(my_formatter);
	    logger.addHandler(fh);
	    logger.setUseParentHandlers(false);
	} catch (IOException ex) {
	} catch (NoClassDefFoundError ex) {
	    logger = null;
	}
    }

    public void setOn(boolean b) {
	logger.setLevel(b ? Level.INFO : Level.OFF);
	logger.getParent().setLevel(b ? Level.INFO : Level.OFF);
    }

    public java.util.logging.Logger getLogger() {
	return logger;
    }
}

