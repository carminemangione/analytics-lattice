package analyticslattice.variable;

import analyticslattice.GetColumnValue;

public abstract class StringFunctionVariable extends FunctionVariable{

    protected StringFunctionVariable(String name) {
        super(name);
    }

    @Override
    public abstract String getString(GetColumnValue record) throws Exception;
}
