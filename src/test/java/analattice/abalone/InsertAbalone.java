package analattice.abalone;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertAbalone {

    private static final String CREATE_TABLE;
    private static final String INSERT;
    static {
        try {
            CREATE_TABLE = Resources.toString(Resources.getResource("abalone/create_table.sql"), Charset.defaultCharset());
            INSERT = Resources.toString(Resources.getResource("abalone/insert.sql"), Charset.defaultCharset());
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
        try(PreparedStatement ps = connection.prepareStatement(CREATE_TABLE)){
            ps.execute();
        }
        try(PreparedStatement ps = connection.prepareStatement(INSERT)){
            for (Abalone abalone : abalones) {
                abalone.bind(ps);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        connection.setAutoCommit(true);
    }
}
