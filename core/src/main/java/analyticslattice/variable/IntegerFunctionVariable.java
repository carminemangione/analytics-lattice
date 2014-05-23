package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class IntegerFunctionVariable extends FunctionVariable<Integer> {

    public IntegerFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract Integer getInteger(GetColumnValue record) throws Exception;

    @Override
    public final String getString(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value.toString();
    }

    @Override
    public final Boolean getBoolean(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value != 0;
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value.longValue();
    }

    @Override
    public final Double getDouble(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value.doubleValue();
    }

    @Override
    public final Integer getObject(GetColumnValue record) throws Exception {
        return getInteger(record);
    }

    @Override
    public final Integer getCustom(GetColumnValue record) throws Exception {
        return getInteger(record);
    }

    // need to throw exception, just call super
    @Override
    public final boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        return super.getBooleanPrimitive(record);
    }

    @Override
    public final int getIntegerPrimitive(GetColumnValue record) throws Exception {
        return super.getIntegerPrimitive(record);
    }

    @Override
    public final long getLongPrimitive(GetColumnValue record) throws Exception {
        return super.getLongPrimitive(record);
    }

    @Override
    public final double getDoublePrimitive(GetColumnValue record) throws Exception {
        return super.getDoublePrimitive(record);
    }
}
