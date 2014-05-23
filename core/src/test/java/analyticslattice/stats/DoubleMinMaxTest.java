package analyticslattice.stats;

import analyticslattice.stats.minmax.DoubleMinMax;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleMinMaxTest {

    @Test
    public void noValues() throws Exception {
        DoubleMinMax minMax = new DoubleMinMax();
        assertFalse(minMax.hasSeenAny());
        assertNull(minMax.getMin());
        assertNull(minMax.getMax());
    }

    @Test
    public void someValuesNothingCrazy() throws Exception {
        DoubleMinMax minMax = new DoubleMinMax();
        minMax.visit(0.0);
        minMax.visit(1.0);
        minMax.visit(-1.0);
        assertTrue(minMax.hasSeenAny());
        assertEquals(-1.0, minMax.getMin(), 0.0);
        assertEquals(1.0, minMax.getMax(), 0.0);
    }

    @Test
    public void nanIsNotAValue() throws Exception {
        DoubleMinMax minMax = new DoubleMinMax();
        minMax.visit(Double.NaN);
        assertFalse(minMax.hasSeenAny());
        minMax.visit(0.0);
        assertTrue(minMax.hasSeenAny());
        assertEquals(0.0, minMax.getMin(), 0.0);
        assertEquals(0.0, minMax.getMax(), 0.0);
    }

    @Test
    public void infinitesAreValues() throws Exception {
        DoubleMinMax minMax = new DoubleMinMax();
        minMax.visit(Double.NaN);
        minMax.visit(Double.NEGATIVE_INFINITY);
        minMax.visit(Double.POSITIVE_INFINITY);
        minMax.visit(0.0);
        assertTrue(minMax.hasSeenAny());
        assertEquals(Double.NEGATIVE_INFINITY, minMax.getMin(), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, minMax.getMax(), 0.0);
    }

}
