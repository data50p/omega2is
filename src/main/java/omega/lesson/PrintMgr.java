package omega.lesson;

import fpdo.sundry.S;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PrintMgr implements Printable {
    static HashMap flags;
    static List argl;

    int selected_printer_nid = 0;

    class PrintableDoc implements Doc {
	private Printable printable;

	public PrintableDoc(Printable printable) {
	    this.printable = printable;
	}

	public DocFlavor getDocFlavor() {
	    return DocFlavor.SERVICE_FORMATTED.PRINTABLE;
	}

	public DocAttributeSet getAttributes() {
	    return null;
	}

	public Object getPrintData() throws IOException {
	    return printable;
	}

	public Reader getReaderForText() throws IOException {
	    return null;
	}

	public InputStream getStreamForBytes() throws IOException {
	    return null;
	}
    }

    RenderedImage image2;

//     void test2() {
//  	Tiff t = new Tiff();
// 	for(int i = 0; i < 5; i++) {
// 	    int page = i;
// 	    image2 = t.getIm((String)argl.get(0), page);
// 	    if ( image2 != null ) {
// 		int width = image2.getWidth();
// 		int height = image2.getHeight();

// 		omega.Context.sout_log.getLogger().info("ERR: " + "wh = " + width + ' ' + height);

// 		PrinterJob pj = PrinterJob.getPrinterJob();
// 		pj.setPrintable(this);
// 		if ( pj.printDialog()) {
// 		    try {
// 			pj.print();
// 		    } catch (PrinterException ex) {
// 			omega.Context.sout_log.getLogger().info("ERR: " + "" + ex);
// 		    }
// 		}
// 	    }
// 	}
//     }

    Font item_fo;

    Font getItemFont() {
	if (item_fo == null)
	    item_fo = new Font("Arial", Font.PLAIN, 50);
	return item_fo;
    }

    void setItemFont(Font fo) {
	item_fo = fo;
    }

    public PrintService getPrintService(int nid) {
	DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
	PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	aset.add(OrientationRequested.PORTRAIT);
//		    aset.add(MediaSizeName.ISO_A4);
//		aset.add(new MediaPrintableArea(50, 50, 100, 200, MediaPrintableArea.MM));
	aset.add(new JobName("Omega sentences", null));

	flavor = null;
	if (nid == -1) {
	    return PrintServiceLookup.lookupDefaultPrintService();
	}

	PrintService[] service = PrintServiceLookup.lookupPrintServices(flavor, aset);
	if (service == null || service.length == 0 || nid >= service.length)
	    return PrintServiceLookup.lookupDefaultPrintService();
	return service[nid];
    }

    ArrayList sentences;
    String lesson_name;

    void print(PrintService print_service,
	       String title,
	       ArrayList sentences,
	       String lesson_name) throws Exception {
	this.sentences = sentences;
	this.lesson_name = lesson_name;

	try {
	    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	    aset.add(OrientationRequested.PORTRAIT);
	    aset.add(new JobName("Omega sentences", null));
	    DocPrintJob pj = print_service.createPrintJob();

	    try {
		Doc doc = new PrintableDoc(this);
		pj.print(doc, aset);
	    } catch (PrintException ex) {
		throw new Exception("warning" + ex);
	    }
	} finally {
	    System.gc();
	}
    }

    String getPrinterList(String delim) {
	StringBuffer sb = new StringBuffer();

	DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;

	PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

	aset.add(OrientationRequested.PORTRAIT);
//	aset.add(MediaSizeName.ISO_A4);
//		aset.add(new MediaPrintableArea(50, 50, 100, 200, MediaPrintableArea.MM));
	aset.add(new JobName("Omega sentences", null));

//	PrintService[] service = new PrintService[] {PrintServiceLookup.lookupDefaultPrintService()};
	PrintService[] service = PrintServiceLookup.lookupPrintServices(null /*flavor*/, aset);

	for (int i = 0; i < service.length; i++) {
	    PrintService ps = service[i];
	    if (i > 0)
		sb.append(delim);
	    sb.append(i + ":  \"" + ps + "\"");
	}

	return sb.toString();
    }

    void listPrinters() {
	String s = getPrinterList("\nPrintServer: ");
	omega.Context.sout_log.getLogger().info("PrintServer: " + s);
    }

    int getStringWidth(Graphics2D g2, Font fo, String s) {
	RenderingHints rh = g2.getRenderingHints();
	rh.put(rh.KEY_ANTIALIASING, rh.VALUE_ANTIALIAS_ON);
	g2.setRenderingHints(rh);

	FontRenderContext frc = g2.getFontRenderContext();
	Rectangle2D r = fo.getStringBounds(s, frc);
	return (int) r.getWidth();
    }

    int getStringHeight(Graphics2D g2, Font fo, String s) {
	RenderingHints rh = g2.getRenderingHints();
	rh.put(rh.KEY_ANTIALIASING, rh.VALUE_ANTIALIAS_ON);
	g2.setRenderingHints(rh);

	FontRenderContext frc = g2.getFontRenderContext();
	Rectangle2D r = fo.getStringBounds(s, frc);
	return (int) r.getHeight();
    }

    int gap = 4;

    int[] getBounding(Graphics2D g2, ArrayList li) {
	int WW = 0;
	int HH = 0;
	if (li == null)
	    return new int[]{500, 350};

	Iterator it = li.iterator();
	while (it.hasNext()) {
	    String sent = (String) it.next();
	    int sh = getStringHeight(g2, getItemFont(), sent);
	    int sw = getStringWidth(g2, getItemFont(), sent);
	    HH += sh + gap;
	    WW = sw > WW ? sw : WW;
	}
	omega.Context.sout_log.getLogger().info("ERR: " + "bounding is " + WW + ' ' + HH);
	return new int[]{WW, HH};
    }

    public int print(Graphics g, PageFormat pf, int pageIndex) {
	ArrayList sentences = this.sentences;

	try {
	    double pfW = pf.getImageableWidth();
	    double pfH = pf.getImageableHeight();
	    double pfX = pf.getImageableX();
	    double pfY = pf.getImageableY();

// 	    double scaleX = pfW / imW;
// 	    double scaleY = pfH / imH;

	    omega.Context.sout_log.getLogger().info("ERR: " + "print..." + pageIndex);
	    omega.Context.sout_log.getLogger().info("ERR: " + "size " + /* imW + ' ' + imH + ' ' + */ pfW + ' ' + pfH);
	    omega.Context.sout_log.getLogger().info("ERR: " + "po " + pfX + ' ' + pfY);
//	    omega.Context.sout_log.getLogger().info("ERR: " + "scale " + scaleX + ' ' + scaleY);

	    if (pageIndex == 0) {
		Graphics2D g2 = (Graphics2D) g;
		//AffineTransform at = new AffineTransform();
		AffineTransform at = g2.getTransform();
		omega.Context.sout_log.getLogger().info("ERR: " + "at " + at);
		at.translate(pfX, pfY);
		g2.setTransform(at);

// 		g2.drawRect(0, 0, 10, 10);
// 		g2.drawRect(10, 10, 90, 90);
// 		g2.drawRect((int)(pfW-10-1), (int)(pfH-10-1), 10, 10);

		setItemFont(new Font("Arial", Font.PLAIN, 20));
		int[] bounding = getBounding(g2, sentences);

		while (bounding[0] > pfW || bounding[1] > pfH) {
		    int size = getItemFont().getSize();
		    setItemFont(new Font("Arial", Font.PLAIN, (int) (size * 0.9)));
		    bounding = getBounding(g2, sentences);
		    omega.Context.sout_log.getLogger().info("ERR: " + "font size is " + getItemFont().getSize());

		}

		g2.setFont(getItemFont());

		int x = 0;
		int y = 0;
		Iterator it = sentences.iterator();
		int cnt = 0;
		int sh = getStringHeight(g2, getItemFont(), "Aj");
		y += sh + 5;

		g2.drawString(lesson_name, x, y);

		y += sh + sh + gap * 2;
		while (it.hasNext()) {
		    String sent = (String) it.next();
		    g2.drawString(sent, x, y);
		    y += sh + gap;
		}

		return Printable.PAGE_EXISTS;
	    } else {
		return Printable.NO_SUCH_PAGE;
	    }
	} finally {
	    System.gc();
	}
    }

    static public PrintService selectPrinter() {
	try {
	    DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
	    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	    aset.add(OrientationRequested.PORTRAIT);
	    aset.add(new JobName("Omega sentences", null));

	    PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, aset);

	    PrintService ps = ServiceUI.printDialog(null,
		    50,
		    50,
		    services,
		    null,
		    flavor,
		    aset);
	    return ps;
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "PM: selectPrinter: " + ex);
	    ex.printStackTrace();
	}
	return null;
    }

    static public void list(boolean gui) {
	PrintMgr pm = new PrintMgr();
	if (gui) {
	    String s = pm.getPrinterList("</li><li>");
	    String text = "<html><h2 color=\"#600000\">" +
		    "Print Spooler - Printer list" +
		    "</h2>" +
		    "Make sure that the printer name in<br>" +
		    "<em>Remote.settings.tundra</em> is correct.<p>" +
		    "Use full name as in the list or index number<p>" +
		    "<p>Found printers are:<ul><li>" +
		    s +
		    "</li></ul></html>";
	    omega.Context.sout_log.getLogger().info("ERR: " + "---\n" + text + "\n---");
	    JOptionPane.showMessageDialog(null,
		    text,
		    "Print Spooler",
		    JOptionPane.ERROR_MESSAGE);
	} else
	    pm.listPrinters();
    }

    static public void main(String[] args) {
	flags = S.flagAsMap(args);
	argl = S.argAsList(args);

	PrintMgr pm = new PrintMgr();
	pm.listPrinters();
    }

}
