package analyticslattice.reader;

import analyticslattice.ColumnSet;
import analyticslattice.variable.FunctionVariable;
import analyticslattice.variable.Variable;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

public class TransformReader implements RecordReader {

    private final RecordReader delegate;

    private final ImmutableList<FunctionVariable> functionVariables;
    private final ColumnSet reconciledColumnSet;
    private final int[] indexMap;

    public TransformReader(RecordReader delegate, ImmutableList<FunctionVariable> functionVariables) {
        this.delegate = delegate;
        this.functionVariables = functionVariables;

        ColumnSet leftColumnSet = delegate.getColumnSet();
        ColumnSet rightColumnSet = new ColumnSet(FluentIterable.from(functionVariables)
                .transform(Variable.TO_NAME)
                .toList());
        this.reconciledColumnSet = leftColumnSet.appendAndOverwrite(rightColumnSet);
        this.indexMap = new int[reconciledColumnSet.size()];
        for (int i = 0; i < reconciledColumnSet.size(); i++) {
            String column = reconciledColumnSet.column(i);
            if (rightColumnSet.contains(column)) {
                indexMap[i] = posToNegIndex(rightColumnSet.columnIndex(column));
            } else {
                indexMap[i] = leftColumnSet.columnIndex(column);
            }
        }
    }

    @Override
    public ColumnSet getColumnSet() {
        return reconciledColumnSet;
    }

    @Override
    public boolean next() throws Exception {
        return delegate.next();
    }

    @Override
    public String getString(String columnLabel) throws Exception {
        return getString(columnIndex(columnLabel));
    }

    @Override
    public String getString(int columnIndex) throws Exception {
        String toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getString(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getString(delegate);
        }
        return toReturn;
    }

    @Override
    public Boolean getBoolean(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getBoolean(columnIndex);
    }

    @Override
    public Boolean getBoolean(int columnIndex) throws Exception {
        Boolean toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getBoolean(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getBoolean(delegate);
        }
        return toReturn;
    }

    @Override
    public boolean getBooleanPrimitive(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getBooleanPrimitive(columnIndex);
    }

    @Override
    public boolean getBooleanPrimitive(int columnIndex) throws Exception {
        boolean toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getBooleanPrimitive(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getBooleanPrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public Integer getInteger(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getInteger(columnIndex);
    }

    @Override
    public Integer getInteger(int columnIndex) throws Exception {
        Integer toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getInteger(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getInteger(delegate);
        }
        return toReturn;
    }

    @Override
    public int getIntegerPrimitive(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getIntegerPrimitive(columnIndex);
    }

    @Override
    public int getIntegerPrimitive(int columnIndex) throws Exception {
        int toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getIntegerPrimitive(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getIntegerPrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public Long getLong(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getLong(columnIndex);
    }

    @Override
    public Long getLong(int columnIndex) throws Exception {
        Long toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getLong(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getLong(delegate);
        }
        return toReturn;
    }

    @Override
    public long getLongPrimitive(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getLongPrimitive(columnIndex);
    }

    @Override
    public long getLongPrimitive(int columnIndex) throws Exception {
        long toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getLongPrimitive(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getLongPrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public Double getDouble(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getDouble(columnIndex);
    }

    @Override
    public Double getDouble(int columnIndex) throws Exception {
        Double toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getDouble(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getDouble(delegate);
        }
        return toReturn;
    }

    @Override
    public double getDoublePrimitive(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getDoublePrimitive(columnIndex);
    }

    @Override
    public double getDoublePrimitive(int columnIndex) throws Exception {
        double toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getDoublePrimitive(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = functionVariable.getDoublePrimitive(delegate);
        }
        return toReturn;
    }

    @Override
    public <T> T getCustom(String columnLabel, Class<T> clazz) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getCustom(columnIndex, clazz);
    }

    @Override
    public <T> T getCustom(int columnIndex, Class<T> clazz) throws Exception {
        T toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getCustom(columnIndex, clazz);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = clazz.cast(functionVariable.getCustom(delegate));
        }
        return toReturn;
    }

    @Override
    public <T> T getCustom(String columnLabel) throws Exception {
        int columnIndex = columnIndex(columnLabel);
        return getCustom(columnIndex);
    }

    @Override
    public <T> T getCustom(int columnIndex) throws Exception {
        T toReturn;
        columnIndex = indexMap[columnIndex];
        if (columnIndex > -1) {
            toReturn = delegate.getCustom(columnIndex);
        } else {
            columnIndex = negToPosIndex(columnIndex);
            FunctionVariable functionVariable = functionVariables.get(columnIndex);
            toReturn = (T) functionVariable.getCustom(delegate);
        }
        return toReturn;
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }

    private int columnIndex(String columnLabel) {
        return reconciledColumnSet.columnIndex(columnLabel);
    }

    private static int posToNegIndex(int postColumnIndex) {
        return -postColumnIndex - 1;
    }

    private static int negToPosIndex(int negColumnIndex) {
        return -(negColumnIndex + 1);
    }
}
