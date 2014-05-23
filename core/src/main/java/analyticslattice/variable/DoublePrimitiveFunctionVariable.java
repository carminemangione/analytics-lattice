package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class DoublePrimitiveFunctionVariable extends FunctionVariable<Double> {

    public DoublePrimitiveFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract double getDoublePrimitive(GetColumnValue record) throws Exception ;

    @Override
    public final Double getDouble(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record);
    }

    @Override
    public final String getString(GetColumnValue record) throws Exception {
        return Double.toString(getDoublePrimitive(record));
    }

    @Override
    public final Boolean getBoolean(GetColumnValue record) throws Exception {
        return getBooleanPrimitive(record);
    }

    @Override
    public final boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        return getDoublePrimitive(record) != 0.0;
    }

    @Override
    public final Integer getInteger(GetColumnValue record) throws Exception {
        return getIntegerPrimitive(record);
    }

    @Override
    public final int getIntegerPrimitive(GetColumnValue record) throws Exception {
        return (int) getDoublePrimitive(record);
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception {
        return getLongPrimitive(record);
    }

    @Override
    public final long getLongPrimitive(GetColumnValue record) throws Exception {
        return (long) getDoublePrimitive(record);
    }

    @Override
    public final Double getObject(GetColumnValue record) throws Exception {
        return getDouble(record);
    }

    @Override
    public final Double getCustom(GetColumnValue record) throws Exception {
        return getDouble(record);
    }


}
