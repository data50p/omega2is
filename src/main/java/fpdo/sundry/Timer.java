package fpdo.sundry;

public class Timer {
    private long ct0;

    public Timer() {
	ct0 = S.ct();
    }

    public int get() {
	return (int) (S.ct() - ct0);
    }

    public String toString() {
	return "" + get();
    }
}
