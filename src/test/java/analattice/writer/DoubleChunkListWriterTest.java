package analattice.writer;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoubleChunkListWriterTest {

    @Test(expected = IllegalStateException.class)
    public void cannotGetChunksWhenOpen() throws Exception {
        new DoubleChunkListWriter().getChunks();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAppendWhenClosed() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter();
        writer.close();
        writer.append();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotSetWhenClosed() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter();
        writer.append();
        writer.close();
        writer.set(1.0);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotSetBeforeFirstAppend() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter();
        writer.set(1.0);
    }

    @Test
    public void noChunks() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter();
        writer.close();
        List<double[]> chunks = writer.getChunks();
        assertTrue(chunks.isEmpty());
    }

    @Test
    public void oneFullChunk() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter(4);
        for (int i = 0; i < 4; i++) {
            writer.append();
            writer.set((double) i);
        }
        writer.close();

        List<double[]> chunks = writer.getChunks();
        assertEquals(1, chunks.size());

        double[] firstChunk = chunks.get(0);
        assertArrayEquals(new double[]{0.0, 1.0, 2.0, 3.0}, firstChunk, 0.0);
    }

    @Test
    public void oneNotFullChunk() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter(4);
        for (int i = 0; i < 2; i++) {
            writer.append();
            writer.set((double) i);
        }
        writer.close();

        List<double[]> chunks = writer.getChunks();
        assertEquals(1, chunks.size());

        double[] firstChunk = chunks.get(0);
        assertArrayEquals(new double[]{0.0, 1.0}, firstChunk, 0.0);
    }

    @Test
    public void coupleChunks() throws Exception {
        DoubleChunkListWriter writer = new DoubleChunkListWriter(4);
        for (int i = 0; i < 10; i++) {
            writer.append();
            writer.set((double) i);
        }
        writer.close();

        List<double[]> chunks = writer.getChunks();
        assertEquals(3, chunks.size());

        double[] firstChunk = chunks.get(0);
        assertArrayEquals(new double[]{0.0, 1.0, 2.0, 3.0}, firstChunk, 0.0);

        double[] secondChunk = chunks.get(1);
        assertArrayEquals(new double[]{4.0, 5.0, 6.0, 7.0}, secondChunk, 0.0);

        double[] thirdChunk = chunks.get(2);
        assertArrayEquals(new double[]{8.0, 9.0}, thirdChunk, 0.0);
    }
}
