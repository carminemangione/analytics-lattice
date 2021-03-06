package analyticslattice.abalone;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public abstract class AbaloneDataSource  implements DataSource {

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    protected static class InsertAbalone {

        private static final String CREATE_TABLE;
        private static final String INSERT;

        static {
            try {
                CREATE_TABLE = Resources.toString(Resources.getResource("analyticslattice/abalone/create_table.sql"), Charset.defaultCharset());
                INSERT = Resources.toString(Resources.getResource("analyticslattice/abalone/insert.sql"), Charset.defaultCharset());
            } catch (IOException e) {
                throw new RuntimeException("Could not load sql for " + InsertAbalone.class.getCanonicalName());
            }
        }

        private final ImmutableList<Abalone> abalones;

        public InsertAbalone(ImmutableList<Abalone> abalones) {
            this.abalones = abalones;
        }

        public void createTableAndInsert(Connection connection) throws SQLException {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(CREATE_TABLE)) {
                ps.execute();
            }
            try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
                for (Abalone abalone : abalones) {
                    abalone.bind(ps);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            connection.setAutoCommit(true);
        }
    }

}
