package analyticslattice.stats.histogram;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

public class IntegerHistogram extends Histogram<Integer> {

    public IntegerHistogram() {
        this(Histogram.DEFAULT_SIZE);
    }

    public IntegerHistogram(int maxDistinctValues) {
        super(maxDistinctValues);
        //reserve two slots for 0 and 1
        histogram.put(0, 0);
        histogram.put(0, 1);
    }

    @Override
    public Set<Integer> elements() {
        Set<Integer> elements = histogram.keySet();
        int countZero = countOf(0);
        int countOne = countOf(1);
        if (countZero == 0 || countOne == 0) {
            ImmutableSet.Builder<Integer> excludeThese = ImmutableSet.builder();
            if (countZero == 0) {
                excludeThese.add(0);
            }
            if (countOne == 0) {
                excludeThese.add(1);
            }
            elements = Sets.difference(elements, excludeThese.build());
        }
        return ImmutableSet.copyOf(elements);
    }
}
