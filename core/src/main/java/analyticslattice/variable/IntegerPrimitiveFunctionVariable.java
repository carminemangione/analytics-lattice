package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class IntegerPrimitiveFunctionVariable extends FunctionVariable{

    public IntegerPrimitiveFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract int getIntegerPrimitive(GetColumnValue record) throws Exception;

    @Override
    public final String getString(GetColumnValue record) throws Exception {
        return Integer.toString(getIntegerPrimitive(record));
    }

    @Override
    public final Boolean getBoolean(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record);
    }

    @Override
    public final boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record) != 0;
    }

    @Override
    public final Integer getInteger(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record);
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception {
        return getLongPrimitive(record);
    }

    @Override
    public final long getLongPrimitive(GetColumnValue record) throws Exception {
        return (long) getIntegerPrimitive(record);
    }

    @Override
    public final Double getDouble(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record);
    }

    @Override
    public final double getDoublePrimitive(GetColumnValue record) throws Exception {
        return (double) getIntegerPrimitive(record);
    }

    @Override
    public final Integer getObject(GetColumnValue record) throws Exception {
        return getInteger(record);
    }

    @Override
    public final Integer getCustom(GetColumnValue record) throws Exception {
        return getInteger(record);
    }
}
