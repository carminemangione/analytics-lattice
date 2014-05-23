package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class BooleanFunctionVariable extends FunctionVariable<Boolean> {

    public BooleanFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract Boolean getBoolean(GetColumnValue record) throws Exception;

    @Override
    public final Double getDouble(GetColumnValue record) throws Exception {
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : (boolReturn ? 1.0 : 0.0);
    }

    @Override
    public final String getString(GetColumnValue record) throws Exception{
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : boolReturn.toString();
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception{
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : (boolReturn ? 1L : 0L);
    }

    @Override
    public final Integer getInteger(GetColumnValue record) throws Exception{
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : (boolReturn ? 1 : 0);
    }

    @Override
    public final Boolean getObject(GetColumnValue record) throws Exception {
        return getBoolean(record);
    }

    @Override
    public final Boolean getCustom(GetColumnValue record) throws Exception {
        return getBoolean(record);
    }

    // need to throw exceptions, so just call super
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
