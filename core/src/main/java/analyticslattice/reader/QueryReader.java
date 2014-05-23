package analyticslattice.reader;

import analyticslattice.ColumnSet;

import java.sql.Connection;
import java.util.List;

public class QueryReader implements RecordReader {

    public QueryReader(String query, List<Object> sqlParameters) {

    }

    @Override
    public ColumnSet<String> getColumnSet() {
        return null;
    }

    @Override
    public boolean next() throws Exception {
        return false;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public String getString(String columnLabel) throws Exception {
        return null;
    }

    @Override
    public String getString(int columnIndex) throws Exception {
        return null;
    }

    @Override
    public Boolean getBoolean(String columnLabel) throws Exception {
        return null;
    }

    @Override
    public Boolean getBoolean(int columnIndex) throws Exception {
        return null;
    }

    @Override
    public boolean getBooleanPrimitive(String columnLabel) throws Exception {
        return false;
    }

    @Override
    public boolean getBooleanPrimitive(int columnIndex) throws Exception {
        return false;
    }

    @Override
    public Integer getInteger(String columnLabel) throws Exception {
        return null;
    }

    @Override
    public Integer getInteger(int columnIndex) throws Exception {
        return null;
    }

    @Override
    public int getIntegerPrimitive(String columnLabel) throws Exception {
        return 0;
    }

    @Override
    public int getIntegerPrimitive(int columnIndex) throws Exception {
        return 0;
    }

    @Override
    public Long getLong(String columnLabel) throws Exception {
        return null;
    }

    @Override
    public Long getLong(int columnIndex) throws Exception {
        return null;
    }

    @Override
    public long getLongPrimitive(String columnLabel) throws Exception {
        return 0;
    }

    @Override
    public long getLongPrimitive(int columnIndex) throws Exception {
        return 0;
    }

    @Override
    public Double getDouble(String columnLabel) throws Exception {
        return null;
    }

    @Override
    public Double getDouble(int columnIndex) throws Exception {
        return null;
    }

    @Override
    public double getDoublePrimitive(String columnLabel) throws Exception {
        return 0;
    }

    @Override
    public double getDoublePrimitive(int columnIndex) throws Exception {
        return 0;
    }
}
