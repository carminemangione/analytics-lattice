package analyticslattice.stats.histogram;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Histogram<E> {

    public static final int DEFAULT_SIZE = 200;

    protected final Map<E, Integer> histogram = new HashMap<>();
    protected final int maxDistinctValues;
    protected int countOverflow = 0;
    protected int totalCount = 0;
    protected int countNull = 0;


    public Histogram() {
        this(DEFAULT_SIZE);
    }

    public Histogram(int maxDistinctValues) {
        if (maxDistinctValues < 10) {
            throw new IllegalArgumentException("maxDistinctValues must be at least 10");
        }
        this.maxDistinctValues = maxDistinctValues;
    }

    public void visit(E e) {
        if (e == null) {
            countNull++;
        } else {
            Integer count = histogram.get(e);
            if (count == null) {
                if (histogram.size() == maxDistinctValues) {
                    countOverflow++;
                } else {
                    histogram.put(e, 1);
                }
            } else {
                histogram.put(e, count + 1);
            }
        }
        totalCount++;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean hasOverflowed() {
        return countOverflow > 0;
    }

    public int getCountOverflow() {
        return countOverflow;
    }

    /**
     * Does not include null
     */
    public Set<E> elements() {
        return ImmutableSet.copyOf(histogram.keySet());
    }

    public int countOf(E e) {
        int toReturn;
        if (e == null) {
            toReturn = countNull;
        } else {
            Integer count = histogram.get(e);
            toReturn = count == null ? 0 : count;
        }
        return toReturn;
    }
}
