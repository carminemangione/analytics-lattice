package analattice.writer;

public abstract class DoubleChunkWriter implements AutoCloseable {

    private final int chunkSize;

    private double[] currentChunk;
    private int currentIndex;
    private boolean open = true;

    public DoubleChunkWriter() {
        this(1024);
    }

    public DoubleChunkWriter(int chunkSize) {
        if (chunkSize < 1) {
            throw new IllegalArgumentException("chunkSize must be positive");
        }
        this.chunkSize = chunkSize;
        this.currentChunk = new double[chunkSize];
        this.currentIndex = -1;
    }

    public void append() {
        if(!open){
            throw new IllegalStateException("Not open");
        }
        currentIndex++;
        if (currentIndex == currentChunk.length) {
            flushChunk(currentChunk, chunkSize);
            currentIndex = 0;
            currentChunk = new double[chunkSize];
        }
    }

    public void set(double v) {
        if(!open){
            throw new IllegalStateException("Not open");
        }
        if (currentIndex < 0) {
            throw new IllegalStateException("Have not appended any records");
        }
        currentChunk[currentIndex] = v;
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public void close() throws Exception {
        flushChunk(currentChunk, currentIndex+1);
        open = false;
    }

    protected abstract void flushChunk(double[] chunk, int size);
}
