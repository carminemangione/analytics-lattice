package analyticslattice.reader;

import analyticslattice.ColumnSet;
import analyticslattice.GetColumnValue;

public interface RecordReader extends GetColumnValue, AutoCloseable {

    ColumnSet getColumnSet();

    boolean next() throws Exception;
}
