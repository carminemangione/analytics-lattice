package analyticslattice.abalone.variables;

import analyticslattice.GetColumnValue;
import analyticslattice.variable.BooleanFunctionVariable;

public class IsMaleVar extends BooleanFunctionVariable {

    public static final String NAME = "is_male";

    public IsMaleVar() {
        super(NAME);
    }

    @Override
    public Boolean getBoolean(GetColumnValue record) throws Exception {
        return "M".equals(record.getString("sex"));
    }
}
