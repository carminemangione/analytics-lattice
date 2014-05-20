package analattice.stats;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinMaxTest {

    @Test
    public void noValues() throws Exception {
        MinMax minMax = MinMax.make();
        assertFalse(minMax.hasSeenAny());
        assertNull(minMax.getMin());
        assertNull(minMax.getMax());
    }

    @Test
    public void someValuesNothingCrazy() throws Exception {
        MinMax minMax = MinMax.make();
        minMax.observe(0.0);
        minMax.observe(1.0);
        minMax.observe(-1.0);
        assertTrue(minMax.hasSeenAny());
        assertEquals(-1.0, minMax.getMin(), 0.0);
        assertEquals(1.0, minMax.getMax(), 0.0);
    }

    @Test
    public void nanIsNotAValue() throws Exception {
        MinMax minMax = MinMax.make();
        minMax.observe(Double.NaN);
        assertFalse(minMax.hasSeenAny());
        minMax.observe(0.0);
        assertTrue(minMax.hasSeenAny());
        assertEquals(0.0, minMax.getMin(), 0.0);
        assertEquals(0.0, minMax.getMax(), 0.0);
    }

    @Test
    public void infinitesAreValues() throws Exception {
        MinMax minMax = MinMax.make();
        minMax.observe(Double.NaN);
        minMax.observe(Double.NEGATIVE_INFINITY);
        minMax.observe(Double.POSITIVE_INFINITY);
        minMax.observe(0.0);
        assertTrue(minMax.hasSeenAny());
        assertEquals(Double.NEGATIVE_INFINITY, minMax.getMin(), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, minMax.getMax(), 0.0);
    }

}
