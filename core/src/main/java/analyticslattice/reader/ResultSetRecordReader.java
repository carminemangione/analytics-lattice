package analyticslattice.reader;

import analyticslattice.ColumnSet;
import analyticslattice.jdbc.SmartResultSet;
import com.google.common.collect.ImmutableList;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetRecordReader implements RecordReader {

    private final ColumnSet columnSet;
    private final SmartResultSet rs;

    public ResultSetRecordReader(ResultSet rs) throws SQLException {
        this.rs = new SmartResultSet(rs);
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            builder.add(metaData.getColumnName(i).toLowerCase());
        }
        this.columnSet = ColumnSet.from(builder.build());
    }

    @Override
    public ColumnSet getColumnSet() {
        return columnSet;
    }

    @Override
    public boolean next() throws SQLException {
        return rs.next();
    }

    @Override
    public void close() throws SQLException {
        rs.close();
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return rs.getString(columnLabel);
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public Boolean getBoolean(String columnLabel) throws SQLException {
        return rs.getBoolean(columnLabel);
    }

    @Override
    public Boolean getBoolean(int columnIndex) throws SQLException {
        return rs.getBoolean(columnIndex);
    }

    @Override
    public boolean getBooleanPrimitive(String columnLabel) throws SQLException {
        return rs.getBooleanPrimitive(columnLabel);
    }

    @Override
    public boolean getBooleanPrimitive(int columnIndex) throws SQLException {
        return rs.getBooleanPrimitive(columnIndex);
    }

    @Override
    public Integer getInteger(String columnLabel) throws SQLException {
        return rs.getInteger(columnLabel);
    }

    @Override
    public Integer getInteger(int columnIndex) throws SQLException {
        return rs.getInteger(columnIndex);
    }

    @Override
    public int getIntegerPrimitive(String columnLabel) throws SQLException {
        return rs.getIntegerPrimitive(columnLabel);
    }

    @Override
    public int getIntegerPrimitive(int columnIndex) throws SQLException {
        return rs.getIntegerPrimitive(columnIndex);
    }

    @Override
    public Long getLong(String columnLabel) throws SQLException {
        return rs.getLong(columnLabel);
    }

    @Override
    public Long getLong(int columnIndex) throws SQLException {
        return rs.getLong(columnIndex);
    }

    @Override
    public long getLongPrimitive(String columnLabel) throws SQLException {
        return rs.getLongPrimitive(columnLabel);
    }

    @Override
    public long getLongPrimitive(int columnIndex) throws SQLException {
        return rs.getLongPrimitive(columnIndex);
    }

    @Override
    public Double getDouble(String columnLabel) throws SQLException {
        return rs.getDouble(columnLabel);
    }

    @Override
    public Double getDouble(int columnIndex) throws SQLException {
        return rs.getDouble(columnIndex);
    }

    @Override
    public double getDoublePrimitive(String columnLabel) throws SQLException {
        return rs.getDoublePrimitive(columnLabel);
    }

    @Override
    public double getDoublePrimitive(int columnIndex) throws SQLException {
        return rs.getDoublePrimitive(columnIndex);
    }

    @Override
    public <T> T getCustom(String name, Class<T> clazz) {
        throw new UnsupportedOperationException("cannot get custom objects from result sets");
    }

    @Override
    public <T> T getCustom(int columnIndex, Class<T> clazz) {
        throw new UnsupportedOperationException("cannot get custom objects from result sets");
    }

    @Override
    public <T> T getCustom(String name) throws Exception {
        throw new UnsupportedOperationException("cannot get custom objects from result sets");
    }

    @Override
    public <T> T getCustom(int columnIndex) throws Exception {
        throw new UnsupportedOperationException("cannot get custom objects from result sets");
    }
}
