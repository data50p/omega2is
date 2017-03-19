package omega.anim.tool.timeline;

import fpdo.sundry.S;
import omega.t9n.T;

public class TriggerEventScale extends TriggerEvent {
    public String getCmd() {
        return "Scale";
    }

    public String getCmdLabel() {
        return T.t("Scale object");
    }

    public String getHelp() {
        return T.t("<factor change / sec> <end factor=1.0>");
    }

    public TriggerEventScale() {
        super("0.0");
    }

    public TriggerEventScale(String arg) {
        super(arg);
    }

    public double getArgDouble() {
        String s = getArgString();
        String sa[] = S.split(s, " ");
        if (sa.length == 1) {
            double d = S.tD(s);
            return d;
        }
        double d = S.tD(sa[0]);
        return d;
    }

    public double getArgDouble2nd() {
        String s = getArgString();
        String sa[] = S.split(s, " ");
        if (sa.length == 1) {
            return 1.0;
        }
        double d = S.tD(sa[1]);
        return d;
    }
}
