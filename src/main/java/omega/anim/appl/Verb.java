package omega.anim.appl;

import fpdo.sundry.S;

import java.util.ArrayList;
import java.util.List;

public class Verb {
    String all;
    String[] part;

    Verb(String s) {
        all = s;

        String[] sa = S.split(s, " ");

        List li = new ArrayList();

        String ww = "";
        for (int i = 0; i < sa.length; i++) {
            String w = sa[i];
            if (w.charAt(0) == '$') {
                if (ww.length() > 0)
                    li.add(ww);
                ww = "";
                li.add(w);
            } else {
                if (ww.length() == 0)
                    ww += w;
                else
                    ww += " " + w;
            }
        }
        if (ww.length() > 0)
            li.add(ww);
        ww = "";

        part = (String[]) li.toArray(new String[0]);
    }

    String getBase() {
        for (int i = 0; i < part.length; i++)
            if (part[i].charAt(0) != '$')
                return part[i];
        return "";
    }

    String[] getActorsPath() {
        ArrayList li = new ArrayList();

        for (int i = 0; i < part.length; i++)
            if (part[i].charAt(0) == '$')
                li.add(part[i].substring(1));
        return (String[]) li.toArray(new String[0]);
    }

    public String toString() {
        return "[" + S.arrToString(part) + "]";
    }
}
