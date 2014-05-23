package analyticslattice.abalone;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbaloneDerbyDB extends AbaloneDataSource implements AutoCloseable {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:";
    private final String databaseName = "abalone";

    public AbaloneDerbyDB() {
        try {
            Class.forName(DRIVER);
            //noinspection UnusedDeclaration,EmptyTryBlock
            try (Connection connection = DriverManager.getConnection(URL + "memory:" + databaseName + ";create=true")) {
                ImmutableList<Abalone> abalones = Abalone.fromCSVs(Resources.getResource("analyticslattice/abalone/abalone.data").openStream());
                new InsertAbalone(abalones).createTableAndInsert(connection);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        //noinspection UnusedDeclaration,EmptyTryBlock
        try (Connection connection = DriverManager.getConnection(URL + "memory:" + databaseName + ";drop=true")) {

        } catch (SQLException e) {
            if (e.getErrorCode() != 45000) {
                e.printStackTrace();
            }
        }
    }

    public void dropTableIfExists(Connection connection, String tableName) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE " + tableName)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            boolean canIgnore = "42Y55".equalsIgnoreCase(e.getSQLState()) || "42Y07".equalsIgnoreCase(e.getSQLState());
            if (!canIgnore)
                throw e;
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL + "memory:" + databaseName + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
