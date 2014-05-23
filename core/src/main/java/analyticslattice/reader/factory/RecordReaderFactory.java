package analyticslattice.reader.factory;

import analyticslattice.reader.RecordReader;

public interface RecordReaderFactory {

    RecordReader openReader() throws Exception;

}
