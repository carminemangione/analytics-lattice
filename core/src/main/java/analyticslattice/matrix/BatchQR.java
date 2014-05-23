package analyticslattice.matrix;

import no.uib.cipr.matrix.*;

public class BatchQR {

    private final int numColumns;
    private final DenseMatrix m;
    private final QR qr;
    private int currentRow;
    private final DenseMatrix r;


    public BatchQR(int numColumns, int aspectRatio) {
        if(numColumns < 1){
            throw new IllegalArgumentException("must have numColums of 1 or greater");
        }
        if(aspectRatio < 1){
            throw new IllegalArgumentException("must have aspect ratio of 1 or greater, preferably 4 or greater");
        }
        this.numColumns = numColumns;
        this.m = new DenseMatrix(numColumns * aspectRatio + numColumns, numColumns);
        this.r = new DenseMatrix(numColumns, numColumns);
        this.qr = new QR(m.numRows(), m.numColumns());
        this.currentRow = numColumns;
    }

    public void append(DenseVector row){
        if(row.size() != numColumns){
            throw new IllegalArgumentException(String.format("observed row must have %d entries", numColumns));
        }
        for (VectorEntry entry : row) {
            m.set(currentRow, entry.index(), entry.get());
        }
        currentRow++;
        if(currentRow == m.numRows()){
            performQRThenSetM();
        }
    }

    public DenseMatrix getR(){
        if(currentRow != numColumns){
            performQRThenSetM();
        }
        return r;
    }

    private void performQRThenSetM() {
        currentRow = numColumns;
        qr.factor(m);
        r.zero();
        r.set(qr.getR());
        m.zero();
        for (MatrixEntry entry : r) {
            m.set(entry.row(), entry.column(), entry.get());
        }
    }


}
