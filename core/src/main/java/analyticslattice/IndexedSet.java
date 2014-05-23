package analyticslattice;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Iterator;

public class IndexedSet<E> implements Iterable<E> {

    public static <ELEMENT> IndexedSet<ELEMENT> of(){
        return new IndexedSet<>(ImmutableList.<ELEMENT>of());
    }

    public static <ELEMENT> IndexedSet<ELEMENT> of(ELEMENT e0){
        return new IndexedSet<>(ImmutableList.of(e0));
    }

    public static <ELEMENT> IndexedSet<ELEMENT> of(ELEMENT e0, ELEMENT e1){
        return new IndexedSet<>(ImmutableList.of(e0, e1));
    }

    public static <ELEMENT> IndexedSet<ELEMENT> of(ELEMENT e0, ELEMENT e1, ELEMENT e2){
        return new IndexedSet<>(ImmutableList.of(e0, e1, e2));
    }

    public static <ELEMENT> IndexedSet<ELEMENT> of(ELEMENT e0, ELEMENT e1, ELEMENT e2, ELEMENT e3){
        return new IndexedSet<>(ImmutableList.of(e0, e1, e2, e3));
    }

    private final ImmutableList<E> columns;
    private final ImmutableMap<E, Integer> columnIndices;

    public IndexedSet(ImmutableList<E> columns) {
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

    public int size() {
        return columns.size();
    }

    public boolean contains(E key) {
        return columnIndices.containsKey(key);
    }

    @Override
    public Iterator<E> iterator() {
        return this.columns.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexedSet columnSet = (IndexedSet) o;

        return columns.equals(columnSet.columns);

    }

    @Override
    public int hashCode() {
        return columns.hashCode();
    }

    @Override
    public String toString() {
        return columns.toString();
    }
}
