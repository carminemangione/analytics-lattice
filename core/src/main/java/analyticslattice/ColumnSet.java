package analyticslattice;

import com.google.common.collect.ImmutableList;

public class ColumnSet extends IndexedSet<String> {

    public static ColumnSet of() {
        return new ColumnSet(ImmutableList.<String>of());
    }

    public static ColumnSet of(String e0) {
        return new ColumnSet(ImmutableList.of(e0));
    }

    public static ColumnSet of(String e0, String e1) {
        return new ColumnSet(ImmutableList.of(e0, e1));
    }

    public static ColumnSet of(String e0, String e1, String e2) {
        return new ColumnSet(ImmutableList.of(e0, e1, e2));
    }

    public static ColumnSet of(String e0, String e1, String e2, String e3) {
        return new ColumnSet(ImmutableList.of(e0, e1, e2, e3));
    }

    public static ColumnSet from(Iterable<String> iterable) {
        return new ColumnSet(ImmutableList.copyOf(iterable));
    }

    public static ColumnSet from(String[] iterable) {
        return new ColumnSet(ImmutableList.copyOf(iterable));
    }

    public static ColumnSet from(ImmutableList<String> immutableList) {
        return new ColumnSet(immutableList);
    }

    public ColumnSet(ImmutableList<String> columns) {
        super(columns);
    }

    public ColumnSet appendAndOverwrite(ColumnSet rightColumnSet) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (String column : this) {
            if(!rightColumnSet.contains(column)){
                builder.add(column);
            }
        }
        builder.addAll(rightColumnSet);
        return new ColumnSet(builder.build());
    }
}
