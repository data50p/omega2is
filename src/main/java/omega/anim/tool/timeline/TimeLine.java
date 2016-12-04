package omega.anim.tool.timeline;

import fpdo.xml.Element;

import java.io.Serializable;
import java.util.*;

public class TimeLine implements Serializable {
    int offset;
    int duration;
    List markers;
    public int nid;
    public TimeMarker last_added_tm = null;

    String lesson_id = "";

    public String getLessonId() {
	return lesson_id;
    }

    public void setLessonId(String s) {
	lesson_id = s;
    }

    public TimeLine(int nid) {
	this.nid = nid;
	markers = new LinkedList();
    }

    public TimeLine(Element el) {
	markers = new LinkedList();
	String id = el.findAttr("lesson_id");
	if (id != null)
	    lesson_id = id;
	nid = Integer.parseInt(el.findAttr("nid"));
	offset = Integer.parseInt(el.findAttr("offset"));
	duration = Integer.parseInt(el.findAttr("duration"));
	for (int i = 0; i < 100; i++) {
	    Element me = el.findElement("TimeMarker", i);
	    if (me == null)
		break;
	    String t = me.findAttr("type");
	    String w = me.findAttr("when");
	    String d = me.findAttr("duration");
	    int wl = Integer.parseInt(w);
	    int dl = 0;
	    if (d != null)
		dl = Integer.parseInt(d);
	    addMarker(t.charAt(0), wl, dl);
	    Element teel = me.findElement("T_Event", 0);
	    if (teel != null) {
		TimeMarker tm = last_added_tm;
		for (int ii = 0; ii < 1000; ii++) {
		    Element tee = teel.findElement("TriggerEvent", ii);
		    if (tee == null)
			break;
		    TriggerEvent te = TriggerEventFactory.createTriggerEvent(tee);
		    int slot = TriggerEventFactory.getSlot(te.getCmd());
		    tm.setTriggerEvent(te, slot);
		}
	    }

	    if (t.equals("}"))
		omega.anim.panels.timeline.TimeLinePanel.global_tick_stop = wl + offset; // FIX
	}
    }

    public TimeLine(int nid, int offset, int duration) {
	this(nid);
	this.offset = offset;
	this.duration = duration;
	addMarker('[', 0);
	addMarker(']', duration);
    }

    public TimeLine(int nid, TimeLine src) {
	markers = new LinkedList();
	this.nid = nid;
	offset = src.offset;
	duration = src.duration;
	Iterator it = src.markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    TimeMarker new_tm = addMarker(tm.type,
		    tm.when - offset,
		    tm.duration);
	    if (tm.t_event != null) {
//		omega.Context.sout_log.getLogger().info("ERR: " + "t_ev " + tm.t_event.length);
		for (int i = 0; i < tm.t_event.length; i++) {
		    TriggerEvent te = tm.t_event[i];
		    TriggerEvent nte = TriggerEventFactory.createTriggerEvent(te.getCmd());
		    if (nte != null) {
			String arg = te.getArgString();
			if (nte.hasSelections()) {
			    ((TriggerEventSelections) nte).setArgFromCmd(arg);
			} else
			    nte.setArg(arg);
			nte.setOn(te.is_on);
		    }
//		    omega.Context.sout_log.getLogger().info("ERR: " + "new " + nte);
		    new_tm.setTriggerEvent(nte, i);
		}
	    }
	}
    }

    public TimeMarker addMarker(char type, int when) {
	return addMarker(type, when, 0);
    }

    public TimeMarker addMarker(char type, int when, int duration) {
	TimeMarker tm = new TimeMarker(this, type, offset + when, duration);
	markers.add(tm);
	reNumerateMarker();
	last_added_tm = tm;
	return last_added_tm;
    }

    public TimeMarker addMarker(TimeLine tltl, int when) {
	TimeMarker tm = new TimeMarker(this, tltl, offset + when);
	markers.add(tm);
	reNumerateMarker();
	last_added_tm = tm;
	return last_added_tm;
    }

    public void removeMarker(TimeMarker tm) {
	markers.remove(tm);
    }

    public void reNumerateMarker() {
	TimeMarker[] ma = (TimeMarker[]) (markers.toArray(new TimeMarker[0]));
	Arrays.sort(ma, new Comparator() {
	    public int compare(Object o1, Object o2) {
		TimeMarker tm1 = (TimeMarker) o1;
		TimeMarker tm2 = (TimeMarker) o2;
		return (int) (tm1.when - tm2.when);
	    }
	});
	markers = new ArrayList();
	for (int i = 0; i < ma.length; i++)
	    ma[i].ord = i;

	int ty = TimeMarker.TSYNC;
	int cc = 0;
//   	for(int i = 0; i < ma.length; i++)
//   	    if ( ma[i].type == ty )
// 		ma[i].ord_same_type = cc++;

	ty = TimeMarker.TRIGGER;
	cc = 0;
//   	for(int i = 0; i < ma.length; i++)
//   	    if ( ma[i].type == ty )
// 		ma[i].ord_same_type = cc++;

	Collection col = Arrays.asList(ma);
	markers.addAll(col);
    }


    public double[] getTimeMarker_TSyncSegments() {
	TimeMarker[] ta = getAllTimeMarkerType(TimeMarker.TSYNC);
	double[] da = null;
	if (ta == null || ta.length == 0) {
	    da = new double[2];
	    da[0] = offset;
	    da[1] = duration + offset;
	} else {
	    da = new double[ta.length + 2];
	    for (int i = 0; i < da.length; i++) {
		if (i == 0) {
		    da[i] = offset;
		} else if (i == da.length - 1) {
		    da[i] = duration + offset;
		} else {
		    da[i] = ta[i - 1].when;
		}
	    }
	}
	return da;
    }

    public TimeMarker[] getAllTimeMarkerType(char t) {
	int c = 0;
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == t)
		c++;
	}

	TimeMarker[] ta = new TimeMarker[c];

	c = 0;
	it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == t)
		ta[c++] = tm;
	}
	Arrays.sort(ta, new Comparator() {
	    public int compare(Object o1, Object o2) {
		TimeMarker tm1 = (TimeMarker) o1;
		TimeMarker tm2 = (TimeMarker) o2;
		return (int) (tm1.when - tm2.when);
	    }
	});
	return ta;
    }


    public void adjustSomeTimeMarkerRelative(double d) {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.relativeAdjust()) {
		double dd = d * (tm.when - offset);
		tm.move((int) dd, 1);
	    }
	}
    }

    public int adjustMove(int d) {
	if (offset + d < 0)
	    return (int) -(offset - 1);
	return (int) d;
    }

    public void move(int d) {
	move(d, 1);
    }

    public void move(int d, int grid) {
	offset += d;
	offset = omega.util.Num.grid(offset, grid);
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.isMoveAble())
		tm.move(d, grid);
	}
    }

    public void moveSelectedTimeMarker(int d, int grid) {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.selected) {
		if (tm.type == TimeMarker.STOP)
		    size(d);
		else if (tm.type == TimeMarker.START)
		    move(d);
		else if (tm.isMoveAble())
		    tm.move(d, grid);
	    }
	}
    }

    public TimeMarker getSelectedTimeMarker() {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.selected) {
		return tm;
	    }
	}
	return null;
    }

    public void updateEndMarker(int when) {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == TimeMarker.END)
		tm.when = when;
	}
    }

    public int getEndMarker() {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == TimeMarker.END)
		return tm.when;
	}
	return 2000;
    }

    public void updateBeginMarker(int when) {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == TimeMarker.BEGIN)
		tm.when = when;
	}
    }

    public void size(int d) {
	duration += d;
	if (duration <= 0)
	    duration = 1;
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == TimeMarker.STOP)
		tm.when = offset + duration;
	}
    }

    public int getDuration() {
	return duration;
    }

    public int getOffset() {
	return offset;
    }

    public List getMarkersAbs(int from, int to) {
	return getMarkersAbs(from, to, false);
    }

    public List getMarkersAbs(int from, int to, boolean special) {
	List l = new ArrayList();
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.when > from && tm.when <= to)
		l.add(tm);
	}
	return l;
    }

    public List getMarkersType(char type) {
	List l = new ArrayList();
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == type)
		l.add(tm);
	}
	return l;
    }

    public TimeMarker getMarkerAtIndexType(int ix, char type) {
	Iterator it = markers.iterator();
	int c = 0;
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    if (tm.type == type)
		if (c == ix)
		    return tm;
		else
		    c++;
	}
	return null;
    }

    public TimeMarker getNearestTimeMarker(int dt) {
	int dt_f = 9999999;
	TimeMarker tm_f = null;

	List l = new ArrayList();
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    int tdist = (int) Math.abs(tm.when - dt);
	    if (tdist < dt_f) {
		dt_f = tdist;
		tm_f = tm;
	    }
	}
	return tm_f;
    }

    public void setDeselectTimeMarker() {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    tm.setSelected(false);
	}
    }

    public boolean activeNow(int now) {
	return now > offset && now < offset + duration;
    }

    public String toString() {
	return "TimeLine{" +
		"nid=" + nid +
		", markers.size()=" + markers.size() +
		"}";
    }

    public String toStringDeep() {
	return "TimeLine{" +
		"" + markers +
		"}";
    }

    Element getElement() {
	Element el = new Element("TimeLine");
	el.addAttr("lesson_id", "" + getLessonId());
	el.addAttr("offset", "" + offset);
	el.addAttr("duration", "" + duration);
	el.addAttr("nid", "" + nid);
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    el.add(tm.getElement());
	}
	return el;
    }

    public void fetchPlaySound(List li) {
	Iterator it = markers.iterator();
	while (it.hasNext()) {
	    TimeMarker tm = (TimeMarker) it.next();
	    tm.fetchPlaySound(li);
	}
    }
}
