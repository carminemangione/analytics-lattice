package analattice.variable;

import analattice.GetColumnValue;

public abstract class IntegerFunctionVariable extends FunctionVariable {
    protected IntegerFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract Integer getInteger(GetColumnValue record) throws Exception;

    @Override
    public String getString(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value.toString();
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value != 0;
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value.longValue();
    }

    @Override
    public Double getDouble(GetColumnValue record) throws Exception {
        Integer value = getInteger(record);
        return value == null ? null : value.doubleValue();
    }
}
