package analyticslattice.stats.minmax;

public class DoubleMinMax {

    public static DoubleMinMax fromPrevious(Double currentMin, Double currentMax) {
        if ((currentMin == null ^ currentMax == null)) {
            throw new IllegalArgumentException("either both currentMin and currentMax can be null or neither can be null");
        }
        if (currentMin != null) {
            if (Double.isNaN(currentMin)) {
                throw new IllegalArgumentException("cannot construct with currentMin=nan");
            }
            if (Double.isNaN(currentMax)) {
                throw new IllegalArgumentException("cannot construct with currentMax=nan");
            }
            if (currentMin > currentMax) {
                throw new IllegalArgumentException("currentMin should be less than currentMax");
            }
        }
        return new DoubleMinMax(currentMin, currentMax);
    }

    private Double min = null;
    private Double max = null;

    public DoubleMinMax() {
        this(null, null);
    }

    protected DoubleMinMax(Double currentMin, Double currentMax) {
        this.min = currentMin;
        this.max = currentMax;
    }

    public void visit(double value) {
        if (!Double.isNaN(value)) {
            if (min == null) {
                min = value;
                max = value;
            } else {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }
        }
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public boolean hasSeenAny() {
        return min != null;
    }
}
