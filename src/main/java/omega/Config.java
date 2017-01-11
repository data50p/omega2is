package omega;

public class Config {
    static public int TIMELINES_N = 4;
    static public int CABARET_ACTOR_N = 12;
    static public int WINGS_N = 10;
    static public boolean T = !false;
    static public boolean RUN_MODE = false;
    static public int t_step = 10;

    static public int key_next_1 = 9;
    static public int key_next_2 = 9;
    static public int key_select_1 = ' ';
    static public int key_select_2 = '\r';
    static public int key_select_3 = '\n';

    static public boolean LIU_Mode = true;

    static public boolean alwaysShowLicense = false;
    public static boolean fullScreen = false;

    static public boolean isKeyNext(int kc) {
	return kc == key_next_1 ||
		kc == key_next_2;
    }

    static public boolean isKeySelect(int kc) {
	return
		kc == key_select_1 ||
			kc == key_select_2 ||
			kc == key_select_3;
    }

    static public boolean isKeyESC(int kc) {
	return
		kc == '\033';
    }

    public static void setNextKey() {
	key_next_2 = ' ';
	key_select_1 = '\r';
    }

    public static void setSelectKey() {
	key_next_2 = 9;
	key_select_1 = ' ';
    }
}
