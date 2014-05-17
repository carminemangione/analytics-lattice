package analattice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnSetTest {

    @Test
    public void column() throws Exception {
        ColumnSet<String> columnSet = ColumnSet.of("a", "b", "c");
        assertEquals("a", columnSet.column(0));
        assertEquals("b", columnSet.column(1));
        assertEquals("c", columnSet.column(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void negativeColumnIndex() throws Exception {
        ColumnSet.of("a").column(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexTooLarge() throws Exception {
        ColumnSet.of("a").column(1);
    }

    @Test
    public void columnIndex() throws Exception {
        ColumnSet<String> columnSet = ColumnSet.of("a", "b", "c");
        assertEquals(0, columnSet.columnIndex("a"));
        assertEquals(1, columnSet.columnIndex("b"));
        assertEquals(2, columnSet.columnIndex("c"));
    }
}
