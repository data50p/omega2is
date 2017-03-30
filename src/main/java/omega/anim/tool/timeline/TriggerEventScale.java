package omega.anim.tool.timeline;

import omega.t9n.T;
import omega.util.SundryUtils;

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
        String sa[] = SundryUtils.split(s, " ");
        if (sa.length == 1) {
            double d = SundryUtils.tD(s);
            return d;
        }
        double d = SundryUtils.tD(sa[0]);
        return d;
    }

    public double getArgDouble2nd() {
        String s = getArgString();
        String sa[] = SundryUtils.split(s, " ");
        if (sa.length == 1) {
            return 1.0;
        }
        double d = SundryUtils.tD(sa[1]);
        return d;
    }
}
