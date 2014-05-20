package analattice.matrix;

import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BatchQRTest {

    @Test(expected = IllegalArgumentException.class)
    public void needPositiveAspectRatio() throws Exception {
        new BatchQR(4, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void needPositiveColumnCount() throws Exception {
        new BatchQR(0, 4);
    }

    @Test
    public void computeMeanStddev() throws Exception {
        BatchQR batchQR = new BatchQR(2, 4);
        DenseVector updateRow = new DenseVector(2);

        double sum = 0;
        int numObserved = 10000;
        for (int i = 1; i <= numObserved; i++) {
            updateRow.set(0, 1);
            updateRow.set(1, i);
            batchQR.append(updateRow);
            sum += i;
        }
        double mean = sum/numObserved;
        double sumSquaredError = 0.0;
        for (int i = 1; i <= numObserved; i++) {
            double error = mean - i;
            sumSquaredError += error * error;
        }
        double variance = sumSquaredError / numObserved;
        double stddev = Math.sqrt(variance);


        DenseMatrix r = batchQR.getR();
        assertEquals(Math.sqrt(numObserved), r.get(0, 0), 1.0e-6);
        assertEquals(mean * Math.sqrt(numObserved), r.get(0, 1), 1.0e-6);
        assertEquals(0.0, r.get(1, 0), 0.0);
        assertEquals(stddev * Math.sqrt(numObserved), r.get(1, 1), 1.0e-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rowNotTheRightSize() throws Exception {
        new BatchQR(4, 2).append(new DenseVector(3));
    }
}
