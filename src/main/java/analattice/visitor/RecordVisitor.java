package analattice.visitor;

import analattice.GetColumnValue;

public interface RecordVisitor {

    void visit(GetColumnValue record) throws Exception;

}
