package analattice.variable;

import analattice.GetColumnValue;

public abstract class BooleanFunctionVariable extends FunctionVariable {

    protected BooleanFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract Boolean getBoolean(GetColumnValue record) throws Exception;

    @Override
    public Double getDouble(GetColumnValue record) throws Exception {
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : (boolReturn ? 1.0 : 0.0);
    }

    @Override
    public String getString(GetColumnValue record) throws Exception{
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : boolReturn.toString();
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception{
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : (boolReturn ? 1L : 0L);
    }

    @Override
    public Integer getInteger(GetColumnValue record) throws Exception{
        Boolean boolReturn = getBoolean(record);
        return boolReturn == null ? null : (boolReturn ? 1 : 0);
    }
}
