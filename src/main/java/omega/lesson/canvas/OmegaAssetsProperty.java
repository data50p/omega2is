package omega.lesson.canvas;


import fpdo.sundry.S;
import omega.Config;
import omega.Context;
import omega.Version;
import omega.adm.assets.TargetCombinations;
import omega.i18n.T;
import omega.lesson.LessonContext;
import omega.lesson.canvas.result.ChooseOmegaBundleFile;
import omega.swing.TableSorter;
import omega.value.Value;
import omega.value.Values;
import org.hs.jfc.FormPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class OmegaAssetsProperty extends Property_B {

    static final int COL_MEDIA = 0;
    static final int COL_FOOBAR = 1;
    static final int COL_ACT1 = 2;
    static final int COL_ACT2 = 3;
    public static final String OMEGA_BUNDLE_MANIFEST = "bundle.omega_manifest";

    HashMap guimap = new HashMap();
    LessonContext l_ctxt;
    JFrame owner;

    JTable table;
    JButton set_act_b;
    JButton set_sgn_b;

    JRadioButton rb_def, rb_act;
    JRadioButton rb_defSign, rb_actSign;

    int[][] tmm;

    OmAssProp_TableModel tmod;

    TargetCombinations latestTargetCombinations;
    static TargetCombinations.Builder targetCombinationsBuilder = new TargetCombinations.Builder();
    private JButton oaBundleJB;
    private JTextField infoTF;

    OmegaAssetsProperty(JFrame owner, LessonContext l_ctxt) {
        super(owner, T.t("Omega - Assets Dialog"));
        this.owner = owner;
        this.l_ctxt = l_ctxt;
        build(getContentPane());
        pack();
        setVisible(true);
    }

    void destroy() {
    }

    public void refresh() {
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Dimension d2 = new Dimension((int) d.getWidth() + 100, (int) d.getHeight());
        return d2;
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension d = super.getMinimumSize();
        return d;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension d = super.getMaximumSize();
        return d;
    }


    class myActionListener implements ActionListener {
        void set(String what, String value) {
            JTextField tf = (JTextField) guimap.get(what);
            if (value == null) {
                String def = tf.getText();
                value = l_ctxt.getLessonCanvas().askForOneTarget(OmegaAssetsProperty.this, def);
                if (value == null)
                    value = def;

                // what to do ?
            }
        }

        public void actionPerformed(ActionEvent ev) {
            String s = ev.getActionCommand();

            if (s.equals("dump assets")) {
                ChooseGenericFile choose_f = new ChooseGenericFile(true);

                String url_s = null;
                int rv = choose_f.showDialog(omega.lesson.appl.ApplContext.top_frame, T.t("Save"));
                omega.Context.sout_log.getLogger().info("ERR: " + "choose file -> " + rv);
                if (rv == JFileChooser.APPROVE_OPTION) {
                    File file = choose_f.getSelectedFile();
                    PrintWriter pw = S.createPrintWriter(file.getPath());
                    for (String s2 : targetCombinationsBuilder.asOne().src_set) {
                        print(pw, "src", s2);
                    }
                    for (String s2 : targetCombinationsBuilder.asOne().dep_set) {
                        print(pw, "dep", s2);
                    }
                    pw.close();
                }
            }

            if (s.equals("save bundle")) {
                ChooseOmegaBundleFile choose_f = new ChooseOmegaBundleFile(true);

                String url_s = null;
                int rv = choose_f.showDialog(omega.lesson.appl.ApplContext.top_frame, T.t("Save"));
                omega.Context.sout_log.getLogger().info("ERR: " + "choose file -> " + rv);
                if (rv == JFileChooser.APPROVE_OPTION) {
                    File file = choose_f.getSelectedFile();
                    if (!file.getName().endsWith(Config.OMEGA_BUNDLE_EXTENSION))
                        file = new File(file.getPath() + Config.OMEGA_BUNDLE_EXTENSION);
                    try {
                        java.util.List<String> manifest = new ArrayList<>();
                        for (String s2 : targetCombinationsBuilder.asOne().src_set) {
                            String manifestInfo = manifestInfo(s2);
                            manifest.add(manifestInfo);
                        }
                        for (String s2 : targetCombinationsBuilder.asOne().dep_set) {
                            String manifestInfo = manifestInfo(s2);
                            manifest.add(manifestInfo);
                        }

                        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(file));
                        StringBuilder sb = new StringBuilder();
                        sb.append("type: omega-assets\n");
                        sb.append("version: " + Version.getVersion() + "\n");
                        sb.append("saved: " + new Date() + "\n");
                        sb.append("user: " + System.getProperty("user.name") + "\n");
                        sb.append("info: " + infoTF.getText() + "\n");
                        for(String man: manifest)
                            sb.append(man + "\n");
                        putData(out, OMEGA_BUNDLE_MANIFEST, sb.toString());

                        for (String s2 : targetCombinationsBuilder.asOne().src_set) {
                            put(out, s2);
                        }
                        for (String s2 : targetCombinationsBuilder.asOne().dep_set) {
                            put(out, s2);
                        }

                        out.close();

                    } catch (IOException e) {
                    }
                }
            }

            if (s.equals("new bundle")) {
                targetCombinationsBuilder = new TargetCombinations.Builder();
                targetCombinationsBuilder.add(latestTargetCombinations);
                oaBundleJB.setText(T.t("Add Omega Assets to Bundle") + " " + targetCombinationsBuilder.srcSize());
            }

            if (s.equals("add bundle")) {
                targetCombinationsBuilder.add(latestTargetCombinations);
                latestTargetCombinations = targetCombinationsBuilder.asOne();
                tmod.update(latestTargetCombinations);
                oaBundleJB.setText(T.t("Add Omega Assets to Bundle") + " " + targetCombinationsBuilder.srcSize());
            }

            if (s.equals("import bundle")) {
                importOmegaAssetsBundle();
                tmod.update(latestTargetCombinations);
                oaBundleJB.setText(T.t("Import Omega Bundle") + " " + targetCombinationsBuilder.srcSize());
            }

            if (s.equals("close")) {
                setVisible(false);
            }
        }
    }

    private String manifestInfo(String fName) {
        File f = new File(Context.omegaAssets(fName));
        if (f.exists() && f.canRead())
            return "entry: " + f.length() + ", " + fName;
        else
            return "entry: -1, " + fName;
    }

    private void importOmegaAssetsBundle() {
        ChooseOmegaBundleFile choose_f = new ChooseOmegaBundleFile(true);

        String url_s = null;
        int rv = choose_f.showDialog(omega.lesson.appl.ApplContext.top_frame, T.t("Import"));
        omega.Context.sout_log.getLogger().info("ERR: " + "choose file -> " + rv);
        if (rv == JFileChooser.APPROVE_OPTION) {
            File file = choose_f.getSelectedFile();
            if (!file.getName().endsWith(".omega_bundle"))

                // add dialog

                return;
            try {
                ZipInputStream in = new ZipInputStream(new FileInputStream(file));
                for (; ; ) {
                    ZipEntry zent = in.getNextEntry();
                    if (zent == null)
                        break;

                    String name = zent.getName();
                    if (zent.isDirectory()) {
                        File dir = new File(Context.omegaAssets(name));
                        if (dir.mkdirs()) {
                            System.err.println("Created dir: T " + dir);
                        } else {
                            System.err.println("Created dir: f " + dir);
                        }
                    } else {
                        try {
                            System.err.println("Got: " + name + ' ' + Context.omegaAssets("."));
                            FileOutputStream output = null;
                            boolean omega_bundle = OMEGA_BUNDLE_MANIFEST.equals(name);
                            if (omega_bundle)
                                name = rmExt(file.getName()) + "-" + name;
                            File entFile = new File(Context.omegaAssets(name));
                            long time = zent.getTime();
                            try {
                                if (!entFile.getParentFile().exists())
                                    entFile.getParentFile().mkdirs();
                                if (entFile.exists()) {
                                    System.err.println("Overwrite: exist " + entFile);
//                                    continue;
                                }
                                output = new FileOutputStream(entFile);
                                int len = 0;
                                byte[] buffer = new byte[4096];
                                while ((len = in.read(buffer)) > 0) {
                                    output.write(buffer, 0, len);
                                    // hack
                                    if (omega_bundle) {
                                        String infoText = new String(buffer, 0, len);
                                        String sa[] = infoText.split("\n");
                                        for (String s : sa) {
                                            if (s.startsWith("info: "))
                                                infoTF.setText(s.substring(6));
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (output != null)
                                    output.close();
                            }
                            entFile.setLastModified(time);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                in.close();

            } catch (IOException e) {
            }
        }
    }

    private String rmExt(String name) {
        int ix = name.lastIndexOf(".");
        if (ix != -1)
            return name.substring(0, ix);
        return name;
    }

    private void print(PrintWriter pw, String prfx, String s2) {
        File f = new File(Context.omegaAssets(s2));
        String stat = f.exists() && f.canRead() ? "OK" : "??";
        pw.println(prfx + ", " + stat + ", " + s2);
    }

    private void put(ZipOutputStream out, String s2) throws IOException {
        byte[] data = fileAsBytaArray(s2);
        if (data != null) {
            ZipEntry e = new ZipEntry(s2);
            File f = new File(Context.omegaAssets(s2));
            e.setTime(f.lastModified());
            out.putNextEntry(e);

            out.write(data, 0, data.length);
            out.closeEntry();
        }
    }

    private void putData(ZipOutputStream out, String name, String text) throws IOException {
        ZipEntry e = new ZipEntry(name);
        out.putNextEntry(e);
        out.write(text.getBytes("utf-8"));
        out.closeEntry();
    }


    private byte[] fileAsBytaArray(String fn) {
        File f = new File(Context.omegaAssets(fn));
        try {
            long fileSize = f.length();
            if (fileSize > Integer.MAX_VALUE) {
                fileSize = Integer.MAX_VALUE;
            }
            InputStream is = new FileInputStream(f);
            byte[] data = new byte[(int) fileSize];
            byte[] buf = new byte[1024];
            int pos = 0;
            for (; ; ) {
                int n = is.read(buf);
                if (n == -1)
                    break;
                System.arraycopy(buf, 0, data, pos, n);
                pos += n;
            }
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    OmegaAssetsProperty.myActionListener myactl = new OmegaAssetsProperty.myActionListener();

    // when item in table selected
    class MyListSelectionModel extends DefaultListSelectionModel implements ListSelectionListener {
        MyListSelectionModel() {
            addListSelectionListener(this);
        }

        public void valueChanged(ListSelectionEvent ev) {
//log	    omega.Context.sout_log.getLogger().info("ERR: " + "" + ev);
            if (ev.getValueIsAdjusting() == false) {
                OmegaAssetsProperty.MyListSelectionModel lselmod_ = (OmegaAssetsProperty.MyListSelectionModel) ev.getSource();
                int ix = lselmod_.getMinSelectionIndex();
                if (ix >= 0) {
                    TableModel tmod = (TableModel) table.getModel();
                    String s = (String) tmod.getValueAt(ix, COL_ACT1);
                }
            }
        }
    }

    OmegaAssetsProperty.MyListSelectionModel lselmod = new OmegaAssetsProperty.MyListSelectionModel();


    class CloseAction extends AbstractAction {
        CloseAction() {
            super(T.t("Close"));
        }

        public void actionPerformed(ActionEvent ev) {
            setVisible(false);
        }
    }

    ;

    void build(Container con) {
        FormPanel fpan = new FormPanel(5, 5, 7, 15);

        //	JPanel pan1 = new JPanel();
        con.setLayout(new BorderLayout());

        JLabel jl;
        JTextField tf;
        JComboBox cb;
        JCheckBox ch;
        JButton jb;

        int Y = 0;
        int X = 0;

// 	fpan.add(new JLabel(T.t("Parameter:   ")), gbcf.createL(X++, Y, 1));
// 	fpan.add(new JLabel(T.t("Value:          ")),  gbcf.createL(X++, Y, 1));

// 	Y++;
// 	X = 0;
        fpan.add(jl = new JLabel(T.t("Info")), tf = new JTextField("", 40), Y, ++X);
        infoTF = tf;
        infoTF.setEditable(true);

        guimap.put("info", tf);
        tf.getDocument().addDocumentListener(mydocl);
        tf.setEnabled(true);

        fpan.add(new JLabel(""), jb = new JButton(T.t("New Omega Bundle")), Y, ++X);
        jb.setActionCommand("new bundle");
        jb.addActionListener(myactl);

        fpan.add(new JLabel(""), jb = new JButton(T.t("Add Omega Assets to Bundle") + " " + targetCombinationsBuilder.srcSize()), Y, ++X);
        jb.setActionCommand("add bundle");
        jb.addActionListener(myactl);
        oaBundleJB = jb;

        fpan.add(new JLabel(""), jb = new JButton(T.t("Save Omega Bundle")), Y, ++X);
        jb.setActionCommand("save bundle");
        jb.addActionListener(myactl);


        Y++;
        X = 0;
        fpan.add(new JLabel(""), jb = new JButton(T.t("Save Omega Bundle list")), Y, ++X);
        jb.setActionCommand("dump assets");
        jb.addActionListener(myactl);

        fpan.add(new JLabel(""), jb = new JButton(T.t("Import Omega Assets Bundle")), Y, ++X);
        jb.setActionCommand("import bundle");
        jb.addActionListener(myactl);


        Y++;
        X = 0;
        fpan.add(new JLabel(""), new JLabel(T.t("(Shift) Click on the table header to (reverse) sort")), Y, ++X);

        Y++;
        X = 0;

        latestTargetCombinations = l_ctxt.getLessonCanvas().getAllTargetCombinationsEx2(false);
        latestTargetCombinations.src_set.add(l_ctxt.getLesson().getLoadedFName());
        tmod = new OmAssProp_TableModel(this, latestTargetCombinations, tmm);

        TableSorter tsort = new TableSorter(tmod);

        table = new JTable(tsort);
        tsort.addMouseListenerToHeaderInTable(table);

        JScrollPane jscr = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn tcol = table.getColumnModel().getColumn(i);
            tcol.setPreferredWidth(i == 0 ? 350 :
                    i == 1 ? 350 :
                            i == 2 ? 40 :
                                    40);
        }
        try {
            table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
            table.setSelectionModel(lselmod);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setRowSelectionInterval(0, 0);
            table.setPreferredScrollableViewportSize(new Dimension(830, 300));
        } catch (Exception ex) {
        }

        //	fpan.add(new JLabel(""), jscr, Y, ++X);

// 	JPanel c_pan = new JPanel();
// 	pan1.add(c_pan,  gbcf.createL(X++, Y, 5));

        con.add(fpan, BorderLayout.NORTH);
        con.add(jscr, BorderLayout.CENTER);
        JPanel jpa = new JPanel();
        jpa.add(jb = new JButton(new OmegaAssetsProperty.CloseAction()));
        con.add(jpa, BorderLayout.SOUTH);
    }

    String setActionField(String current) {
        ChooseSpecificActionFile choose_f = new ChooseSpecificActionFile();

        String url_s = null;
        int rv = choose_f.showDialog(omega.lesson.appl.ApplContext.top_frame, T.t("Select"));
//log	omega.Context.sout_log.getLogger().info("ERR: " + "choose file -> " + rv);
        if (rv == JFileChooser.APPROVE_OPTION) {
            File file = choose_f.getSelectedFile();
            url_s = omega.util.Files.toURL(file);

            String tfn = omega.util.Files.rmHead(url_s);
            return tfn;
        }
        return null;
    }

    public void updValues(Values vs) {
        Iterator it = vs.iterator();
        while (it.hasNext()) {
            Value v = (Value) it.next();
        }
    }

    void updTrigger(Document doc) {
        Iterator it = guimap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object o = guimap.get(key);
            if (o instanceof JTextField) {
                JTextField tf = (JTextField) o;
                if (doc == tf.getDocument()) {
                    String txt = tf.getText();
                    fireValueChanged(new Value(key, txt));
                }
            }
        }
    }

    void setLabel(String id, String txt) {
        Iterator it = guimap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (key.equals(id)) {
                Object o = guimap.get(key);
                if (o instanceof JLabel) {
                    JLabel jl = (JLabel) o;
                    jl.setText(txt);
                }
            }
        }
    }

    void updTrigger(JComboBox cb) {
        try {
            JComboBox cbg;

//  	    cbg = (JComboBox)guimap.get("type");
//  	    if ( cb == cbg ) {
//  		String s = (String)cb.getSelectedItem();
//  		omega.Context.sout_log.getLogger().info("ERR: " + "CB type " + cb);
//  		if ( s.equals("action") )
//  		    setLabel("Llid", "Path id");
//  		if ( s.equals("actor") )
//    		    setLabel("Llid", "Path id");
//  		else
//  		    setLabel("Llid", "-");
//  	    }

            cbg = (JComboBox) guimap.get("Slid");
            if (cb == cbg) {
                JTextField tf = (JTextField) guimap.get("lid");
                updTF(tf, cbg);
            }

        } catch (ClassCastException ex) {
            omega.Context.sout_log.getLogger().info("ERR: " + "CCE " + ex);
        }
    }

    void updTrigger(JCheckBox ch) {
        try {
            JCheckBox chg;

            chg = (JCheckBox) guimap.get("Slid");
        } catch (ClassCastException ex) {
            omega.Context.sout_log.getLogger().info("ERR: " + "CCE " + ex);
        }
    }

    public fpdo.xml.Element getElement() {
        fpdo.xml.Element el = new fpdo.xml.Element("test_prop");

        fpdo.xml.Element pel = new fpdo.xml.Element("test");
        pel.addAttr("kind", "pre");
        pel.addAttr("ord", "1");
        pel.addAttr("text", ((JTextField) (guimap.get("pret1"))).getText());
        el.add(pel);

        pel = new fpdo.xml.Element("test");
        pel.addAttr("kind", "pre");
        pel.addAttr("ord", "2");
        pel.addAttr("text", ((JTextField) (guimap.get("pret2"))).getText());
        el.add(pel);

        pel = new fpdo.xml.Element("test");
        pel.addAttr("kind", "post");
        pel.addAttr("ord", "1");
        pel.addAttr("text", ((JTextField) (guimap.get("postt1"))).getText());
        el.add(pel);

        pel = new fpdo.xml.Element("test");
        pel.addAttr("kind", "post");
        pel.addAttr("ord", "2");
        pel.addAttr("text", ((JTextField) (guimap.get("postt2"))).getText());
        el.add(pel);

        return el;
    }
}
