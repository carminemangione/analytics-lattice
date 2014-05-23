package analyticslattice.reader.factory;

import analyticslattice.reader.DelegatingRecordReader;
import analyticslattice.reader.ResultSetRecordReader;
import com.google.common.collect.ImmutableList;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryRecordReaderFactory implements RecordReaderFactory {

    private final DataSource dataSource;
    private final String query;
    private final ImmutableList<String> sqlParameters;

    public QueryRecordReaderFactory(DataSource dataSource, String query) {
        this(dataSource, query, null);
    }

    public QueryRecordReaderFactory(DataSource dataSource, String query, ImmutableList<String> sqlParameters) {
        this.dataSource = dataSource;
        this.query = query;
        this.sqlParameters = sqlParameters == null || sqlParameters.isEmpty() ? null : sqlParameters;
    }

    @Override
    public QueryRecordReader openReader() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            if (sqlParameters != null) {
                for (int i = 0; i < sqlParameters.size(); i++) {
                    ps.setObject(i + 1, sqlParameters.get(i));
                }
            }

            ResultSet rs = ps.executeQuery();
            ResultSetRecordReader delegate = new ResultSetRecordReader(rs);
            return new QueryRecordReader(connection, ps, delegate);
        } catch (SQLException e) {
            try {
                closeAllSQL(ps, connection);
            } catch (SQLException closeE) {
                e.printStackTrace();
            }
            throw e;
        }
    }

    public static class QueryRecordReader extends DelegatingRecordReader {
        private final Connection connection;
        private final PreparedStatement ps;

        public QueryRecordReader(Connection connection, PreparedStatement ps, ResultSetRecordReader delegate) {
            super(delegate);
            this.connection = connection;
            this.ps = ps;
        }

        @Override
        public void close() throws Exception {
            closeAllSQL(super.delegate, ps, connection);
        }
    }

    public static void closeAllSQL(AutoCloseable... closeables) throws SQLException {
        ImmutableList.Builder<SQLException> exceptionsBuilder = ImmutableList.builder();
        for (AutoCloseable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (SQLException e) {
                exceptionsBuilder.add(e);
            } catch (Exception e) {
                exceptionsBuilder.add(new SQLException(e));
            }
        }
        ImmutableList<SQLException> sqlExceptions = exceptionsBuilder.build();
        if (sqlExceptions.size() >= 1) {
            throw sqlExceptions.get(0);
        }
    }
}
