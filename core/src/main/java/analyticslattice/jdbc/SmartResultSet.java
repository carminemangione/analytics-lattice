package analyticslattice.jdbc;

import analyticslattice.GetColumnValue;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Is 0 indexed. Normal Result sets are 1 indexed
 */
public class SmartResultSet implements GetColumnValue, AutoCloseable {

    private final ResultSet rs;

    public SmartResultSet(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return rs.getString(columnLabel);
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return rs.getString(columnIndex+1);
    }

    @Override
    public Boolean getBoolean(String columnLabel) throws SQLException {
        return isNull(columnLabel) ? null : rs.getBoolean(columnLabel);
    }

    @Override
    public Boolean getBoolean(int columnIndex) throws SQLException {
        return isNull(columnIndex) ? null : rs.getBoolean(columnIndex+1);
    }

    @Override
    public boolean getBooleanPrimitive(String columnLabel) throws SQLException {
        exceptIfNull(columnLabel);
        return rs.getBoolean(columnLabel);
    }

    @Override
    public boolean getBooleanPrimitive(int columnIndex) throws SQLException {
        exceptIfNull(columnIndex);
        return rs.getBoolean(columnIndex+1);
    }

    @Override
    public Integer getInteger(String columnLabel) throws SQLException {
        return isNull(columnLabel) ? null : rs.getInt(columnLabel);
    }

    @Override
    public Integer getInteger(int columnIndex) throws SQLException {
        return isNull(columnIndex) ? null : rs.getInt(columnIndex+1);
    }

    @Override
    public int getIntegerPrimitive(String columnLabel) throws SQLException {
        exceptIfNull(columnLabel);
        return rs.getInt(columnLabel);
    }

    @Override
    public int getIntegerPrimitive(int columnIndex) throws SQLException {
        exceptIfNull(columnIndex);
        return rs.getInt(columnIndex+1);
    }

    @Override
    public Long getLong(String columnLabel) throws SQLException {
        return isNull(columnLabel) ? null : rs.getLong(columnLabel);
    }

    @Override
    public Long getLong(int columnIndex) throws SQLException {
        return isNull(columnIndex) ? null : rs.getLong(columnIndex+1);
    }

    @Override
    public long getLongPrimitive(String columnLabel) throws SQLException {
        exceptIfNull(columnLabel);
        return rs.getLong(columnLabel);
    }

    @Override
    public long getLongPrimitive(int columnIndex) throws SQLException {
        exceptIfNull(columnIndex);
        return rs.getLong(columnIndex+1);
    }

    @Override
    public Double getDouble(String columnLabel) throws SQLException {
        return isNull(columnLabel) ? null : rs.getDouble(columnLabel);
    }

    @Override
    public Double getDouble(int columnIndex) throws SQLException {
        return isNull(columnIndex) ? null : rs.getDouble(columnIndex+1);
    }

    @Override
    public double getDoublePrimitive(String columnLabel) throws SQLException {
        exceptIfNull(columnLabel);
        return rs.getDouble(columnLabel);
    }

    @Override
    public double getDoublePrimitive(int columnIndex) throws SQLException {
        exceptIfNull(columnIndex);
        return rs.getDouble(columnIndex+1);
    }

    @Override
    public <T> T getCustom(String name, Class<T> clazz) throws Exception {
        throw new UnsupportedOperationException("cannot get custom objects from result sets");
    }

    @Override
    public <T> T getCustom(int columnIndex, Class<T> clazz) throws Exception {
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

    public boolean next() throws SQLException {
        return rs.next();
    }

    @Override
    public void close() throws SQLException {
        rs.close();
    }

    private void exceptIfNull(String columnLabel) throws SQLException {
        if(isNull(columnLabel)){
            String message = String.format("Could not convert null value for column \"%s\" to primitive", columnLabel);
            throw new NullPointerException(message);
        }
    }

    private boolean isNull(String columnLabel) throws SQLException {
        return rs.getObject(columnLabel) == null;
    }

    private void exceptIfNull(int columnIndex) throws SQLException {
        if(isNull(columnIndex)){
            String columnLabel = rs.getMetaData().getColumnLabel(columnIndex + 1);
            String message = String.format("Could not convert null value for column \"%s\" to primitive", columnLabel);
            throw new NullPointerException(message);
        }
    }

    private boolean isNull(int columnIndex) throws SQLException {
        return rs.getObject(columnIndex+1) == null;
    }
}
