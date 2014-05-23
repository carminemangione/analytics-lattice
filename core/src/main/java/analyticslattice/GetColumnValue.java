package analyticslattice;

public interface GetColumnValue {

    String getString(String columnLabel) throws Exception;

    String getString(int columnIndex) throws Exception;

    Boolean getBoolean(String columnLabel) throws Exception;

    Boolean getBoolean(int columnIndex) throws Exception;

    boolean getBooleanPrimitive(String columnLabel) throws Exception;

    boolean getBooleanPrimitive(int columnIndex) throws Exception;

    Integer getInteger(String columnLabel) throws Exception;

    Integer getInteger(int columnIndex) throws Exception;

    int getIntegerPrimitive(String columnLabel) throws Exception;

    int getIntegerPrimitive(int columnIndex) throws Exception;

    Long getLong(String columnLabel) throws Exception;

    Long getLong(int columnIndex) throws Exception;

    long getLongPrimitive(String columnLabel) throws Exception;

    long getLongPrimitive(int columnIndex) throws Exception;

    Double getDouble(String columnLabel) throws Exception;

    Double getDouble(int columnIndex) throws Exception;

    double getDoublePrimitive(String columnLabel) throws Exception;

    double getDoublePrimitive(int columnIndex) throws Exception;

}
