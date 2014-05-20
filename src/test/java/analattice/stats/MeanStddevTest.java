package analattice.stats;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MeanStddevTest {

    @Test
    public void noValues() throws Exception {
        MeanStddev meanAndStddev = new MeanStddev();
        assertNull(meanAndStddev.getMean());
        assertNull(meanAndStddev.getStddev());
        assertNull(meanAndStddev.getVariance());
    }

    @Test
    public void singleValue() throws Exception {
        MeanStddev meanAndStddev = new MeanStddev();
        meanAndStddev.visit(10.0);
        assertEquals(10.0, meanAndStddev.getMean(), 1.0e-6);
        assertEquals(0.0, meanAndStddev.getStddev(), 1.0e-6);
        assertEquals(0.0, meanAndStddev.getVariance(), 1.0e-6);
    }

    @Test
    public void grabBag() throws Exception {
        MeanStddev meanAndStddev = new MeanStddev();
        int numObservations = 10000;
        double sum = 0.0;
        for (int i = 1; i <= numObservations; i++) {
            sum += i;
            meanAndStddev.visit((double) i);
            meanAndStddev.visit(Double.NaN);
        }
        double expectedMean = sum / numObservations;
        double sumSquaredError = 0.0;
        for (int i = 1; i <= numObservations; i++) {
            double error = expectedMean - i;
            sumSquaredError += error * error;
        }
        double expectedVariance = sumSquaredError/ numObservations;
        double expectedStddev = Math.sqrt(expectedVariance);

        assertEquals(expectedMean, meanAndStddev.getMean(), 1.0e-6);
        assertEquals(expectedStddev, meanAndStddev.getStddev(), 1.0e-6);
        assertEquals(expectedVariance, meanAndStddev.getVariance(), 1.0e-6);
    }

    @Test
    public void positiveInfinites() throws Exception {
        MeanStddev meanAndStddev = new MeanStddev();
        meanAndStddev.visit(1.0);
        meanAndStddev.visit(2.0);
        meanAndStddev.visit(3.0);
        meanAndStddev.visit(Double.POSITIVE_INFINITY);
        meanAndStddev.visit(Double.POSITIVE_INFINITY);
        meanAndStddev.visit(Double.POSITIVE_INFINITY);

        assertEquals(Double.POSITIVE_INFINITY, meanAndStddev.getMean(), 0.0);
        assertEquals(Double.NaN, meanAndStddev.getStddev(), 1.0e-6);
        assertEquals(Double.NaN, meanAndStddev.getVariance(), 1.0e-6);
    }

    @Test
    public void negativeInfinites() throws Exception {
        MeanStddev meanAndStddev = new MeanStddev();
        meanAndStddev.visit(1.0);
        meanAndStddev.visit(2.0);
        meanAndStddev.visit(3.0);
        meanAndStddev.visit(Double.NEGATIVE_INFINITY);
        meanAndStddev.visit(Double.NEGATIVE_INFINITY);
        meanAndStddev.visit(Double.NEGATIVE_INFINITY);

        assertEquals(Double.NEGATIVE_INFINITY, meanAndStddev.getMean(), 0.0);
        assertEquals(Double.NaN, meanAndStddev.getStddev(), 1.0e-6);
        assertEquals(Double.NaN, meanAndStddev.getVariance(), 1.0e-6);
    }

    @Test
    public void somePositiveSomeNegativeInfinites() throws Exception {
        MeanStddev meanAndStddev = new MeanStddev();
        meanAndStddev.visit(1.0);
        meanAndStddev.visit(2.0);
        meanAndStddev.visit(3.0);
        meanAndStddev.visit(Double.POSITIVE_INFINITY);
        meanAndStddev.visit(Double.NEGATIVE_INFINITY);
        meanAndStddev.visit(Double.NEGATIVE_INFINITY);

        assertEquals(Double.NaN, meanAndStddev.getMean(), 0.0);
        assertEquals(Double.NaN, meanAndStddev.getStddev(), 1.0e-6);
        assertEquals(Double.NaN, meanAndStddev.getVariance(), 1.0e-6);
    }
}
