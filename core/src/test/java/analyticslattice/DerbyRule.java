package analyticslattice;

import org.junit.rules.ExternalResource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DerbyRule extends ExternalResource {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:";
    private final String databaseName;
    private Connection connectionToKeepItOpen;

    public DerbyRule() {
        this("DerbyRule");
    }

    public DerbyRule(String databaseName) {
        try {
            this.databaseName = databaseName;
            Class.forName(DRIVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void before() throws Throwable {
        try (Connection connection =
                     DriverManager.getConnection(URL + "memory:" + databaseName + ";create=true")) {
            //just to close
        }
    }

    @Override
    protected void after() {
        try (Connection connection =
                     DriverManager.getConnection(URL + "memory:" + databaseName + ";drop=true")) {

        } catch (SQLException e) {
            if (e.getErrorCode() != 45000) {
                e.printStackTrace();
            }
        }
    }

    public Connection openConnection() {
        try {
            return DriverManager.getConnection(URL + "memory:" + databaseName + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
