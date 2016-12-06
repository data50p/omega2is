package fpdo.xml;


import fpdo.sundry.S;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SAX_node extends DefaultHandler {
    static class MyErrorHandler extends DefaultHandler {
	// treat validation errors as fatal
	public void error(SAXParseException e)
		throws SAXParseException {
	    throw e;
	}

	// dump warnings too
	public void warning(SAXParseException err)
		throws SAXParseException {
	    omega.Context.sout_log.getLogger().info("** Warning"
		    + ", line " + err.getLineNumber()
		    + ", uri " + err.getSystemId());
	    omega.Context.sout_log.getLogger().info("   " + err.getMessage());
	}
    }


    public void setDocumentLocator(Locator l) {
	// we'd record this if we needed to resolve relative URIs
	// in content or attributes, or wanted to give diagnostics.
    }

    public void startDocument() throws SAXException {
    }

    public void endDocument()
	    throws SAXException {
    }

    static Element el;
    Stack stack = new Stack();

    public void startElement(String namespaceURI,
			     String localName,
			     String qName,
			     Attributes attrs)
	    throws SAXException {

	Element el1 = new Element(qName);

	if (attrs != null) {
	    for (int i = 0; i < attrs.getLength(); i++) {
		String n = attrs.getQName(i);
		String v = attrs.getValue(i);
		el1.addAttr(n, v);
	    }
	}
	stack.push(el1);
    }

    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	Element e = (Element) stack.pop();
	if (!stack.isEmpty()) {
	    Element e1 = (Element) stack.peek();
	    e1.add(e);
	} else {
	    el = e;
	}
    }

    public void characters(char buf[], int offset, int len)
	    throws SAXException {
	Element e = (Element) stack.peek();
	if (buf[offset + len - 1] == '\n')
	    len--;
	e.add(new PCDATA(new String(buf, offset, len)));
    }

    public void ignorableWhitespace(char buf[], int offset, int len)
	    throws SAXException {
//	omega.Context.sout_log.getLogger().info("ERR: " + "iW " + new String(buf, offset, len));
    }

    public void processingInstruction(String target, String data)
	    throws SAXException {
	omega.Context.sout_log.getLogger().info("ERR: " + "pI " + target);
    }

    public void notationDecl(String name, String publicId, String systemId) {
	omega.Context.sout_log.getLogger().info("ERR: " + "nD " + name);
    }

    public void unparsedEntityDecl(String name, String publicId,
				   String systemId, String notationName) {
	omega.Context.sout_log.getLogger().info("ERR: " + "UeD " + name);
    }

    /**
     * Called for every scanned element
     */
    private static Element element(String file, SAX_node sn) throws IOException {
	return element(file, sn, true);
    }

    private static Element element(String file, SAX_node sn, boolean validating) throws IOException {
	InputSource input;

	try {
	    String uri = "file:" + new File(file).getAbsolutePath();

	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    if (validating)
		spf.setValidating(true);

	    spf.setNamespaceAware(!true);

	    SAXParser sp = spf.newSAXParser();
	    XMLReader xmlr = sp.getXMLReader();
//	    Parser parser = sp.getParser();
	    xmlr.setContentHandler(sn);
	    xmlr.setErrorHandler(new MyErrorHandler());
	    xmlr.parse(uri);

//	    SAXParserFactory spf = SAXParserFactory.newInstance();
//	    if (validating)
//		spf.setValidating(true);

//	    SAXParser sp = spf.newSAXParser();
//	    Parser parser = sp.getParser();
//	    parser.setDocumentHandler(sn);
//	    parser.setErrorHandler(new MyErrorHandler());
//	    parser.parse(uri);

	    return el;

	} catch (SAXParseException err) {
	    omega.Context.sout_log.getLogger().info("** Parsing error"
		    + ", line " + err.getLineNumber()
		    + ", uri " + err.getSystemId());
	    omega.Context.sout_log.getLogger().info("   " + err.getMessage());

	} catch (SAXException e) {
	    Exception x = e;
	    if (e.getException() != null)
		x = e.getException();
	    x.printStackTrace();

	} catch (Throwable t) {
//            t.printStackTrace ();
	}
	return null;
    }

    public static Element parse(String file, boolean validating) {
	try {
	    SAX_node sn = new SAX_node();
	    Element el = element(file, sn, validating);
	    return el;
	} catch (IOException ex) {
	}
	return null;
    }

    public static void main(String argv[]) throws IOException {
	HashMap flag = S.flagAsMap(argv);
	List argl = S.argAsList(argv);

	omega.Context.sout_log.getLogger().info("ERR: " + "argl " + argl + ' ' + flag);

	String file = (String) argl.get(0);

	boolean validating = true;
	if (flag.get("n") != null)
	    validating = false;

	Element el = SAX_node.parse(file, validating);

	List li = el.find((String) argl.get(1));

	omega.Context.sout_log.getLogger().info("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
	omega.Context.sout_log.getLogger().info("<!DOCTYPE start SYSTEM \"x.dtd\">");

	Iterator it = li.iterator();
	while (it.hasNext()) {
	    Element ell = (Element) it.next();
	    StringBuffer sbu = new StringBuffer();
	    StringBuffer sbl = new StringBuffer();

	    ell.render(sbu, sbl);

	    System.out.println(sbu.toString());
	    System.out.println(sbl.toString());
	}
    }
}
