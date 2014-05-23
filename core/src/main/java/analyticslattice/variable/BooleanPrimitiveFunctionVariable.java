package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class BooleanPrimitiveFunctionVariable extends FunctionVariable {

    public BooleanPrimitiveFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract boolean getBooleanPrimitive(GetColumnValue record) throws Exception;

    @Override
    public final String getString(GetColumnValue record) throws Exception {
        return Boolean.toString(getBooleanPrimitive(record));
    }

    @Override
    public final Boolean getBoolean(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record);
    }

    @Override
    public final Integer getInteger(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record);
    }

    @Override
    public final int getIntegerPrimitive(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record) ? 1 : 0;
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception {
        return getLongPrimitive(record);
    }

    @Override
    public final long getLongPrimitive(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record) ? 1L : 0L;
    }

    @Override
    public final Double getDouble(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record);
    }

    @Override
    public final double getDoublePrimitive(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record) ? 1.0 : 0.0;
    }

    @Override
    public final Boolean getObject(GetColumnValue record) throws Exception {
        return getBoolean(record);
    }

    @Override
    public final Boolean getCustom(GetColumnValue record) throws Exception {
        return getBoolean(record);
    }
}
