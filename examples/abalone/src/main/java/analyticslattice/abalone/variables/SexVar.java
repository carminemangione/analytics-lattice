package analyticslattice.abalone.variables;

import analyticslattice.GetColumnValue;
import analyticslattice.variable.CustomFunctionVariable;

public class SexVar extends CustomFunctionVariable<Sex> {

    public static final String NAME = "sex";

    public SexVar() {
        super(NAME);
    }

    @Override
    public Sex getCustom(GetColumnValue record) throws Exception {
        String sexString = record.getString("sex");
        return sexString == null ? null : Sex.valueOf(sexString);
    }
}
