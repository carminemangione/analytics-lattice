package analyticslattice.visitor;

import analyticslattice.GetColumnValue;

public interface RecordVisitor {

    void visit(GetColumnValue record) throws Exception;

}
