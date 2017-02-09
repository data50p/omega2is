package omega.lesson.canvas;


import omega.i18n.T;
import omega.lesson.machine.Target;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class OmAssProp_TableModel extends AbstractTableModel {
    OmegaAssetsProperty sprop;

    List<Target.SentenceResult> li;
    List<String> li_set;

    int[][] test_member_map;

    final int TEST_MEM_OFFS = SentenceProperty.COL_TEST;

    String[] hdn = new String[]{T.t("What"),
	    T.t("Dependent File"),
	    T.t("Foobar"),
	    T.t("Alt 1"),
	    T.t("Alt 2"),
    };

    OmAssProp_TableModel(OmegaAssetsProperty sprop, List<Target.SentenceResult> li, int[][] tmm) {
	this.sprop = sprop;
	this.li = li;
	li_set = new ArrayList<String>();
	li_set.addAll(li.get(0).set);
	test_member_map = tmm;
    }

    public int getColumnCount() {
	return 5;
    }

    public int getRowCount() {
	return li.size();
    }

    public Class getColumnClass(int c) {
	return c == 0 ? String.class :
		c >= TEST_MEM_OFFS ? Integer.class :
			String.class;
    }

    public String getColumnName(int c) {
	return hdn[c];
    }

    public boolean isCellEditable(int r, int c) {
	return c >= TEST_MEM_OFFS;
    }

    public Object getValueAt(int row, int col) {
	if (col == 0)
	    return li.get(row).sound;

	if (col == 1) {
	    String se = li.get(row).sound;
	    if (se == null)
		se = "";
	    String sent = se.replaceAll("\\{[a-z0-9]*?\\}", "");
	    String s = sprop.l_ctxt.getLesson().action_specific.getAction(sent);
	    if (s != null)
		return s;
	    else
		return "";
	}

	if (col == 2) {
	    String se = row < li_set.size() ? li_set.get(row) : "";
	    if (se == null)
		se = "";
	    return se;
	}

	if (col >= TEST_MEM_OFFS) {
	    return col;//new Integer(test_member_map[row][col - TEST_MEM_OFFS]);
	}

	return "";
    }

    public void setValueAt(Object val, int row, int col) {
	omega.Context.sout_log.getLogger().info("ERR: " + "SET VAL " + val);
//	if (col == SentenceProperty.COL_ACT) {
//	    String sent = sa[row].replaceAll("\\{[a-z0-9]*?\\}", "");
//	    sprop.l_ctxt.getLesson().action_specific.setAction(sent, (String) val);
//	}
//	if (col == SentenceProperty.COL_SIGN) {
//	    String sent = sa[row].replaceAll("\\{[a-z0-9]*?\\}", "");
//	    sprop.l_ctxt.getLesson().action_specific.setSign(sent, (String) val);
//	}
//	if (col >= TEST_MEM_OFFS) {
//	    test_member_map[row][col - TEST_MEM_OFFS] = ((Integer) val).intValue();
//	    sprop.l_ctxt.getLesson().setTestMatrix(sa, test_member_map);
//	}
//	sprop.repaint();
    }
}

