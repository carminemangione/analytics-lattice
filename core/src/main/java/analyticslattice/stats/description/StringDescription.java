package analyticslattice.stats.description;

import analyticslattice.stats.histogram.Histogram;
import analyticslattice.stats.histogram.IntegerHistogram;
import analyticslattice.stats.histogram.StringHistogram;
import analyticslattice.stats.minmax.IntegerMinMax;

public class StringDescription implements PrintableDescription {

    private final StringHistogram histogram;
    private final IntegerHistogram lengthHistogram;
    private final IntegerMinMax lengthMinMax = new IntegerMinMax();

    public StringDescription() {
        this(Histogram.DEFAULT_SIZE, Histogram.DEFAULT_SIZE);
    }

    public StringDescription(int histogramMaxDistinctValues, int lengthHistogramMaxDistinctValues) {
        this.histogram = new StringHistogram(histogramMaxDistinctValues);
        this.lengthHistogram = new IntegerHistogram(lengthHistogramMaxDistinctValues);
    }

    public void visit(String x){
        histogram.visit(x);
        if(x != null){
            int length = x.length();
            lengthHistogram.visit(length);
            lengthMinMax.visit(length);
        }
    }

    public int getTotalCount(){
        return histogram.getTotalCount();
    }

    public int getCountNull(){
        return histogram.countOf(null);
    }

    public int getCountEmpty(){
        return histogram.countOf("");
    }

    public StringHistogram getHistogram() {
        return histogram;
    }

    public IntegerHistogram getLengthHistogram() {
        return lengthHistogram;
    }

    public IntegerMinMax getLengthMinMax() {
        return lengthMinMax;
    }

    @Override
    public String header() {
        return "totalCount\tcountEmpty\tcountNull\tminLength\tmaxLength";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(getTotalCount()).append("\t")
                .append(getCountEmpty()).append("\t")
                .append(getCountNull()).append("\t")
                .append(lengthMinMax.getMin()).append("\t")
                .append(lengthMinMax.getMax());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(header()).append("\n");
        toString(stringBuilder);
        return stringBuilder.toString();
    }
}
