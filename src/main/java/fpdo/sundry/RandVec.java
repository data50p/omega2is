package fpdo.sundry;

public class RandVec {
    int[] ia;
    private int[] accum;
    private int sum;
    private int maxsum;
    private long seed = S.rand(0x7fffffff);
    private java.util.Random randG;
    private boolean invalid = false;

    private int rand(int a) {
	int r = 0x7fffffff & randG.nextInt();
	return r % a;
    }

    public RandVec(int[] ia) {
	randG = new java.util.Random(seed);
	this.ia = (int[]) ia.clone();
	maxsum = sum = U.sum(this.ia);
	accum = new int[this.ia.length];

	mkAccum();
	if (sum != accum[this.ia.length - 1])
	    throw new Error("RandVec: sum != accum");
    }

    public Object clone() {
	if (invalid) {
	    throw new RuntimeException("RandVec.clone(): Object invalid!");
	}
	RandVec nrv = new RandVec(ia);
	nrv.seed = seed;
	nrv.randG = new java.util.Random(seed);
	return nrv;
    }

    public int size() {
	return maxsum;
    }

    public void unget(int a) {
	ia[a]++;
	sum++;
	mkAccum();
    }

    public int getNext() {
//  	omega.Context.sout_log.getLogger().info("accum " + S.arrToString(ia));
//  	omega.Context.sout_log.getLogger().info("accum " + S.arrToString(accum));
	int r = rand(sum);
	for (int i = 0; i < accum.length; i++)
	    if (r < accum[i]) {
		invalid = true;
		ia[i]--;
		sum--;
//    		if ( sum < 0 )
//    		    throw new Error("RandVec: counter < 0");
		mkAccum();
		return i;
	    }
	throw new Error("RandVec: can't get number");
    }

    public int[] getAsIntA() {
	int[] ia = new int[maxsum];

	for (int i = 0; i < ia.length; i++)
	    ia[i] = getNext();

	return ia;
    }

    private void mkAccum() {
	int a = 0;
	for (int i = 0; i < ia.length; i++) {
	    a += ia[i];
	    accum[i] = a;
	}
    }

    public String toString() {
	return "RandVec{" +
		"sum=" + sum +
		", maxsum=" + maxsum +
		", seed=" + seed +
		", randG=" + randG +
		", ia[]=" + S.arrToString(ia) +
		"}";
    }

    static public void main(String[] args) {
	for (int j = 0; j < 2; j++) {
	    RandVec rv = new RandVec(new int[]{1,
		    2,
		    4,
		    10
	    });
	    omega.Context.sout_log.getLogger().info("----- " + rv);
	    RandVec rvc = (RandVec) rv.clone();
	    for (int i = 0; i < rv.size(); i++) {
		int a = rv.getNext();
		omega.Context.sout_log.getLogger().info("a " + a);
	    }
	    omega.Context.sout_log.getLogger().info("-----" + rvc);
	    for (int i = 0; i < rvc.size(); i++) {
		int a = rvc.getNext();
		omega.Context.sout_log.getLogger().info("c " + a);
	    }
	}
    }
}
