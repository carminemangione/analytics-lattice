package analyticslattice.abalone.steps;

import analyticslattice.abalone.AbaloneDerbyDB;
import analyticslattice.reader.RecordReader;
import analyticslattice.reader.factory.QueryRecordReaderFactory;
import analyticslattice.stats.description.DoubleDescription;
import com.google.common.collect.ImmutableList;

public class AB_QueryReader {

    public static void main(String[] args) throws Exception {
        DoubleDescription infantLengthDescription = new DoubleDescription();
        try (AbaloneDerbyDB db = new AbaloneDerbyDB()) {
            QueryRecordReaderFactory factory = new QueryRecordReaderFactory(db,
                    "SELECT * from abalone WHERE sex = ?",
                    ImmutableList.of("I"));

            try (RecordReader reader = factory.openReader()) {
                while (reader.next()) {
                    infantLengthDescription.visit(reader.getDouble("length"));
                }
            }

        }
        System.out.println("Some information about infant length (tab separated values)");
        System.out.println(infantLengthDescription);
    }
}
