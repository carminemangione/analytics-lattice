package analattice;

import java.util.ArrayList;
import java.util.List;

public class RecordListWriter<K> {

    private final ColumnSet<K> columnSet;
    private final List<List<Object>> records = new ArrayList<List<Object>>();
    private List<Object> currentRecord = null;

    public RecordListWriter(ColumnSet<K> columnSet) {
        this.columnSet = columnSet;
    }

    public void appendRecord(){
        currentRecord = new ArrayList<Object>(columnSet.size());
        records.add(currentRecord);
    }

    public void set(K column, Object value){
        currentRecord.set(columnSet.columnIndex(column), value);
    }

}
