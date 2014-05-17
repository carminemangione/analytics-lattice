package analattice;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Iterator;

public class ColumnSet<E> implements Iterable<E> {

    public static <ELEMENT> ColumnSet<ELEMENT> of(){
        return new ColumnSet<ELEMENT>(ImmutableList.<ELEMENT>of());
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(ELEMENT e0){
        return new ColumnSet<ELEMENT>(ImmutableList.of(e0));
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(ELEMENT e0, ELEMENT e1){
        return new ColumnSet<ELEMENT>(ImmutableList.of(e0, e1));
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(ELEMENT e0, ELEMENT e1, ELEMENT e2){
        return new ColumnSet<ELEMENT>(ImmutableList.of(e0, e1, e2));
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(ELEMENT e0, ELEMENT e1, ELEMENT e2, ELEMENT e3){
        return new ColumnSet<ELEMENT>(ImmutableList.of(e0, e1, e2, e3));
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(Iterable<? extends ELEMENT> iterable){
        return new ColumnSet<ELEMENT>(ImmutableList.copyOf(iterable));
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(ELEMENT[] iterable){
        return new ColumnSet<ELEMENT>(ImmutableList.copyOf(iterable));
    }

    public static <ELEMENT> ColumnSet<ELEMENT> of(ImmutableList<ELEMENT> immutableList){
        return new ColumnSet<ELEMENT>(immutableList);
    }

    private final ImmutableList<E> columns;
    private final ImmutableMap<E, Integer> columnIndices;

    public ColumnSet(ImmutableList<E> columns) {
        this.columns = columns;
        ImmutableMap.Builder<E, Integer> columnIndices = ImmutableMap.builder();
        for (int i = 0; i < this.columns.size(); i++) {
            columnIndices.put(this.columns.get(i), i);
        }
        this.columnIndices = columnIndices.build();
    }

    public int columnIndex(E column) {
        Integer index = this.columnIndices.get(column);
        if (index == null) {
            String message = String.format("%s is not in the column set %s", column, this.columns);
            throw new IllegalArgumentException(message);
        }
        return index;
    }

    public E column(int index) {
        if (index < 0 || index >= this.columns.size()) {
            String message = String.format("invalid index %d, valid indices between [0, %d]", index, columns.size() - 1);
            throw new IndexOutOfBoundsException(message);
        }
        return columns.get(index);
    }

    public ImmutableList<E> getColumns() {
        return columns;
    }

    public ImmutableMap<E, Integer> getColumnIndices() {
        return columnIndices;
    }

    @Override
    public Iterator<E> iterator() {
        return this.columns.iterator();
    }

    public int size() {
        return columns.size();
    }
}
