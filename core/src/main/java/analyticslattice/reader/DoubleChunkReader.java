package analyticslattice.reader;

public abstract class DoubleChunkReader implements AutoCloseable {



    protected DoubleChunkReader() {
    }

    public abstract double[] next();

}