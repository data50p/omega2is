package omega.anim.tool.timeline;

import omega.t9n.T;

public class TriggerEventImageAttrib extends TriggerEvent {
    public String getCmd() {
        return "ImageAttrib";
    }

    public String getCmdLabel() {
        return T.t("dep_set Image Attribute");
    }

    public String getHelp() {
        return T.t("Tail part in filename (${banid:var})");
    }

    public TriggerEventImageAttrib() {
        super("");
    }

    public TriggerEventImageAttrib(String arg) {
        super(arg);
    }
}
