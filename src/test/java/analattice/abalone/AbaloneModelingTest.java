package analattice.abalone;

import analattice.ColumnSet;
import analattice.DerbyRule;
import analattice.GetColumnValue;
import analattice.reader.RecordReader;
import analattice.reader.ResultSetRecordReader;
import analattice.reader.TransformReader;
import analattice.variable.BooleanFunctionVariable;
import analattice.variable.FunctionVariable;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbaloneModelingTest {

    @Rule
    public DerbyRule derbyRule = new DerbyRule();

    @Before
    public void setUp() throws Exception {
        ImmutableList<Abalone> abalones = Abalone.fromCSVs(Resources.getResource("abalone/abalone.data").openStream());
        try(Connection connection = derbyRule.openConnection()){
            new InsertAbalone(abalones).createTableAndInsert(connection);
        }
    }

    @Test
    public void recordSetColumns() throws Exception {
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * from abalone");
            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
            ColumnSet<String> expectedColumnSet = ColumnSet.from(Arrays.asList("sex", "length", "diameter", "height", "whole_weight",
                    "shucked_weight", "viscera_weight", "shell_weight", "rings"));
            assertEquals(expectedColumnSet, reader.getColumnSet());
        }
    }

    @Test
    public void aTransformReader() throws Exception {
        ImmutableList<FunctionVariable> variables = ImmutableList.of((FunctionVariable)new IsMale());
        try(Connection connection = derbyRule.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * from abalone");
            RecordReader reader = new TransformReader(new ResultSetRecordReader(ps.executeQuery()), variables)){
            while(reader.next()){
                String sex = reader.getString("sex");
                if("M".equals(sex)){
                    assertTrue(reader.getBoolean("is_male"));
                    assertEquals(new Integer(1), reader.getInteger("is_male"));
                    assertEquals(new Long(1), reader.getLong("is_male"));
                    assertEquals(new Double(1.0), reader.getDouble("is_male"));
                    assertEquals("true", reader.getString("is_male"));
                }else{
                    assertFalse(reader.getBoolean("is_male"));
                    assertEquals(new Integer(0), reader.getInteger("is_male"));
                    assertEquals(new Long(0), reader.getLong("is_male"));
                    assertEquals(new Double(0.0), reader.getDouble("is_male"));
                    assertEquals("false", reader.getString("is_male"));
                }
            }
        }
    }

    private static class IsMale extends BooleanFunctionVariable{

        protected IsMale() {
            super("is_male");
        }

        @Override
        public Boolean getBoolean(GetColumnValue record) throws Exception{
            return "M".equalsIgnoreCase(record.getString("sex"));
        }
    }
}