package analyticslattice.visitor;

import analyticslattice.GetColumnValue;
import analyticslattice.stats.description.*;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

import java.util.Arrays;

public class DescriptionVisitors implements RecordVisitor {

    private final ImmutableMap<String, Double> doubles;
    private final ImmutableMap<String, DoublePrimitive> doublePrimitives;
    private final ImmutableMap<String, Integer> integers;
    private final ImmutableMap<String, IntegerPrimitive> integerPrimitives;
    private final ImmutableMap<String, Boolean> booleans;
    private final ImmutableMap<String, BooleanPrimitive> booleanPrimitives;
    private final ImmutableList<RecordVisitor> allVisitors;


    public DescriptionVisitors(ImmutableMap<String, Double> doubles,
                               ImmutableMap<String, DoublePrimitive> doublePrimitives,
                               ImmutableMap<String, Integer> integers,
                               ImmutableMap<String, IntegerPrimitive> integerPrimitives,
                               ImmutableMap<String, Boolean> booleans,
                               ImmutableMap<String, BooleanPrimitive> booleanPrimitives) {
        this.doubles = doubles;
        this.doublePrimitives = doublePrimitives;
        this.integers = integers;
        this.integerPrimitives = integerPrimitives;
        this.booleans = booleans;
        this.booleanPrimitives = booleanPrimitives;
        this.allVisitors = ImmutableList.<RecordVisitor>builder()
                .addAll(ImmutableList.copyOf(doubles.values()))
                .addAll(doublePrimitives.values())
                .addAll(integers.values())
                .addAll(integerPrimitives.values())
                .addAll(booleans.values())
                .addAll(booleanPrimitives.values())
                .build();
    }

    public DoubleDescription getDouble(String column) {
        Double x = doubles.get(column);
        if (x == null) {
            throw new IllegalArgumentException(String.format("no DoubleDescription found for column \"%s\"", column));
        }
        return x.getDescription();
    }

    public DoublePrimitiveDescription getDoublePrimitive(String column) {
        DoublePrimitive x = doublePrimitives.get(column);
        if (x == null) {
            throw new IllegalArgumentException(String.format("no DoublePrimitiveDescription found for column \"%s\"", column));
        }
        return x.getDescription();
    }

    public IntegerDescription getInteger(String column) {
        Integer x = integers.get(column);
        if (x == null) {
            throw new IllegalArgumentException(String.format("no IntegerDescription found for column \"%s\"", column));
        }
        return x.getDescription();
    }

    public IntegerPrimitiveDescription getIntegerPrimitive(String column) {
        IntegerPrimitive x = integerPrimitives.get(column);
        if (x == null) {
            throw new IllegalArgumentException(String.format("no IntegerPrimitiveDescription found for column \"%s\"", column));
        }
        return x.getDescription();
    }

    public BooleanDescription getBoolean(String column) {
        Boolean x = booleans.get(column);
        if (x == null) {
            throw new IllegalArgumentException(String.format("no BooleanDescription found for column \"%s\"", column));
        }
        return x.getDescription();
    }

    public BooleanPrimitiveDescription getBooleanPrimitive(String column) {
        BooleanPrimitive x = booleanPrimitives.get(column);
        if (x == null) {
            throw new IllegalArgumentException(String.format("no BooleanPrimitiveDescription found for column \"%s\"", column));
        }
        return x.getDescription();
    }

    @Override
    public void visit(GetColumnValue record) throws Exception {
        for (RecordVisitor visitor : allVisitors) {
            visitor.visit(record);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        printOutTypeVisitors(stringBuilder, doubles.values(), "double column\t");
        printOutTypeVisitors(stringBuilder, doublePrimitives.values(), "primitive double column\t");
        printOutTypeVisitors(stringBuilder, integers.values(), "integer column\t");
        printOutTypeVisitors(stringBuilder, integerPrimitives.values(), "primitive integer column\t");
        printOutTypeVisitors(stringBuilder, booleans.values(), "boolean column\t");
        printOutTypeVisitors(stringBuilder, booleanPrimitives.values(), "primitive boolean column\t");
        return stringBuilder.toString();
    }

    private void printOutTypeVisitors(StringBuilder stringBuilder,
                                      ImmutableCollection<? extends ColumnVisitor> visitors,
                                      String headerPrefix) {
        if (!visitors.isEmpty()) {
            ImmutableList<? extends ColumnVisitor> sortedDoubles = Ordering.natural().immutableSortedCopy(visitors);
            stringBuilder.append(headerPrefix)
                    .append(sortedDoubles.get(0).getDescription().header()).append("\n");
            for (int i = 0; i < sortedDoubles.size(); i++) {
                ColumnVisitor doubleDescription = sortedDoubles.get(i);
                stringBuilder.append(doubleDescription.column).append("\t");
                doubleDescription.getDescription().toString(stringBuilder);
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
        }
    }

    public static class Double extends ColumnVisitor<DoubleDescription> {

        public Double(String column) {
            super(column, new DoubleDescription());
        }

        @Override
        public void visit(GetColumnValue record) throws Exception {
            description.visit(record.getDouble(column));
        }
    }

    public static class DoublePrimitive extends ColumnVisitor<DoublePrimitiveDescription> {

        public DoublePrimitive(String column) {
            super(column, new DoublePrimitiveDescription());
        }

        @Override
        public void visit(GetColumnValue record) throws Exception {
            description.visit(record.getDoublePrimitive(column));
        }
    }

    public static class Integer extends ColumnVisitor<IntegerDescription> {

        public Integer(String column) {
            super(column, new IntegerDescription());
        }

        @Override
        public void visit(GetColumnValue record) throws Exception {
            description.visit(record.getInteger(column));
        }
    }

    public static class IntegerPrimitive extends ColumnVisitor<IntegerPrimitiveDescription> {

        public IntegerPrimitive(String column) {
            super(column, new IntegerPrimitiveDescription());
        }

        @Override
        public void visit(GetColumnValue record) throws Exception {
            description.visit(record.getIntegerPrimitive(column));
        }
    }

    public static class Boolean extends ColumnVisitor<BooleanDescription> {

        public Boolean(String column) {
            super(column, new BooleanDescription());
        }

        @Override
        public void visit(GetColumnValue record) throws Exception {
            description.visit(record.getBoolean(column));
        }
    }

    public static class BooleanPrimitive extends ColumnVisitor<BooleanPrimitiveDescription> {

        public BooleanPrimitive(String column) {
            super(column, new BooleanPrimitiveDescription());
        }

        @Override
        public void visit(GetColumnValue record) throws Exception {
            description.visit(record.getBooleanPrimitive(column));
        }
    }

    public abstract static class ColumnVisitor<D extends PrintableDescription>
            implements RecordVisitor, Comparable<ColumnVisitor> {

        protected final String column;
        protected final D description;

        public ColumnVisitor(String column, D description) {
            this.column = column;
            this.description = description;
        }

        public D getDescription() {
            return description;
        }

        @Override
        public int compareTo(ColumnVisitor o) {
            return column.compareToIgnoreCase(o.column);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final ImmutableMap.Builder<String, Double> doubles = ImmutableMap.builder();
        private final ImmutableMap.Builder<String, DoublePrimitive> doublePrimitives = ImmutableMap.builder();
        private final ImmutableMap.Builder<String, Integer> integers = ImmutableMap.builder();
        private final ImmutableMap.Builder<String, IntegerPrimitive> integerPrimitives = ImmutableMap.builder();
        private final ImmutableMap.Builder<String, Boolean> booleans = ImmutableMap.builder();
        private final ImmutableMap.Builder<String, BooleanPrimitive> booleanPrimitives = ImmutableMap.builder();

        private Builder() {
        }

        public Builder addDouble(String column) {
            doubles.put(column, new Double(column));
            return this;
        }

        public Builder addDoubles(Iterable<String> columns) {
            for (String column : columns) {
                addDouble(column);
            }
            return this;
        }

        public Builder addDoubles(String... columns) {
            return addDoubles(Arrays.asList(columns));
        }

        public Builder addDoublePrimitive(String column) {
            doublePrimitives.put(column, new DoublePrimitive(column));
            return this;
        }

        public Builder addDoublePrimitives(Iterable<String> columns) {
            for (String column : columns) {
                addDoublePrimitive(column);
            }
            return this;
        }

        public Builder addDoublePrimitives(String... columns) {
            return addDoublePrimitives(Arrays.asList(columns));
        }

        public Builder addInteger(String column) {
            integers.put(column, new Integer(column));
            return this;
        }

        public Builder addIntegers(Iterable<String> columns) {
            for (String column : columns) {
                addInteger(column);
            }
            return this;
        }

        public Builder addIntegers(String... columns) {
            return addIntegers(Arrays.asList(columns));
        }

        public Builder addIntegerPrimitive(String column) {
            integerPrimitives.put(column, new IntegerPrimitive(column));
            return this;
        }

        public Builder addIntegerPrimitives(Iterable<String> columns) {
            for (String column : columns) {
                addIntegerPrimitive(column);
            }
            return this;
        }

        public Builder addIntegerPrimitives(String... columns) {
            return addIntegerPrimitives(Arrays.asList(columns));
        }

        public Builder addBoolean(String column) {
            booleans.put(column, new Boolean(column));
            return this;
        }

        public Builder addBooleans(Iterable<String> columns) {
            for (String column : columns) {
                addBoolean(column);
            }
            return this;
        }

        public Builder addBooleans(String... columns) {
            return addBooleans(Arrays.asList(columns));
        }

        public Builder addBooleanPrimitive(String column) {
            booleanPrimitives.put(column, new BooleanPrimitive(column));
            return this;
        }

        public Builder addBooleanPrimitives(Iterable<String> columns) {
            for (String column : columns) {
                addBooleanPrimitive(column);
            }
            return this;
        }

        public Builder addBooleanPrimitives(String... columns) {
            return addDoublePrimitives(Arrays.asList(columns));
        }

        public DescriptionVisitors build() {
            return new DescriptionVisitors(
                    doubles.build(),
                    doublePrimitives.build(),
                    integers.build(),
                    integerPrimitives.build(),
                    booleans.build(),
                    booleanPrimitives.build());
        }
    }
}
