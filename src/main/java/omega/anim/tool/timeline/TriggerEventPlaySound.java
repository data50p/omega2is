package omega.anim.tool.timeline;

import omega.i18n.T;

public class TriggerEventPlaySound extends TriggerEvent {
    public String getCmd() {
        return "PlaySound";
    }

    public String getCmdLabel() {
        return T.t("Play Sound");
    }

    public String getHelp() {
        return T.t("audio file (${banid:var})");
    }

    public TriggerEventPlaySound() {
        super("");
    }

    public TriggerEventPlaySound(String fname) {
        super(fname);
    }

    public String[] getFiles() {
        return new String[]{"au", "wav", "mp3"};
    }
}
