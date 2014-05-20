package analattice.stats.minmax;

public class IntegerMinMax {

    public static IntegerMinMax fromPrevious(Integer currentMin, Integer currentMax) {
        if ((currentMin == null ^ currentMax == null)) {
            throw new IllegalArgumentException("either both currentMin and currentMax can be null or neither can be null");
        }
        if (currentMin != null) {
            if (currentMin > currentMax) {
                throw new IllegalArgumentException("currentMin should be less than currentMax");
            }
        }
        return new IntegerMinMax(currentMin, currentMax);
    }

    private Integer min = null;
    private Integer max = null;

    public IntegerMinMax() {
        this(null, null);
    }

    protected IntegerMinMax(Integer currentMin, Integer currentMax) {
        this.min = currentMin;
        this.max = currentMax;
    }

    public void visit(int value) {
        if (min == null) {
            min = value;
            max = value;
        } else {
            min = Math.min(min.intValue(), value);
            max = Math.max(max, value);
        }
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public boolean hasSeenAny() {
        return min != null;
    }

}
