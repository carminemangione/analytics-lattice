package analyticslattice.abalone.steps;

import analyticslattice.ColumnSet;
import analyticslattice.abalone.AbaloneDerbyDB;
import analyticslattice.reader.ResultSetRecordReader;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AA_ResultSetReader {

    public static void main(String[] args) throws Exception {
        try (AbaloneDerbyDB db = new AbaloneDerbyDB();
             Connection connection = db.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * from abalone");
             ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())) {

            printSome(reader);
        }
    }

    private static void printSome(ResultSetRecordReader reader) throws SQLException {
        ColumnSet<String> columnSet = reader.getColumnSet();
        System.out.println("Column Set Inferred From Result Meta Data: " + columnSet);
        System.out.println();
        System.out.println("The first ten records, converting all values to strings");
        for(int i = 0; reader.next() && i < 10; i++){
            ImmutableMap.Builder<String, String> printableMapBuilder = ImmutableMap.builder();
            for (String column : columnSet) {
                printableMapBuilder.put(column, reader.getString(column));
            }
            System.out.println(printableMapBuilder.build());
        }
    }
}