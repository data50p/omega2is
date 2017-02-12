package omega.adm.assets;

import omega.lesson.machine.Target;

import java.util.*;

/**
 * Created by lars on 2017-02-12.
 */
public class TargetCombinations {

    public static class Builder {
    	List<TargetCombinations> bundle = new ArrayList<>();

    	public Builder() {
	}

	public void add(TargetCombinations tc) {
    	    bundle.add(tc);
	}

	public TargetCombinations asOne() {
    	    TargetCombinations as_one = new TargetCombinations();
    	    for(TargetCombinations tc : bundle) {
    	        as_one.merge(tc);
	    }
	    return as_one;
	}
    }

    private void merge(TargetCombinations tc) {
	dep_set.addAll(tc.dep_set);
	src_set.addAll(tc.src_set);
    }

    public Set<String> src_set = new HashSet<>();
    public Set<String> dep_set = new HashSet<>();
}
