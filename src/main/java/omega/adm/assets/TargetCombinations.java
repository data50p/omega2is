package omega.adm.assets;

import omega.lesson.machine.Target;

import java.util.*;

/**
 * Created by lars on 2017-02-12.
 */
public class TargetCombinations {

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
    	    if ( as_one_cache != null )
    	        return as_one_cache;

    	    TargetCombinations as_one = new TargetCombinations();
    	    for(TargetCombinations tc : bundle) {
    	        as_one.merge(tc);
	    }
	    as_one_cache = as_one;
	    return as_one;
	}

	public int srcSize() {
    	    return asOne().src_set.size();
	}
    }

    public Set<String> src_set = new HashSet<>();
    public Set<String> dep_set = new HashSet<>();

    private void merge(TargetCombinations tc) {
	dep_set.addAll(tc.dep_set);
	src_set.addAll(tc.src_set);
    }
}
