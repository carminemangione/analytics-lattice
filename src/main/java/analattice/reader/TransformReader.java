package analattice.reader;

import analattice.ColumnSet;
import analattice.variable.FunctionVariable;
import com.google.common.collect.ImmutableList;

public class TransformReader implements RecordReader {

    private final RecordReader delegate;
    private final int numDelegateColumns;

    private final ImmutableList<FunctionVariable> functionVariables;
    private final ColumnSet<String> columnSet;

    public TransformReader(RecordReader delegate, ImmutableList<FunctionVariable> functionVariables) {
        this.delegate = delegate;
        this.numDelegateColumns = delegate.getColumnSet().size();
        this.functionVariables = functionVariables;

        ImmutableList.Builder<String> totalColumnSetBuilder = ImmutableList.builder();
        totalColumnSetBuilder.addAll(this.delegate.getColumnSet().getColumns());
        for (FunctionVariable functionVariable : functionVariables) {
            totalColumnSetBuilder.add(functionVariable.getName());
        }
        this.columnSet = ColumnSet.from(totalColumnSetBuilder.build());
    }

    @Override
    public ColumnSet<String> getColumnSet() {
        return columnSet;
    }

    @Override
    public boolean next() throws Exception {
        return delegate.next();
    }

    @Override
    public String getString(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getString(columnIndex);
    }

    @Override
    public String getString(int columnIndex) throws Exception {
        String toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getString(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getString(delegate);
        }
        return toReturn;
    }

    @Override
    public Boolean getBoolean(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getBoolean(columnIndex);
    }

    @Override
    public Boolean getBoolean(int columnIndex) throws Exception {
        Boolean toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getBoolean(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getBoolean(delegate);
        }
        return toReturn;
    }

    @Override
    public boolean getBooleanPrimitive(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getBooleanPrimitive(columnIndex);
    }

    @Override
    public boolean getBooleanPrimitive(int columnIndex) throws Exception {
        boolean toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getBooleanPrimitive(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getBooleanPrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public Integer getInteger(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getInteger(columnIndex);
    }

    @Override
    public Integer getInteger(int columnIndex) throws Exception {
        Integer toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getInteger(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getInteger(delegate);
        }
        return toReturn;
    }

    @Override
    public int getIntegerPrimitive(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getIntegerPrimitive(columnIndex);
    }

    @Override
    public int getIntegerPrimitive(int columnIndex) throws Exception {
        int toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getIntegerPrimitive(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getIntegerPrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public Long getLong(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getLong(columnIndex);
    }

    @Override
    public Long getLong(int columnIndex) throws Exception {
        Long toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getLong(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getLong(delegate);
        }
        return toReturn;
    }

    @Override
    public long getLongPrimitive(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getLongPrimitive(columnIndex);
    }

    @Override
    public long getLongPrimitive(int columnIndex) throws Exception {
        long toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getLongPrimitive(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getLongPrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public Double getDouble(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getDouble(columnIndex);
    }

    @Override
    public Double getDouble(int columnIndex) throws Exception {
        Double toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getDouble(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getDouble(delegate);
        }
        return toReturn;
    }

    @Override
    public double getDoublePrimitive(String columnLabel) throws Exception {
        int columnIndex = columnSet.columnIndex(columnLabel);
        return getDoublePrimitive(columnIndex);
    }

    @Override
    public double getDoublePrimitive(int columnIndex) throws Exception {
        double toReturn;
        if (columnIndex < numDelegateColumns) {
            toReturn = delegate.getDoublePrimitive(columnIndex);
        } else {
            FunctionVariable functionVariable = functionVariables.get(columnIndex - numDelegateColumns);
            toReturn = functionVariable.getDoublePrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }
}
