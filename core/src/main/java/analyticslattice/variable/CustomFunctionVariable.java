package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class CustomFunctionVariable<R> extends FunctionVariable<R> {

    public CustomFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract R getCustom(GetColumnValue record) throws Exception;

    @Override
    public final R getObject(GetColumnValue record) throws Exception {
        return getCustom(record);
    }
}
