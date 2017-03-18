package fpdo.xml;

public abstract class Node {
    public abstract void render(StringBuffer sbu,
                                StringBuffer sbl);

    public void render(StringBuffer sb) {
        render(sb, null);
    }
}
