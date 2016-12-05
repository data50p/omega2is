package omega.lesson.canvas;

import omega.Config;
import omega.Context;
import omega.anim.appl.AnimEditor;
import omega.anim.appl.EditStateListener;
import omega.i18n.T;
import omega.lesson.machine.Item;
import omega.lesson.machine.Target;
import omega.swing.GBC_Factory;
import omega.value.Value;
import omega.value.Values;
import omega.value.ValuesListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LessonEditorPanel extends JPanel {
    GBC_Factory gbcf = new GBC_Factory();

    //      JComboBox bgcol;
//      JComboBox btcol;
//      JComboBox sncol;
    JButton redraw;
    JButton editanim;
    JButton playSign;

    public LessonCanvas le_canvas;

    TargetProperty tg_prop;
    ItemProperty i_prop;
    //    TestProperty tst_prop;
    SentenceProperty snt_prop;

    int active_target_ix = -1;
    int active_item_ix = -1;
    int active_item_iy = -1;

    AnimEditor anim_editor;

    JTextField lesson_name;
    JTextField lesson_link_next;
    JCheckBox enable_LLN;
    JCheckBox first_LLN;
    JButton getFiles_LLN;

    JTextField editorLessonLang;

    boolean skipDirty = false;

    class myDocumentListener implements DocumentListener {
	public void changedUpdate(DocumentEvent de) {
	    Document doc = de.getDocument();
	    if (!skipDirty)
		omega.lesson.appl.LessonEditor.setDirty();
	}

	public void insertUpdate(DocumentEvent de) {
	    Document doc = de.getDocument();
	    if (!skipDirty)
		omega.lesson.appl.LessonEditor.setDirty();
	}

	public void removeUpdate(DocumentEvent de) {
	    Document doc = de.getDocument();
	    if (!skipDirty)
		omega.lesson.appl.LessonEditor.setDirty();
	}
    }

    myDocumentListener mydocl = new myDocumentListener();

    ValuesListener mvl_tg = new ValuesListener() {
	public void changed(Value v) {
//log		omega.Context.sout_log.getLogger().info("ERR: " + "=00= val list " + v);
	    Target.T_Item tit = le_canvas.getTarget().getT_Item(active_target_ix);
	    if (tit == null)
		return;
	    if (v.id.equals("tid"))
		tit.tid = v.getStr();
	    else if (v.id.equals("lid"))
		tit.lid = v.getStr();
	    else if (v.id.equals("type"))
		tit.type = v.getStr();
	    le_canvas.repaint(tit);
	}
    };

    ValuesListener mvl_it = new ValuesListener() {
	public void changed(Value v) {
	    int it_ix = active_item_ix;
	    int it_iy = active_item_iy;
	    Item itm = le_canvas.getTarget().getItemAt(it_ix, it_iy);
	    if (itm == null)
		return;
	    boolean is_action = itm.it_ent.type.equals("action");

	    if (v.id.equals("text")) {
		itm.setText_Krull(v.getStr());
	    } else if (v.id.equals("dummytext")) {
		itm.setDummyText_Krull(v.getStr(), true);
	    } else if (v.id.equals("tid")) {
		itm.it_ent.tid = v.getStr();
	    } else if (v.id.equals("v1")) {
		itm.setVar(1, v.getStr());
	    } else if (v.id.equals("v2")) {
		itm.setVar(2, v.getStr());
	    } else if (v.id.equals("v3")) {
		itm.setVar(3, v.getStr());
	    } else if (v.id.equals("lid")) {
		itm.setLid_Krull(v.getStr());
	    } else if (v.id.equals("sound")) {
		itm.setSound_Krull(v.getStr());
	    } else if (v.id.equals("sign")) {
		itm.setSign_Krull(v.getStr());
	    } else if (v.id.equals("dummysound")) {
		itm.setDummySound_Krull(v.getStr(), true);
	    } else if (v.id.equals("dummysign")) {
		itm.setDummySign_Krull(v.getStr(), true);
	    } else if (v.id.equals("fname")) {
		omega.Context.sout_log.getLogger().info("ERR: " + "FNAME " + itm.it_ent.type);
		if (true || itm.it_ent.type.equals("action")) { // isAction ) {
		    ((Item) (itm)).setAction_Fname(v.getStr(), "omega_anim");
		}
	    } else if (v.id.equals("ftype")) {
		if (itm.isAction) {
		    ((Item) (itm)).action_type = v.getStr();
		}
	    }
	    le_canvas.repaint(itm);
	}
    };

    class myActionListener implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
	    String s = ev.getActionCommand();

	    if (s.equals("tg_prop")) {
		popupTargetProp();
	    }
//  	    if ( s.equals("tst_prop") ) {
//  		popupTestProp();
//  	    }
	    if (s.equals("snt_prop")) {
		popupSentenceProp();
	    }
	    if (s.equals("itm_prop")) {
		popupItemProp();
	    }
	    if (s.equals("redraw")) {
		le_canvas.reCreateBoxesKeep();
	    }
	    if (s.equals("play")) {
		le_canvas.l_ctxt.getLesson().sendMsg("play&return", null);
	    }
	    if (s.equals("playSign")) {
		playSign();
	    }
	    if (s.equals("listen")) {
		le_canvas.l_ctxt.getLesson().sendMsg("listen", null);
	    }
	    if (s.equals("editanim")) {
		String fn = le_canvas.l_ctxt.getTarget().getActionFileName(0); // main default, first
		omega.Context.sout_log.getLogger().info("ERR: " + "MANY? " + fn);
		if (fn == null || fn.length() == 0) {
		    JOptionPane.showMessageDialog(omega.lesson.appl.LessonEditor.TOP_JFRAME,
			    //le_canvas.l_ctxt.top_frame,
			    T.t("Can't find lesson file: ") +
				    fn);
		} else {
		    if (anim_editor == null) {
			anim_editor = new AnimEditor(fn);
			anim_editor.addEditStateListener(new EditStateListener() {
			    public void dirtyChanged(boolean is_dirty) {
				if (is_dirty) {
				    editanim.setForeground(Color.red);
				} else {
				    editanim.setForeground(Color.black);
				}
			    }
			});
		    } else {
			anim_editor.setVisible(true);
			anim_editor.loadFile(fn);
		    }
		}
	    }
	    if (s.equals("enableLLN")) {
		boolean b = enable_LLN.isSelected();
		lesson_link_next.setEnabled(b);
		first_LLN.setEnabled(b);
	    }
	    if (s.equals("getFiles_LLN")) {
		ChooseActionFile choose_af = new ChooseActionFile();

		int rv = choose_af.showDialog(LessonEditorPanel.this, T.t("Select"));
		if (rv == JFileChooser.APPROVE_OPTION) {
		    try {
			File file = choose_af.getSelectedFile();
			String fname_s = file.getName();
//log			omega.Context.sout_log.getLogger().info("ERR: " + "--> " + fname_s);
			fname_s = file.toURI().toURL().toString(); // getCanonicalPath();
//log			omega.Context.sout_log.getLogger().info("ERR: " + "--> " + fname_s);
			String fn = omega.util.Files.rmHead(fname_s);
//log			omega.Context.sout_log.getLogger().info("ERR: " + "--> " + fn);
			lesson_link_next.setText(fn);
			enable_LLN.setSelected(true);
			lesson_link_next.setEnabled(true);
			//			omega.lesson.appl.LessonEditor.setDirty();
		    } catch (IOException ex) {
			omega.Context.sout_log.getLogger().info("ERR: " + "can't " + ex);
		    }
		}
	    }
	}

	private void playSign() {
	    // LIU
	    omega.Context.setLessonLang(editorLessonLang.getText());
	    le_canvas.l_ctxt.getLesson().sendMsg("playSign:", "media/sign-sv/M.mpg");
	}
    }

    ;
    myActionListener myactl = new myActionListener();

    class myChangeListener implements ChangeListener {
	public void stateChanged(ChangeEvent ev) {
	    boolean b = enable_LLN.isSelected();
	    lesson_link_next.setEnabled(b);
	    first_LLN.setEnabled(b);
	    if (!skipDirty)
		omega.lesson.appl.LessonEditor.setDirty();
	}
    }

    ;
    myChangeListener mychtl = new myChangeListener();

    public LessonEditorPanel(LessonCanvas le_canvas) {
	this.le_canvas = le_canvas;
	setLayout(new GridBagLayout());
	JComboBox cb;
	JButton b;

	int Y = 0;
	int X = 0;
	add(new JLabel(T.t("Lesson name:")), gbcf.createL(X++, Y, 1));
	add(lesson_name = new JTextField(T.t("")), gbcf.createL(X++, Y, 1));
	lesson_name.addActionListener(myactl);
	lesson_name.setActionCommand("lesson_name");
	Document doc2 = lesson_name.getDocument();
	doc2.addDocumentListener(mydocl);
	add(new JLabel(T.t("Next story:")), gbcf.createL(X++, Y, 1));
	JPanel jplln = new JPanel();

	lesson_link_next = new JTextField(T.t(""), 20);
	doc2 = lesson_link_next.getDocument();
	doc2.addDocumentListener(mydocl);
	enable_LLN = new JCheckBox();
	enable_LLN.setActionCommand("enableLLN");
	enable_LLN.addChangeListener(mychtl);
	jplln.add(enable_LLN);
	jplln.add(lesson_link_next);
	add(jplln, gbcf.createL(X++, Y, 2));
	X++;
	jplln.add(b = getFiles_LLN = new JButton("..."), gbcf.createL(X++, Y, 1));
	b.setActionCommand("getFiles_LLN");
	b.addActionListener(myactl);
	add(new JLabel(T.t("First in story:")), gbcf.createL(X++, Y, 1));
	first_LLN = new JCheckBox();
	first_LLN.setActionCommand("firstLLN");
	first_LLN.addChangeListener(mychtl);
	add(first_LLN);

	Y++;
	X = 0;
	add(new JLabel(T.t("Properties:")), gbcf.createL(X++, Y, 1));

	add(b = new JButton(T.t("Target")), gbcf.createL(X++, Y, 1));
	b.setActionCommand("tg_prop");
	b.addActionListener(myactl);

	add(b = new JButton(T.t("Item")), gbcf.create(X++, Y));
	b.setActionCommand("itm_prop");
	b.addActionListener(myactl);

	add(b = new JButton(T.t("Sentence")), gbcf.createL(X++, Y, 1));
	b.setActionCommand("snt_prop");
	b.addActionListener(myactl);

	add(editorLessonLang = new JTextField(T.t("Lesson Lang")), gbcf.createL(X++, Y, 1));
	editorLessonLang.setText(Context.getLessonLang());

	Y++;
	X = 0;
	add(new JLabel(T.t("Commands:")), gbcf.createL(X++, Y, 1));
	add(redraw = new JButton(T.t("Redraw")), gbcf.createL(X++, Y, 1));
	redraw.setActionCommand("redraw");
	redraw.addActionListener(myactl);

	add(redraw = new JButton(T.t("Play")), gbcf.createL(X++, Y, 1));
	redraw.setActionCommand("play");
	redraw.addActionListener(myactl);

	add(redraw = new JButton(T.t("Listen")), gbcf.createL(X++, Y, 1));
	redraw.setActionCommand("listen");
	redraw.addActionListener(myactl);

	if (Config.LIU_Mode) {
	    add(playSign = new JButton(T.t("Play Sign Moview")), gbcf.createL(X++, Y, 1));
	    playSign.setActionCommand("playSign");
	    playSign.addActionListener(myactl);
	}

	add(editanim = new JButton(T.t("Edit anim")), gbcf.createL(X++, Y, 1));
	editanim.setActionCommand("editanim");
	editanim.addActionListener(myactl);
    }

    void destroyAllPopups() {
	if (snt_prop != null) {
	    snt_prop.destroy();
	    snt_prop.removeAll();
	    snt_prop.setVisible(false);
	    snt_prop = null;
	}
	if (tg_prop != null) {
	    tg_prop.removeAll();
	    tg_prop.setVisible(false);
	    tg_prop = null;
	}
	if (i_prop != null) {
	    i_prop.removeAll();
	    i_prop.setVisible(false);
	    i_prop = null;
	}
    }

    public void popupSentenceProp() {
	if (snt_prop == null) {
	    snt_prop = new SentenceProperty(omega.lesson.appl.LessonEditor.TOP_JFRAME, le_canvas.l_ctxt);
	    snt_prop.addValuesListener(mvl_tg);
	} else {
	    snt_prop.destroy();
	    snt_prop.removeAll();
	    snt_prop.setVisible(false);
	    snt_prop = new SentenceProperty(omega.lesson.appl.LessonEditor.TOP_JFRAME, le_canvas.l_ctxt);
	    snt_prop.addValuesListener(mvl_tg);
	}
	snt_prop.setVisible(true);
    }

    public void popupTargetProp() {
	if (tg_prop == null) {
	    tg_prop = new TargetProperty(omega.lesson.appl.LessonEditor.TOP_JFRAME);
	    tg_prop.addValuesListener(mvl_tg);
	}
	tg_prop.setVisible(true);
    }

    public void popupItemProp() {
	if (i_prop == null) {
	    i_prop = new ItemProperty(omega.lesson.appl.LessonEditor.TOP_JFRAME);
	    i_prop.addValuesListener(mvl_it);
	}
	i_prop.setVisible(true);
    }

    public void setTarget(Values vs) {
	if (tg_prop != null)
	    tg_prop.updValues(vs);
//	if ( vs.
    }

    public void setActiveTargetIx(int ix) {
	active_target_ix = ix;
    }

    public void setActiveItemIx(int ix, int iy) {
	active_item_ix = ix;
	active_item_iy = iy;
    }

    public void setItem(Values vs) {
	if (i_prop != null)
	    i_prop.updValues(vs);
    }

    public void setLessonName(String s) {
	skipDirty = true;
	lesson_name.setText(s);
	skipDirty = false;
    }

    public void setLessonLinkNext(String s) {
	skipDirty = true;
	boolean b = s == null;
	enable_LLN.setSelected(!b);
	if (s == null)
	    s = "";
	lesson_link_next.setText(s);
	skipDirty = false;
    }

    public void setLessonIsFirst(boolean b) {
	skipDirty = true;
	first_LLN.setSelected(b);
	skipDirty = false;
    }

    public String getLessonName() {
	return lesson_name.getText();
    }

    public String getLessonLinkNext() {
	if (enable_LLN.isSelected())
	    return lesson_link_next.getText();
	return null;
    }

    public boolean getLessonIsFirst() {
	return first_LLN.isSelected();
    }
}
