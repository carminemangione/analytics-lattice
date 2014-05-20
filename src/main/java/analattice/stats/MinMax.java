package analattice.stats;

public class MinMax {

    public static MinMax make() {
        return new MinMax(null, null);
    }

    public static MinMax fromPrevious(Double currentMin, Double currentMax) {
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
        return new MinMax(currentMin, currentMax);
    }

    private Double min = null;
    private Double max = null;

    protected MinMax(Double currentMin, Double currentMax) {

        this.min = currentMin;
        this.max = currentMax;
    }

    public void observe(double value) {
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
