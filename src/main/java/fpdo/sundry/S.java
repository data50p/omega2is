//
//	$Id: S.java,v 1.3 2000/02/22 09:48:40 lars Exp $
//

package fpdo.sundry;

import java.io.*;
import java.util.*;

public class S {
    public static void m_sleep(int a) {
	try {
	    if (a <= 0)
		a = 10;
	    Thread.sleep(a);
	} catch (InterruptedException e) {
	}
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static BufferedReader fopenr(String fn) {
	try {
	    FileReader fr = new FileReader(fn);
	    return new BufferedReader(fr);
	} catch (Exception ex) {
	    return null;
	}
    }

    public static PrintWriter createPrintWriter(String fn) {
	return createPrintWriter(fn, false);
    }

    public static PrintWriter createPrintWriter(String fn, boolean append) {
	try {
	    PrintWriter pw =
		    new PrintWriter(
			    new BufferedWriter(
				    new FileWriter(fn, append)));
	    return pw;
	} catch (IOException ex) {
	    return null;

	}
    }

    public static PrintWriter createPrintWriter(OutputStream os) {
	PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(os)));
	return pw;
    }


    public static PrintWriter createPrintWriterUTF8(OutputStream os) {
	try {
	    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(os), "UTF-8"));
	    return pw;
	} catch (IOException ex) {
	    return null;
	}
    }

    public static PrintWriter createPrintWriterUTF8(String fn) {
	return createPrintWriterUTF8(fn, false);
    }

    public static PrintWriter createPrintWriterUTF8(String fn, boolean append) {
	try {
	    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(fn, append)), "UTF-8"));
	    return pw;
	} catch (IOException ex) {
	    return null;
	}
    }


// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static String as02(int d) {
	if (d < 10)
	    return "0" + d;
	else
	    return "" + d;
    }

    public static String as_2(int d) {
	if (d < 10)
	    return " " + d;
	else
	    return "" + d;
    }

    public static String as_02(double d) {
	if (d < 0)
	    return "-" + as_02(-d);
	int a = (int) d;
	double f = d - a;
	f *= 100;
	int b = (int) (f + 0.001);
	if (b == 100)
	    return "" + (a + 1) + ".00";
	else if (b < 10)
	    return "" + a + ".0" + b;
	else
	    return "" + a + "." + b;
    }

    public static String pR(int a, int w) {
	String s = "" + a;
	int n = w - s.length();
	String ss = "";
	for (int i = 0; i < n; i++)
	    ss += ' ';
	return s + ss;
    }

    public static String pL(int a, int w, char pad) {
	String s = "" + a;
	int n = w - s.length();
	String ss = "";
	for (int i = 0; i < n; i++)
	    ss += pad;
	return ss + s;
    }

    public static String pL(int a, int w) {
	return pL(a, w, ' ');
    }

    public static String padLeft(String s, int len, char ch) {
	if (s.length() < len) {
	    String ps = "";
	    for (int i = 0; i < len - s.length(); i++)
		ps += ch;
	    return ps + s;
	}
	return s;
    }

    public static String padRight(String s, int len, char ch) {
	if (s.length() < len) {
	    String ps = "";
	    for (int i = 0; i < len - s.length(); i++)
		ps += ch;
	    return s + ps;
	}
	return s;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static long ct() {
	return System.currentTimeMillis();
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static String[] split(String str, String split) {
	StringTokenizer t = new StringTokenizer(str, split);

	int n = t.countTokens();
	int i = 0;
	String[] arr = new String[n];

	for (; t.hasMoreTokens(); ) {
	    String word = t.nextToken();
	    arr[i++] = word;
	}
	return arr;
    }

    public static int[] splitI(String str, String split) {
	String sa[] = split(str, split);
	int[] ia = (int[]) castArray(sa, new int[0]);
	return ia;
    }

    public static double[] splitD(String str, String split) {
	String sa[] = split(str, split);
	double[] ia = (double[]) castArray(sa, new double[0]);
	return ia;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static String a2s(Object o, int w) {
	return arrToString(o, ",", w, ' ');
    }

    public static String a2s(Object o) {
	return arrToString(o, ",", 0, ' ');
    }

    public static String a2s(Object o, String delim) {
	return arrToString(o, delim, 0, ' ');
    }

    public static String a2s(Object o, String delim, int w, char pad) {
	return arrToString(o, delim, w, pad);
    }

    public static String arrToString(Object o, int w) {
	return arrToString(o, ",", w, ' ');
    }

    public static String arrToString(Object o) {
	return arrToString(o, ",", 0, ' ');
    }

    public static String arrToString(Object o, String delim) {
	return arrToString(o, delim, 0, ' ');
    }

    public static String arrToString(Object o, String delim, int w, char pad) {
	if (o == null)
	    return "null";
	StringBuffer s = new StringBuffer();
	Class cls = o.getClass();
	if (cls.isArray()) {
	    Class clsc = cls.getComponentType();
	    if (!clsc.isPrimitive()) {
		if (clsc.isArray()) {
		    Object[] oa = (Object[]) o;
		    for (int i = 0; i < oa.length; i++)
			s.append((i == 0 ? "" : delim) +
				"[" + arrToString(oa[i]) + "]");
		    return s.toString();
		} else {
		    Object[] oa = (Object[]) o;
		    for (int i = 0; i < oa.length; i++)
			s.append((i == 0 ? "" : delim) + oa[i]);
		    return s.toString();
		}
	    }
	    if (clsc.getName().equals("int")) {
		int[] ia = (int[]) o;
		for (int i = 0; i < ia.length; i++)
		    if (w == 0)
			s.append((i == 0 ? "" : delim) + ia[i]);
		    else
			s.append((i == 0 ? "" : delim) + pL(ia[i], w, pad));
		return s.toString();
	    }
	    if (clsc.getName().equals("char")) {
		char[] ia = (char[]) o;
		for (int i = 0; i < ia.length; i++)
		    if (ia[i] == 0)
			s.append((i == 0 ? "" : delim) + "^@");
		    else
			s.append((i == 0 ? "" : delim) + ia[i]);
		return s.toString();
	    }
	    if (clsc.getName().equals("byte")) {
		byte[] ia = (byte[]) o;
		for (int i = 0; i < ia.length; i++)
		    s.append((i == 0 ? "" : delim) + ia[i]);
		return s.toString();
	    }
	    if (clsc.getName().equals("boolean")) {
		boolean[] ba = (boolean[]) o;
		for (int i = 0; i < ba.length; i++)
		    s.append((i == 0 ? "" : delim) + ba[i]);
		return s.toString();
	    }
	}
	return null;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

//      public static Object copyArr(Object o) {
//  	if ( o == null )
//  	    return null;
//  	Class cls = o.getClass();
//  	if ( cls.isArray() ) {
//  	    Class clsc = cls.getComponentType();
//  	    int len = java.lang.reflect.Array.getLength(o);
//  	    Object na = java.lang.reflect.Array.newInstance(clsc, len);
//  	    System.arraycopy(o, 0, na, 0, len);
//  	    return na;
//  	}
//  	return null;
//      }

    // use:        int[] ia = new int[10];
    //             int[] ia2 = (int[])ia.clone());
// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static Object castArray(Object o, Object to) {
	if (o == null)
	    return null;
	Class cls = o.getClass();
	Class tcls = to.getClass();
	if (cls.isArray()) {
	    Class clsc = cls.getComponentType();
	    Class tclsc = tcls.getComponentType();
	    if (!tclsc.isPrimitive()) {
		throw new
			RuntimeException("cast obj[] -> obj'[] Not supported yet");
	    }
	    if (tclsc.getName().equals("int")) {
		if (clsc.getName().equals("java.lang.String")) {
		    int l = java.lang.reflect.Array.getLength(o);
		    int[] ia = new int[l];
		    String[] sa = (String[]) o;
		    try {
			for (int i = 0; i < ia.length; i++) {
			    ia[i] = Integer.parseInt(sa[i]);
			}
			return ia;
		    } catch (NumberFormatException ex) {
			return null;
		    }
		} else {
		    int l = java.lang.reflect.Array.getLength(o);
		    int[] ia = new int[l];
		    for (int i = 0; i < ia.length; i++) {
			ia[i] = java.lang.reflect.Array.getInt(o, i);
		    }
		    return ia;
		}
//  		if ( clsc.getName().equals("byte") ) {
//  			ia[i] = java.lang.reflect.Array.getByte(o, i);
//  		    } else if ( clsc.getName().equals("short") ) {
//  			ia[i] = java.lang.reflect.Array.getShort(o, i);
//  		    } else if ( clsc.getName().equals("int") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getInt(o, i);
//  		    } else if ( clsc.getName().equals("long") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getLong(o, i);
//  		    } else if ( clsc.getName().equals("char") ) {
//  			ia[i] = java.lang.reflect.Array.getChar(o, i);
//  		    } else if ( clsc.getName().equals("double") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getDouble(o, i);
//  		    } else if ( clsc.getName().equals("float") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getFloat(o, i);
//  		    }
//  		}
	    } else if (tclsc.getName().equals("double")) {
		if (clsc.getName().equals("java.lang.String")) {
		    int l = java.lang.reflect.Array.getLength(o);
		    double[] ia = new double[l];
		    String[] sa = (String[]) o;
		    for (int i = 0; i < ia.length; i++) {
			ia[i] = tD(sa[i]);
		    }
		    return ia;
		} else {
		    int l = java.lang.reflect.Array.getLength(o);
		    double[] ia = new double[l];
		    for (int i = 0; i < ia.length; i++) {
			ia[i] = java.lang.reflect.Array.getDouble(o, i);
		    }
		    return ia;
		}
//  		if ( clsc.getName().equals("byte") ) {
//  			ia[i] = java.lang.reflect.Array.getByte(o, i);
//  		    } else if ( clsc.getName().equals("short") ) {
//  			ia[i] = java.lang.reflect.Array.getShort(o, i);
//  		    } else if ( clsc.getName().equals("int") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getInt(o, i);
//  		    } else if ( clsc.getName().equals("long") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getLong(o, i);
//  		    } else if ( clsc.getName().equals("char") ) {
//  			ia[i] = java.lang.reflect.Array.getChar(o, i);
//  		    } else if ( clsc.getName().equals("double") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getDouble(o, i);
//  		    } else if ( clsc.getName().equals("float") ) {
//  			ia[i] = (int)java.lang.reflect.Array.getFloat(o, i);
//  		    }
//  		}
	    } else if (tclsc.getName().equals("char")) {
		int l = java.lang.reflect.Array.getLength(o);
		char[] ia = new char[l];
		for (int i = 0; i < ia.length; i++) {
		    if (clsc.getName().equals("byte")) {
			ia[i] = (char) java.lang.reflect.Array.getByte(o, i);
		    } else if (clsc.getName().equals("short")) {
			ia[i] = (char) java.lang.reflect.Array.getShort(o, i);
		    } else if (clsc.getName().equals("int")) {
			ia[i] = (char) java.lang.reflect.Array.getInt(o, i);
		    } else if (clsc.getName().equals("long")) {
			ia[i] = (char) java.lang.reflect.Array.getLong(o, i);
		    } else if (clsc.getName().equals("char")) {
			ia[i] = (char) java.lang.reflect.Array.getChar(o, i);
		    } else if (clsc.getName().equals("double")) {
			ia[i] = (char) java.lang.reflect.Array.getDouble(o, i);
		    } else if (clsc.getName().equals("float")) {
			ia[i] = (char) java.lang.reflect.Array.getFloat(o, i);
		    }
		}
		return ia;
	    }
	}
	return null;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    static int scrN = 5;

    public static void setScrambleN(int n) {
	scrN = n;
    }

    public static void scrambleArr(Object a) {
	if (a == null)
	    return;
	Class cls = a.getClass();
	if (cls.isArray()) {
	    Class clsc = cls.getComponentType();
	    if (!clsc.isPrimitive()) {
		Object[] arr = (Object[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			Object t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("int")) {
		int[] arr = (int[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			int t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("long")) {
		long[] arr = (long[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			long t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("char")) {
		char[] arr = (char[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			char t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("byte")) {
		byte[] arr = (byte[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			byte t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("short")) {
		short[] arr = (short[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			short t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("double")) {
		double[] arr = (double[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			double t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	    if (clsc.getName().equals("float")) {
		float[] arr = (float[]) a;
		for (int j = 0; j < scrN; j++)
		    for (int i = 0; i < arr.length; i++) {
			int b = S.rand(arr.length);
			float t = arr[i];
			arr[i] = arr[b];
			arr[b] = t;
		    }
	    }
	}
	return;
    }

    public static String scrambleStr(String s) {
	char[] arr = s.toCharArray();
	scrambleArr(arr);
	return new String(arr);
    }


    /*
	try {
	    Class type = Class.forName("int");
	    int size = 10;
	    Object o = java.lang.reflect.Array.newInstance(type, size);

	    for(int i = 0; i < size; i++) {
		java.lang.reflect.Array.setInt(o, i, i);
	    }
	    ff(o);
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ex " + ex);
	}
    */

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static double tD(String s) {
	try {
	    Double dval = Double.valueOf(s);
	    double d = dval.doubleValue();
	    return d;
	} catch (Exception ex) {
	    return 0.0;
	}
    }

//  // - - - - - - - - - - - - - - - - - - - - - - - - - - -

//      static void sortIntArr(int[] a) throws Exception {
//  	Arrays.sort(a);
//      }

//      static void sortCharArr(char[] a) throws Exception {
//  	Arrays.sort(a);
//      }

//      static void sortByteArr(byte[] a) throws Exception {
//  	Arrays.sort(a);
//      }

//      public static boolean sortArr(Object a) {
//  	if ( a == null )
//  	    return true;
//  	try {
//  	    Class cls = a.getClass();
//  	    if ( cls.isArray() ) {
//  		Class clsc = cls.getComponentType();
//  		if ( !clsc.isPrimitive() ) {
//  		    sortArr(a);
//  		}
//  		if ( clsc.getName().equals("int") ) {
//  		    sortIntArr((int[])a);
//  		}
//  		if ( clsc.getName().equals("char") ) {
//  		    sortCharArr((char[])a);
//  		}
//  		if ( clsc.getName().equals("byte") ) {
//  		    sortByteArr((byte[])a);
//  		}
//  	    }
//  	    return true;
//  	} catch (Exception ex) {
//  	    return false;
//  	}
//      }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static int[] upTo(int a) {
	return fromTo(0, a);
    }

    public static int[] fromTo(int a, int b) {
	int ia[] = new int[b - a];
	int ii = 0;
	for (int i = a; i < b; i++)
	    ia[ii++] = i;
	return ia;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static void swapia(int[] ia, int a, int b) {
	int c = ia[a];
	ia[a] = ia[b];
	ia[b] = c;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    static public class Uniq {
	int max;
	int[] picked;
	int taken = 0;

	public Uniq(int max) {
	    this.max = max;
	    picked = S.upTo(max);
	    taken = 0;
	}

	public synchronized int getNext() {
	    int c = 0;
	    for (; ; ) {
		int ix = S.rand(max);
		if (picked[ix] != -1) {
		    int r = picked[ix];
		    picked[ix] = -1;
		    taken++;
		    return r;
		} else {
		    if (++c > 5) {
			int[] ia = new int[max - taken];
			int ix2 = 0;
			for (int i = 0; i < picked.length; i++)
			    if (picked[i] != -1)
				ia[ix2++] = picked[i];
			picked = ia;
			max = picked.length;
			taken = 0;
//			omega.Context.sout_log.getLogger().info("<" + max + ">");
			return getNext();
		    }
		}
	    }
	}

	public synchronized int[] asIntArray() {
	    if (taken != 0) {
		return null;
	    }
	    int[] ia = new int[max];
	    for (int i = 0; i < ia.length; i++)
		ia[i] = getNext();
	    return ia;
	}

	public synchronized int[] asIntArray(int n) {
	    int[] ia = new int[n];
	    for (int i = 0; i < n; i++)
		ia[i] = getNext();
	    return ia;
	}

    }

    ;

    public static Uniq createUniq(int max) {
	return new S.Uniq(max);
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private static Random randG = new Random();

    static RandGen alt_rand;

    public static void setAltRand(RandGen rg) {
	omega.Context.sout_log.getLogger().info("ERR: " + "### alt");
	alt_rand = rg;
    }

    public static int rand(int a) {
	if (alt_rand != null)
	    return alt_rand.rand_(a);

	int r = 0x7fffffff & randG.nextInt();
	return r % a;
    }

    public static int rand_(int a) {
	int r = 0x7fffffff & randG.nextInt();
	return r % a;
    }

// - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static HashMap flagAsMap(String[] argv) {
	final List argl = Arrays.asList(argv);
	HashMap flag = new HashMap();

	for (int i = 0; i < argl.size(); i++) {
	    String s = (String) argl.get(i);
	    if (s.startsWith("-")) {
		String ss = s.substring(1);
		int ix = ss.indexOf('=');
		if (ix != -1) {
		    String sk = ss.substring(0, ix);
		    String sv = ss.substring(ix + 1);
		    if (sv.indexOf(',') == -1)
			flag.put(sk, sv);
		    else {
			String[] sa = split(sv, ",");
			flag.put(sk, sv);
			flag.put("[S;" + sk, sa);
		    }
		} else {
		    flag.put(ss, "");
		}
	    }
	}
	return flag;
    }

    public static List argAsList(String[] argv) {
	List argl = new LinkedList(Arrays.asList(argv));

	for (int i = 0; i < argl.size(); i++) {
	    String s = (String) argl.get(i);
	    if (s.startsWith("-")) {
		argl.remove(i);
		i--;
	    }
	}
	return argl;
    }
}

