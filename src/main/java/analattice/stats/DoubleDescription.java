package analattice.stats;

public class DoubleDescription {

    private final DoubleMinMax minMax = new DoubleMinMax();
    private final MeanStddev meanStddev = new MeanStddev();
    private int countObserved = 0;
    private int countZero = 0;
    private int countOne = 0;
    private int countNull = 0;
    private int countNan = 0;

    public DoubleDescription() {
    }

    public void observe(Double x){
        countObserved++;
        if(x == null){
            countNull++;
        }else if(Double.isNaN(x)){
            countNan++;
        }else{
            if(x == 0.0){
                countZero++;
            }else if(x == 1.0){
                countOne++;
            }
            minMax.observe(x);
            meanStddev.observe(x);
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
}
