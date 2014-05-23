package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class DoubleFunctionVariable extends FunctionVariable {

    protected DoubleFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract Double getDouble(GetColumnValue record) throws Exception ;

    @Override
    public String getString(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : Double.toString(value);
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : value != 0.0;
    }

    @Override
    public Integer getInteger(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : value.intValue();
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception {
        Double value = getDouble(record);
        return value == null ? null : value.longValue();
    }
}
