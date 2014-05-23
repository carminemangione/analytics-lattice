package analyticslattice.reader;

import analyticslattice.ColumnSet;

public class DelegatingRecordReader implements RecordReader {

    protected final RecordReader delegate;

    public DelegatingRecordReader(RecordReader delegate) {
        this.delegate = delegate;
    }

    @Override
    public ColumnSet getColumnSet() {
        return delegate.getColumnSet();
    }

    @Override
    public boolean next() throws Exception {
        return delegate.next();
    }

    @Override
    public String getString(String columnLabel) throws Exception {
        return delegate.getString(columnLabel);
    }

    @Override
    public String getString(int columnIndex) throws Exception {
        return delegate.getString(columnIndex);
    }

    @Override
    public Boolean getBoolean(String columnLabel) throws Exception {
        return delegate.getBoolean(columnLabel);
    }

    @Override
    public Boolean getBoolean(int columnIndex) throws Exception {
        return delegate.getBoolean(columnIndex);
    }

    @Override
    public boolean getBooleanPrimitive(String columnLabel) throws Exception {
        return delegate.getBooleanPrimitive(columnLabel);
    }

    @Override
    public boolean getBooleanPrimitive(int columnIndex) throws Exception {
        return delegate.getBooleanPrimitive(columnIndex);
    }

    @Override
    public Integer getInteger(String columnLabel) throws Exception {
        return delegate.getInteger(columnLabel);
    }

    @Override
    public Integer getInteger(int columnIndex) throws Exception {
        return delegate.getInteger(columnIndex);
    }

    @Override
    public int getIntegerPrimitive(String columnLabel) throws Exception {
        return delegate.getIntegerPrimitive(columnLabel);
    }

    @Override
    public int getIntegerPrimitive(int columnIndex) throws Exception {
        return delegate.getIntegerPrimitive(columnIndex);
    }

    @Override
    public Long getLong(String columnLabel) throws Exception {
        return delegate.getLong(columnLabel);
    }

    @Override
    public Long getLong(int columnIndex) throws Exception {
        return delegate.getLong(columnIndex);
    }

    @Override
    public long getLongPrimitive(String columnLabel) throws Exception {
        return delegate.getLongPrimitive(columnLabel);
    }

    @Override
    public long getLongPrimitive(int columnIndex) throws Exception {
        return delegate.getLongPrimitive(columnIndex);
    }

    @Override
    public Double getDouble(String columnLabel) throws Exception {
        return delegate.getDouble(columnLabel);
    }

    @Override
    public Double getDouble(int columnIndex) throws Exception {
        return delegate.getDouble(columnIndex);
    }

    @Override
    public double getDoublePrimitive(String columnLabel) throws Exception {
        return delegate.getDoublePrimitive(columnLabel);
    }

    @Override
    public double getDoublePrimitive(int columnIndex) throws Exception {
        return delegate.getDoublePrimitive(columnIndex);
    }

    @Override
    public <T> T getCustom(String name, Class<T> clazz) throws Exception {
        return delegate.getCustom(name, clazz);
    }

    @Override
    public <T> T getCustom(int columnIndex, Class<T> clazz) throws Exception {
        return delegate.getCustom(columnIndex, clazz);
    }

    @Override
    public <T> T getCustom(String name) throws Exception {
        return delegate.getCustom(name);
    }

    @Override
    public <T> T getCustom(int columnIndex) throws Exception {
        return delegate.getCustom(columnIndex);
    }

    @Override
    public void close() throws Exception {
        delegate.close();
    }
}
