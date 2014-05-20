package analattice.stats;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DoubleDescriptionTest {

    @Test
    public void noValues() throws Exception {
        DoubleDescription description = new DoubleDescription();
        assertEquals(0, description.getCountObserved());
        assertEquals(0, description.getCountNan());
        assertEquals(0, description.getCountNull());
        assertEquals(0, description.getCountZero());
        assertEquals(0, description.getCountOne());
        assertNull(description.getMin());
        assertNull(description.getMax());
        assertNull(description.getMean());
        assertNull(description.getStddev());
        assertNull(description.getVariance());
    }

    @Test
    public void grabBag() throws Exception {
        DoubleDescription description = new DoubleDescription();
        description.observe(1.0);
        description.observe(0.0);
        description.observe(0.0);
        description.observe(-0.1);
        description.observe(1.3);
        description.observe(null);
        description.observe(null);
        description.observe(Double.NaN);

        assertEquals(8, description.getCountObserved());
        assertEquals(1, description.getCountNan());
        assertEquals(2, description.getCountNull());
        assertEquals(2, description.getCountZero());
        assertEquals(1, description.getCountOne());
        assertEquals(1.3, description.getMax(), 0.0);
        assertEquals(-0.1, description.getMin(), 0.0);
        assertEquals(0.44, description.getMean(), 1.0e-6);

        double expectedVariance = (Math.pow(1.0-0.44, 2) +
                Math.pow(0.44, 2) +
                Math.pow(0.44, 2) +
                Math.pow(0.44 + 0.1, 2) +
                Math.pow(1.3 - 0.44, 2))/5;
        assertEquals(expectedVariance, description.getVariance(), 1.0e-6);

        double expectedStddev = Math.sqrt(expectedVariance);
        assertEquals(expectedStddev, description.getStddev(), 1.0e-6);
    }
}
