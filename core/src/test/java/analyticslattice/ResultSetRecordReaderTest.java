package analyticslattice;

import analyticslattice.reader.ResultSetRecordReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ResultSetRecordReaderTest {

    @Rule
    public DerbyRule derbyRule = new DerbyRule();

    @Before
    public void setUp() throws Exception {
        try(Connection connection = derbyRule.openConnection()){
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate("CREATE TABLE foo(bar VARCHAR(100))");
                statement.executeUpdate("INSERT INTO foo(bar) VALUES ('a'), ('b'), ('c')");
            }
        }
    }

    @Test
    public void noRows() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM foo WHERE bar > 'z' ORDER BY bar asc");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            assertFalse(reader.next());
        }
    }

    @Test
    public void rows() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM foo ORDER BY bar asc");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            assertTrue(reader.next());
            assertEquals("a", reader.getString("bar"));
            assertTrue(reader.next());
            assertEquals("b", reader.getString("bar"));
            assertTrue(reader.next());
            assertEquals("c", reader.getString("bar"));
            assertFalse(reader.next());
        }
    }

    @Test
    public void columnSetFromTableColumns() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT bar from foo");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            ColumnSet expectedColumnSet = ColumnSet.from(Arrays.asList("bar"));
            assertEquals(expectedColumnSet, reader.getColumnSet());
        }
    }

    @Test
    public void columnSetFromAliasedColumns() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT bar as barz from foo");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            ColumnSet expectedColumnSet = ColumnSet.from(Arrays.asList("barz"));
            assertEquals(expectedColumnSet, reader.getColumnSet());
        }
    }

    @Test
    public void columnSetFromUnAliasedColumnsFromSqlFunctions() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT count(0) from foo");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            ColumnSet expectedColumnSet = ColumnSet.from(Arrays.asList("1"));
            assertEquals(expectedColumnSet, reader.getColumnSet());
        }
    }

    @Test
    public void nullColumn() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT bar, cast(NULL as INT) as bar2 from foo");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            while(reader.next()){
                System.out.println(reader.getInteger("bar2"));
                System.out.println(reader.getLong("bar2"));
                System.out.println(reader.getString("bar2"));
                System.out.println(reader.getBoolean("bar2"));
            }
        }
    }
}
