package analyticslattice.writer;

import java.util.ArrayList;
import java.util.List;

public class DoubleChunkListWriter extends DoubleChunkWriter {

    public DoubleChunkListWriter() {
        super();
    }

    public DoubleChunkListWriter(int chunkSize) {
        super(chunkSize);
    }

    private final List<double[]> chunks = new ArrayList<>();

    @Override
    protected void flushChunk(double[] chunk, int size) {
        if(chunk.length == size){
            chunks.add(chunk);
        }else if(size > 0){
            double[] compressedChunk = new double[size];
            System.arraycopy(chunk, 0, compressedChunk, 0, size);
            chunks.add(compressedChunk);
        }
    }

    public List<double[]> getChunks() {
        if(isOpen()){
            throw new IllegalStateException("Can only get chunks after being open");
        }
        return chunks;
    }
}
