package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class DoublePrimitiveFunctionVariable extends FunctionVariable {

    protected DoublePrimitiveFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract double getDoublePrimitive(GetColumnValue record) throws Exception ;

    @Override
    public Double getDouble(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record);
    }

    @Override
    public String getString(GetColumnValue record) throws Exception {
        return Double.toString(getDoublePrimitive(record));
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record);
    }

    @Override
    public boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record) != 0.0;
    }

    @Override
    public Integer getInteger(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record);
    }

    @Override
    public int getIntegerPrimitive(GetColumnValue record) throws Exception {
        return (int) getDoublePrimitive(record);
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception {
        return getLongPrimitive(record);
    }

    @Override
    public long getLongPrimitive(GetColumnValue record) throws Exception {
        return (long) getDoublePrimitive(record);
    }
}
