package analattice.stats;

import analattice.matrix.BatchQR;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;

public class MeanStddev {

    private final BatchQR batchQR;
    private final DenseVector sharedRow = new DenseVector(2);
    private boolean dirty = false;
    private Double mean;
    private Double stddev;

    public MeanStddev() {
        this(20);
    }

    public MeanStddev(int aspectRatio) {
        this.batchQR = new BatchQR(2, aspectRatio);
        this.sharedRow.set(0, 1);
    }

    public void visit(Double x) {
        if (x != null)
            visit(x.doubleValue());
    }

    public void visit(double x) {
        if (!Double.isNaN(x)) {
            dirty = true;
            sharedRow.set(1, x);
            batchQR.append(sharedRow);
        }
    }

    public void visit(Integer x) {
        if (x != null) {
            visit(x.intValue());
        }
    }

    public void visit(int x) {
        dirty = true;
        sharedRow.set(1, x);
        batchQR.append(sharedRow);
    }


    public Double getMean() {
        updateIfDirty();
        return mean;
    }

    public Double getStddev() {
        updateIfDirty();
        return stddev;
    }

    public Double getVariance() {
        updateIfDirty();
        return stddev == null ? null : stddev * stddev;
    }

    private void updateIfDirty() {
        if (dirty) {
            DenseMatrix r = batchQR.getR();
            double sqrtN = r.get(0, 0);
            mean = r.get(0, 1) / sqrtN;
            stddev = r.get(1, 1) / sqrtN;
        }
    }
}
