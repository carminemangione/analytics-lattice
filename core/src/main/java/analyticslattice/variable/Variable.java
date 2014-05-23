package analyticslattice.variable;

import com.google.common.base.Function;

public interface Variable {

    Function<Variable, String> TO_NAME = new Function<Variable, String>() {
        @Override
        public String apply(Variable v) {
            return v == null ? null : v.getName();
        }
    };

    String getName();

}
