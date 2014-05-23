package analyticslattice;

public interface GetRecordValue {

    String getString(GetColumnValue record) throws Exception;

    Boolean getBoolean(GetColumnValue record) throws Exception;

    boolean getBooleanPrimitive(GetColumnValue record) throws Exception;

    Integer getInteger(GetColumnValue record) throws Exception;

    int getIntegerPrimitive(GetColumnValue record) throws Exception;

    Long getLong(GetColumnValue record) throws Exception;

    long getLongPrimitive(GetColumnValue record) throws Exception;

    Double getDouble(GetColumnValue record) throws Exception;

    double getDoublePrimitive(GetColumnValue record) throws Exception;

}
