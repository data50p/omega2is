package fpdo.sundry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TupleSpace {
    List li;
    List leaseLi;

    public TupleSpace() {
	li = new ArrayList();
	leaseLi = new ArrayList();
    }

    private static TupleSpace def_TS;

    public synchronized static TupleSpace getDefaultTupleSpace() {
	if (def_TS == null)
	    def_TS = new TupleSpace();
	return def_TS;
    }

    private boolean match(Class c1, TupleEntry te2) {
	Class sc1 = c1.getSuperclass();
	Class c2 = te2.getClass();

	if (c2 == c1)
	    return true;
	if (sc1 != null && c2 == sc1)
	    return true;
	if (sc1 != null)
	    return match(sc1, te2);
	return false;
    }

    private boolean match(Object te1, TupleEntry te2) {
	Class c1 = te1.getClass();
	Class sc1 = te1.getClass().getSuperclass();
	Class c2 = te2.getClass();

	if (c2 == c1)
	    return true;
	if (sc1 != null && c2 == sc1)
	    return true;
	if (sc1 != null)
	    return match(sc1, te2);

	return false;
    }

    public void write(TupleEntry te) {
	li.add(te);
    }

    public TupleEntry read(TupleEntry tea) {
	for (Iterator it = li.iterator(); it.hasNext(); ) {
	    TupleEntry te = (TupleEntry) it.next();
	    if (match(te, tea))
		return te;
	}
	return null;
    }

    public TupleEntry take(TupleEntry tea) {
	for (ListIterator it = li.listIterator(); it.hasNext(); ) {
	    TupleEntry te = (TupleEntry) it.next();
	    if (match(te, tea)) {
		it.remove();
		return te;
	    }
	}
	return null;
    }

    public TupleEntry lease(TupleEntry tea) {
	for (Iterator it = li.iterator(); it.hasNext(); ) {
	    TupleEntry te = (TupleEntry) it.next();
	    if (te.getClass() == tea.getClass()) {
		it.remove();
		leaseLi.add(te);
		return te;
	    }
	}
	return null;
    }

    public void commit(TupleEntry tea) {
	for (Iterator it = leaseLi.iterator(); it.hasNext(); ) {
	    TupleEntry te = (TupleEntry) it.next();
	    if (te == tea) {
		it.remove();
		break;
	    }
	}
    }

    public void rollback(TupleEntry tea) {
	for (Iterator it = leaseLi.iterator(); it.hasNext(); ) {
	    TupleEntry te = (TupleEntry) it.next();
	    if (te == tea) {
		it.remove();
		li.add(te);
		break;
	    }
	}
    }

    public String toString() {
	return "li=" + li +
		",leaseLi" + leaseLi;
    }
}
