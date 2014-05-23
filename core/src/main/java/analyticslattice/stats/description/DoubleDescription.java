package analyticslattice.stats.description;

import analyticslattice.stats.histogram.DoubleHistogram;
import analyticslattice.stats.histogram.Histogram;
import analyticslattice.stats.MeanStddev;
import analyticslattice.stats.minmax.DoubleMinMax;

public class DoubleDescription implements PrintableDescription {

    private final DoubleMinMax minMax = new DoubleMinMax();
    private final MeanStddev meanStddev = new MeanStddev();
    private final DoubleHistogram histogram;


    public DoubleDescription() {
        this(Histogram.DEFAULT_SIZE);
    }

    public DoubleDescription(int histogramMaxDistinctValues) {
        this.histogram = new DoubleHistogram(histogramMaxDistinctValues);
    }

    public void visit(Double x) {
        if(x != null && !Double.isNaN(x)){
            minMax.visit(x);
            meanStddev.visit(x);
        }
        histogram.visit(x);
    }

    public int getTotalCount() {
        return histogram.getTotalCount();
    }

    public int getCountZero() {
        return histogram.countOf(0.0);
    }

    public int getCountOne() {
        return histogram.countOf(1.0);
    }

    public int getCountNull() {
        return histogram.countOf(null);
    }

    public int getCountNan() {
        return histogram.countOf(Double.NaN);
    }

    public Double getMin() {
        return minMax.getMin();
    }

    public Double getMax() {
        return minMax.getMax();
    }

    public Double getMean() {
        return meanStddev.getMean();
    }

    public Double getVariance() {
        return meanStddev.getVariance();
    }

    public Double getStddev() {
        return meanStddev.getStddev();
    }

    public DoubleMinMax getMinMax() {
        return minMax;
    }

    public MeanStddev getMeanStddev() {
        return meanStddev;
    }

    public DoubleHistogram getHistogram() {
        return histogram;
    }

    @Override
    public String header() {
        return "totalCount\tcountZero\tcountOne\tcountNull\tcountNan\t" +
                "min\tmax\tmean\tvariance\tstddev";
    }


    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(getTotalCount()).append("\t")
                .append(getCountZero()).append("\t")
                .append(getCountOne()).append("\t")
                .append(getCountNull()).append("\t")
                .append(getCountNan()).append("\t")

                .append(getMin()).append("\t")
                .append(getMax()).append("\t")
                .append(getMean()).append("\t")
                .append(getVariance()).append("\t")
                .append(getStddev());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(header()).append("\n");
        toString(stringBuilder);
        return stringBuilder.toString();
    }
}
