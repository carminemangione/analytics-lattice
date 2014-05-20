package analattice.stats.description;

import analattice.stats.MeanStddev;
import analattice.stats.minmax.IntegerMinMax;

public class IntegerDescription implements PrintableDescription {

    private final IntegerMinMax minMax = new IntegerMinMax();
    private final MeanStddev meanStddev = new MeanStddev();
    private int countObserved = 0;
    private int countZero = 0;
    private int countOne = 0;
    private int countNull = 0;

    public IntegerDescription() {
    }

    public void visit(Integer x) {
        countObserved++;
        if (x == null) {
            countNull++;
        } else {
            if (x == 0) {
                countZero++;
            } else if (x == 1) {
                countOne++;
            }
            minMax.visit(x);
            meanStddev.visit(x);
        }
    }

    public int getCountObserved() {
        return countObserved;
    }

    public int getCountZero() {
        return countZero;
    }

    public int getCountOne() {
        return countOne;
    }

    public int getCountNull() {
        return countNull;
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

    @Override
    public String header() {
        return "countTotal\tcountZero\tcountOne\tcountNull\tmin\t" +
                "max\tmean\tvariance\tstddev";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(countObserved).append("\t")
                .append(countZero).append("\t")
                .append(countOne).append("\t")
                .append(countNull).append("\t")
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
