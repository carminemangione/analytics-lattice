package analyticslattice.abalone.steps;

import analyticslattice.abalone.AbaloneDerbyDB;
import analyticslattice.reader.RecordReader;
import analyticslattice.reader.factory.QueryRecordReaderFactory;
import analyticslattice.visitor.DescriptionVisitors;

public class AC_DescriptionVisitor {

    public static void main(String[] args) throws Exception {
        DescriptionVisitors descriptionVisitors = DescriptionVisitors.builder()
                .addDoubles("length", "diameter", "height", "whole_weight",
                        "shucked_weight", "viscera_weight", "shell_weight")
                .addInteger("rings")
                .addString("sex")
                .build();
        visitAllAbalone(descriptionVisitors);

        System.out.println("Describe the raw columns (still tab separated)");
        System.out.println(descriptionVisitors);
    }

    private static void visitAllAbalone(DescriptionVisitors descriptionVisitors) throws Exception {
        try (AbaloneDerbyDB db = new AbaloneDerbyDB()) {
            QueryRecordReaderFactory factory = new QueryRecordReaderFactory(db,
                    "SELECT * from abalone");
            try (RecordReader reader = factory.openReader()) {
                while (reader.next()) {
                    descriptionVisitors.visit(reader);
                }
            }
        }
    }
}
