package analattice.stats.description;

import analattice.stats.MeanStddev;
import analattice.stats.minmax.DoubleMinMax;

public class DoubleDescription implements PrintableDescription {

    private final DoubleMinMax minMax = new DoubleMinMax();
    private final MeanStddev meanStddev = new MeanStddev();
    private int countObserved = 0;
    private int countZero = 0;
    private int countOne = 0;
    private int countNull = 0;
    private int countNan = 0;

    public DoubleDescription() {
    }

    public void visit(Double x) {
        countObserved++;
        if (x == null) {
            countNull++;
        } else if (Double.isNaN(x)) {
            countNan++;
        } else {
            if (x == 0.0) {
                countZero++;
            } else if (x == 1.0) {
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

    public int getCountNan() {
        return countNan;
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

    @Override
    public String header() {
        return "countTotal\tcountZero\tcountOne\tcountNull\tcountNan\t" +
                "min\tmax\tmean\tvariance\tstddev";
    }


    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(countObserved).append("\t")
                .append(countZero).append("\t")
                .append(countOne).append("\t")
                .append(countNull).append("\t")
                .append(countNan).append("\t")

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
