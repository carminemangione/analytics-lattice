package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class DoubleFunctionVariable extends FunctionVariable<Double> {

    public DoubleFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract Double getDouble(GetColumnValue record) throws Exception;

    @Override
    public final String getString(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : Double.toString(value);
    }

    @Override
    public final Boolean getBoolean(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : value != 0.0;
    }

    @Override
    public final Integer getInteger(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : value.intValue();
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : value.longValue();
    }

    @Override
    public final Double getObject(GetColumnValue record) throws Exception {
        return getDouble(record);
    }

    @Override
    public final Double getCustom(GetColumnValue record) throws Exception {
        return getDouble(record);
    }

    //need to throw exception so just call super
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
