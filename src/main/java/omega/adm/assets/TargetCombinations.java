package omega.adm.assets;

import omega.lesson.machine.Target;

import java.util.*;

/**
 * Created by lars on 2017-02-12.
 */
public class TargetCombinations {

    public static class TCItem {
        public String fn;
        public Boolean exist;
        public String originalExtention;

        public TCItem(String fn, boolean exist) {
            this.fn = fn;
            this.exist = exist;
            if ( fn.contains(",") )
                System.err.println("MORE using ,");
            if ( ! fn.contains("/") )
                System.err.println("LESS using /");
        }

        public boolean equals(Object o) {
            if ( o == null )
                return false;
            if ( ! (o instanceof TCItem) )
                return false;
            if (((TCItem) o).fn.equals(fn))
                return true;
            else
                return false;
        }

        public int hashCode() {
            return fn.hashCode();
        }
    }

    public static class Builder {
        List<TargetCombinations> bundle = new ArrayList<>();
        TargetCombinations as_one_cache = null;

        public Builder() {
        }

        public void add(TargetCombinations tc) {
            bundle.add(tc);
            as_one_cache = null;
        }

        public TargetCombinations asOne() {
            if (as_one_cache != null)
                return as_one_cache;

            TargetCombinations as_one = new TargetCombinations();
            for (TargetCombinations tc : bundle) {
                as_one.merge(tc);
            }
            as_one_cache = as_one;
            return as_one;
        }

        public int srcSize() {
            return asOne().src_set.size();
        }
    }

    public Set<TCItem> src_set = new HashSet<>();
    public Set<TCItem> dep_set = new HashSet<>();

    private void merge(TargetCombinations tc) {
        dep_set.addAll(tc.dep_set);
        src_set.addAll(tc.src_set);
    }
}
