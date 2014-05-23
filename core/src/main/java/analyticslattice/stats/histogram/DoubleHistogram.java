package analyticslattice.stats.histogram;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

public class DoubleHistogram extends Histogram<Double> {

    public DoubleHistogram() {
        this(Histogram.DEFAULT_SIZE);
    }

    public DoubleHistogram(int maxDistinctValues) {
        super(maxDistinctValues);
        //three slots are reserved for this stuff
        histogram.put(0.0, 0);
        histogram.put(1.0, 0);
        histogram.put(Double.NaN, 0);
    }

    @Override
    public Set<Double> elements() {
        Set<Double> elements = histogram.keySet();
        int countZero = countOf(0.0);
        int countOne = countOf(1.0);
        int countNan = countOf(Double.NaN);
        if(countZero == 0 || countOne == 0 || countNan == 0){
            ImmutableSet.Builder<Double> excludeThese = ImmutableSet.builder();
            if(countZero == 0){
                excludeThese.add(0.0);
            }
            if(countOne == 0){
                excludeThese.add(1.0);
            }
            if(countNan == 0){
                excludeThese.add(Double.NaN);
            }
            elements = Sets.difference(elements, excludeThese.build());
        }
        return ImmutableSet.copyOf(elements);
    }
}
