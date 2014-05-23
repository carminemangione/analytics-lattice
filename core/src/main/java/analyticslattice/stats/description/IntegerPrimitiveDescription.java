package analyticslattice.stats.description;

import analyticslattice.stats.MeanStddev;
import analyticslattice.stats.histogram.Histogram;
import analyticslattice.stats.histogram.IntegerHistogram;
import analyticslattice.stats.minmax.IntegerMinMax;

public class IntegerPrimitiveDescription implements PrintableDescription{

    private final IntegerMinMax minMax = new IntegerMinMax();
    private final MeanStddev meanStddev = new MeanStddev();
    private final IntegerHistogram histogram;

    public IntegerPrimitiveDescription() {
        this(Histogram.DEFAULT_SIZE);
    }

    public IntegerPrimitiveDescription(int histogramMaxDistinctValues) {
        histogram = new IntegerHistogram(histogramMaxDistinctValues);
    }


    public void visit(int x) {
        minMax.visit(x);
        meanStddev.visit(x);
        histogram.visit(x);
    }

    public int getTotalCount() {
        return histogram.getTotalCount();
    }

    public int getCountZero() {
        return histogram.countOf(0);
    }

    public int getCountOne() {
        return histogram.countOf(1);
    }

    public Integer getMin() {
        return minMax.getMin();
    }

    public Integer getMax() {
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

    public IntegerMinMax getMinMax() {
        return minMax;
    }

    public MeanStddev getMeanStddev() {
        return meanStddev;
    }

    public IntegerHistogram getHistogram() {
        return histogram;
    }

    @Override
    public String header() {
        return "totalCount\tcountZero\tcountOne\tmin\tmax\t" +
                "mean\tvariance\tstddev";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(getTotalCount()).append("\t")
                .append(getCountZero()).append("\t")
                .append(getCountOne()).append("\t")
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
