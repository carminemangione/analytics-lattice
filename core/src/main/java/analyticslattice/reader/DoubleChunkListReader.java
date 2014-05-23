package analyticslattice.reader;

import java.util.List;

public class DoubleChunkListReader extends DoubleChunkReader{

    private final List<double[]> chunks;
    private int currentChunkIndex = 0;

    public DoubleChunkListReader(List<double[]> chunks) {
        this.chunks = chunks;
    }

    public double[] next(){
        return chunks.get(currentChunkIndex++);
    }

    @Override
    public void close() throws Exception {

    }
}
