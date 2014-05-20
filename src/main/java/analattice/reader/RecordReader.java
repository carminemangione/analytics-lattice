package analattice.reader;

import analattice.ColumnSet;
import analattice.GetColumnValue;

public interface RecordReader extends GetColumnValue, AutoCloseable {

    ColumnSet<String> getColumnSet();

    boolean next() throws Exception;
}
