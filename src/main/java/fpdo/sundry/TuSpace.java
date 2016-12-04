package fpdo.sundry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface TuSpaceListener {
    void tuSpaceWritten(TuSpace ts);
}

class TuElem {
    private List oa;
    final static Object ANY = new Object();
    final static Object MANY = new Object(); // only last in list

    TuElem() {
	oa = new ArrayList();
    }

    TuElem(Object o) {
	this();
	oa.add(o);
    }

    TuElem(Object o, Object o1) {
	this();
	oa.add(o);
	oa.add(o1);
    }

    TuElem(Object o, Object o1, Object o2) {
	this();
	oa.add(o);
	oa.add(o1);
	oa.add(o2);
    }

    TuElem(Object o, Object o1, Object o2, Object o3) {
	this();
	oa.add(o);
	oa.add(o1);
	oa.add(o2);
	oa.add(o3);
    }

    TuElem(Object o, Object o1, Object o2, Object o3, Object o4) {
	this();
	oa.add(o);
	oa.add(o1);
	oa.add(o2);
	oa.add(o3);
	oa.add(o4);
    }

    synchronized void add(Object o) {
	oa.add(o);
    }

    synchronized boolean match(final TuElem ti) {
	boolean many = false;
	for (int i = 0, i2 = 0; i < oa.size(); i++, i2++) {
	    Object o1 = (Object) oa.get(i);
	    Object o2 = (Object) ti.oa.get(i);
	    if (o2 == MANY)
		break;
	    if (o2 instanceof java.lang.Class) {
		if (!o1.getClass().equals(o2))
		    return false;
	    } else if (o2 != ANY && !o1.equals(o2))
		return false;
	}
	return true;
    }

    synchronized public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("TuElem{");
	for (int i = 0; i < oa.size(); i++) {
	    Object o = oa.get(i);
	    String ss;
	    if (o == ANY)
		ss = "{ANY}";
	    else if (o == MANY)
		ss = "{MANY}";
	    else
		ss = "" + o;

	    sb.append((i == 0 ? "" : ",") + ss);
	}
	sb.append("}");
	return sb.toString();
    }
}

class TuSpace {
    final static int LISTENER = 0;
    final static int ELEMENT = 1;

    private List l;
    private List llis;

    TuSpace() {
	l = new ArrayList();
	llis = new ArrayList();
    }

    TuSpace(List l) {
	this.l = l;
	llis = new ArrayList();
    }

    synchronized void addTuSpaceListener(TuSpaceListener tsl, TuElem ti) {
	Object[] oa = new Object[2];
	oa[LISTENER] = tsl;
	oa[ELEMENT] = ti;
	llis.add(oa);
    }

    synchronized void removeTuSpaceListener(TuSpaceListener tsl, TuElem ti) {
	List nllis = new ArrayList();
	Iterator it = llis.iterator();
	while (it.hasNext()) {
	    Object[] oa = (Object[]) it.next();
	    if (oa[LISTENER] != tsl ||
		    oa[ELEMENT] != ti)
		nllis.add(oa);
	}
	llis = nllis;
    }

    private void notifyListener(TuElem ti) {
	Iterator it = llis.iterator();
	while (it.hasNext()) {
	    Object[] oa = (Object[]) it.next();
	    if (((TuElem) (oa[ELEMENT])).match(ti))
		((TuSpaceListener) oa[LISTENER]).tuSpaceWritten(this);
	}
    }

    private List matched(TuElem id) {
	List nli = new ArrayList();
	Iterator it = l.iterator();
	while (it.hasNext()) {
	    TuElem ti = (TuElem) it.next();
	    if (ti.match(id))
		nli.add(ti);
	}
	return nli;
    }

    private List notmatched(TuElem id) {
	List nli = new ArrayList();
	Iterator it = l.iterator();
	while (it.hasNext()) {
	    TuElem ti = (TuElem) it.next();
	    if (!ti.match(id))
		nli.add(ti);
	}
	return nli;
    }

    synchronized void write(TuElem id) {
	l.add(id);
	notifyListener(id);
    }

//      synchronized void write(TuSpace ts) {
//  	ts.l.forall(fun (TuElem ti) -> void {
//  	    write(ti);
//  	});
//      }

    synchronized List read(TuElem id) {
	List ml = matched(id);
	return ml;
    }

    synchronized List take(TuElem id) {
	List ml = matched(id);
	List nml = notmatched(id);
	l = nml;
	return ml;
    }

    int length() {
	return l.size();
    }

    boolean isEmpty() {
	return length() == 0;
    }

    synchronized public String toString() {
	String s = "";
	s += "TuSpace{l=";
	s += l;
	return s + "}";
    }

    private static TuSpace def_TS;

    synchronized static TuSpace getDefaultTuSpace() {
	if (def_TS == null)
	    def_TS = new TuSpace();
	return def_TS;
    }
}

class TuSpaceAdapter implements TuSpaceListener {
    public void tuSpaceWritten(TuSpace ts) {
	omega.Context.sout_log.getLogger().info("TUSPACE WRITTEN " + ts);
    }
}
