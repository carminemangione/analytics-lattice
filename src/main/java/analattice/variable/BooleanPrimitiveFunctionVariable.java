package analattice.variable;

import analattice.GetColumnValue;

public abstract class BooleanPrimitiveFunctionVariable extends FunctionVariable {

    protected BooleanPrimitiveFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract boolean getBooleanPrimitive(GetColumnValue record) throws Exception;

    @Override
    public String getString(GetColumnValue record) throws Exception {
        return Boolean.toString(getBooleanPrimitive(record));
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record);
    }

    @Override
    public Integer getInteger(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record);
    }

    @Override
    public int getIntegerPrimitive(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record) ? 1 : 0;
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception {
        return getLongPrimitive(record);
    }

    @Override
    public long getLongPrimitive(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record) ? 1L : 0L;
    }

    @Override
    public Double getDouble(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record);
    }

    @Override
    public double getDoublePrimitive(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record) ? 1.0 : 0.0;
    }
}
