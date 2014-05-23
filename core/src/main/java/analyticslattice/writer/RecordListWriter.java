package analyticslattice.writer;

import analyticslattice.ColumnSet;

import java.util.ArrayList;
import java.util.List;

public class RecordListWriter<String> {

    private final ColumnSet<String> columnSet;
    private final List<List<Object>> records = new ArrayList<List<Object>>();
    private List<Object> currentRecord = null;

    public RecordListWriter(ColumnSet<String> columnSet) {
        this.columnSet = columnSet;
    }

    public void appendRecord(){
        currentRecord = new ArrayList<Object>(columnSet.size());
        records.add(currentRecord);
    }

    public void set(String column, Object value){
        currentRecord.set(columnSet.columnIndex(column), value);
    }

}
