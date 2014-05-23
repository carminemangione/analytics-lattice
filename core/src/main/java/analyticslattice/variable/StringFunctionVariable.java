package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class StringFunctionVariable extends FunctionVariable<String>{

    public StringFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract String getString(GetColumnValue record) throws Exception;


    @Override
    public final String getObject(GetColumnValue record) throws Exception {
        return getString(record);
    }

    @Override
    public final String getCustom(GetColumnValue record) throws Exception {
        return getString(record);
    }

    //need to throw exception, should have parsed the column as a type long before function variable for these types
    @Override
    public final Boolean getBoolean(GetColumnValue record) throws Exception {
        return super.getBoolean(record);
    }

    @Override
    public final boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        return super.getBooleanPrimitive(record);
    }

    @Override
    public final Integer getInteger(GetColumnValue record) throws Exception {
        return super.getInteger(record);
    }

    @Override
    public final int getIntegerPrimitive(GetColumnValue record) throws Exception {
        return super.getIntegerPrimitive(record);
    }

    @Override
    public final Long getLong(GetColumnValue record) throws Exception {
        return super.getLong(record);
    }

    @Override
    public final long getLongPrimitive(GetColumnValue record) throws Exception {
        return super.getLongPrimitive(record);
    }

    @Override
    public final Double getDouble(GetColumnValue record) throws Exception {
        return super.getDouble(record);
    }

    @Override
    public final double getDoublePrimitive(GetColumnValue record) throws Exception {
        return super.getDoublePrimitive(record);
    }
}
