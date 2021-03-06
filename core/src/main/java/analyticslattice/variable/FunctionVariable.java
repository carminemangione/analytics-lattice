package analyticslattice.variable;

import analyticslattice.GetColumnValue;
import analyticslattice.GetRecordValue;

public abstract class FunctionVariable<R> implements GetRecordValue<R>, Variable {

    private final String name;

    public FunctionVariable(String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public String getString(GetColumnValue record) throws Exception {
        throw notImplementedException("getString");
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        throw notImplementedException("getBoolean");
    }

    @Override
    public boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
        throw notImplementedException("getBooleanPrimitive");
    }

    @Override
    public Integer getInteger(GetColumnValue record) throws Exception {
        throw notImplementedException("getInteger");
    }

    @Override
    public int getIntegerPrimitive(GetColumnValue record) throws Exception {
        throw notImplementedException("getIntegerPrimitive");
    }

    @Override
    public Long getLong(GetColumnValue record) throws Exception {
        throw notImplementedException("getLong");
    }

    @Override
    public long getLongPrimitive(GetColumnValue record) throws Exception {
        throw notImplementedException("getLongPrimitive");
    }

    @Override
    public Double getDouble(GetColumnValue record) throws Exception {
        throw notImplementedException("getDouble");
    }

    @Override
    public double getDoublePrimitive(GetColumnValue record) throws Exception {
        throw notImplementedException("getDoublePrimitive");
    }

    @Override
    public Object getObject(GetColumnValue record) throws Exception {
        throw notImplementedException("getObject");
    }

    @Override
    public R getCustom(GetColumnValue record) throws Exception {
        throw notImplementedException("getCustom");
    }

    private UnsupportedOperationException notImplementedException(String methodName) {
        return new UnsupportedOperationException(methodName + " has not been implemented by " + this.getClass().getCanonicalName());
    }
}
