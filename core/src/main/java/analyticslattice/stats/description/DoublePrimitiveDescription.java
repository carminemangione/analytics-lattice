package analyticslattice.stats.description;

import analyticslattice.stats.MeanStddev;
import analyticslattice.stats.histogram.DoubleHistogram;
import analyticslattice.stats.histogram.Histogram;
import analyticslattice.stats.minmax.DoubleMinMax;

public class DoublePrimitiveDescription implements PrintableDescription {

    private final DoubleMinMax minMax = new DoubleMinMax();
    private final MeanStddev meanStddev = new MeanStddev();
    private final DoubleHistogram histogram;

    public DoublePrimitiveDescription() {
        this(Histogram.DEFAULT_SIZE);
    }

    public DoublePrimitiveDescription(int histogramMaxDistinctValues) {
        this.histogram = new DoubleHistogram(histogramMaxDistinctValues);
    }

    public void visit(double x) {
        histogram.visit(x);
        minMax.visit(x);
        meanStddev.visit(x);
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
        return "totalCount\tcountZero\tcountOne\tcountNan\tmin\t" +
                "max\tmean\tvariance\tstddev";
    }


    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(getTotalCount()).append("\t")
                .append(getCountZero()).append("\t")
                .append(getCountOne()).append("\t")
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
