package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class IntegerPrimitiveFunctionVariable extends FunctionVariable{

    protected IntegerPrimitiveFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract int getIntegerPrimitive(GetColumnValue record) throws Exception;

    @Override
    public String getString(GetColumnValue record) throws Exception {
        return Integer.toString(getIntegerPrimitive(record));
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record);
    }

    @Override
    public boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record) != 0;
    }

    @Override
    public Integer getInteger(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record);
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception {
        return getLongPrimitive(record);
    }

    @Override
    public long getLongPrimitive(GetColumnValue record) throws Exception {
        return (long) getIntegerPrimitive(record);
    }

    @Override
    public Double getDouble(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record);
    }

    @Override
    public double getDoublePrimitive(GetColumnValue record) throws Exception {
        return (double) getIntegerPrimitive(record);
    }
}
