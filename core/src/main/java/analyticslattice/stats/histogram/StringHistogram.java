package analyticslattice.stats.histogram;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

public class StringHistogram extends Histogram<String> {

    public StringHistogram() {
        this(Histogram.DEFAULT_SIZE);
    }

    public StringHistogram(int maxDistinctValues) {
        super(maxDistinctValues);
        //three slots are reserved for this stuff
        histogram.put("", 0);
    }

    @Override
    public Set<String> elements() {
        Set<String> elements = histogram.keySet();
        int countEmpty = countOf("");
        if(countEmpty == 0){
            elements = Sets.difference(elements, ImmutableSet.of(""));
        }
        return ImmutableSet.copyOf(elements);
    }

}
