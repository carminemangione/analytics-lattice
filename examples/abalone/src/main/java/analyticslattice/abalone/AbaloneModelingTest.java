package analyticslattice.abalone;

import analyticslattice.ColumnSet;
import analyticslattice.DerbyRule;
import analyticslattice.GetColumnValue;
import analyticslattice.reader.RecordReader;
import analyticslattice.reader.ResultSetRecordReader;
import analyticslattice.reader.TransformReader;
import analyticslattice.variable.BooleanPrimitiveFunctionVariable;
import analyticslattice.variable.FunctionVariable;
import analyticslattice.visitor.DescriptionVisitors;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;


public class AbaloneModelingTest {
//
//    @Rule
//    public DerbyRule derbyRule = new DerbyRule();
//
//    @Before
//    public void setUp() throws Exception {
//        ImmutableList<Abalone> abalones = Abalone.fromCSVs(Resources.getResource("analyticslattice/abalone/abalone.data").openStream());
//        try(Connection connection = derbyRule.openConnection()){
//            new InsertAbalone(abalones).createTableAndInsert(connection);
//        }
//    }
//
//    @Test
//    public void recordSetColumns() throws Exception {
//        try(Connection connection = derbyRule.openConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT * from abalone");
//            ResultSetRecordReader reader = new ResultSetRecordReader(ps.executeQuery())){
//            ColumnSet expectedColumnSet = ColumnSet.from(Arrays.asList("sex", "length", "diameter", "height", "whole_weight",
//                    "shucked_weight", "viscera_weight", "shell_weight", "rings"));
//            assertEquals(expectedColumnSet, reader.getColumnSet());
//        }
//    }
//
//    @Test
//    public void aTransformReader() throws Exception {
//        ImmutableList<FunctionVariable> variables = ImmutableList.of((FunctionVariable)new IsMale());
//        try(Connection connection = derbyRule.openConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT * from abalone");
//            RecordReader reader = new TransformReader(new ResultSetRecordReader(ps.executeQuery()), variables)){
//            while(reader.next()){
//                String sex = reader.getString("sex");
//                if("M".equals(sex)){
//                    assertTrue(reader.getBoolean("is_male"));
//                    assertEquals(new Integer(1), reader.getInteger("is_male"));
//                    assertEquals(new Long(1), reader.getLong("is_male"));
//                    assertEquals(new Double(1.0), reader.getDouble("is_male"));
//                    assertEquals("true", reader.getString("is_male"));
//                }else{
//                    assertFalse(reader.getBoolean("is_male"));
//                    assertEquals(new Integer(0), reader.getInteger("is_male"));
//                    assertEquals(new Long(0), reader.getLong("is_male"));
//                    assertEquals(new Double(0.0), reader.getDouble("is_male"));
//                    assertEquals("false", reader.getString("is_male"));
//                }
//            }
//        }
//    }
//
//    @Test
//    public void doSomeStats() throws Exception {
//        ImmutableList<FunctionVariable> variables = ImmutableList.of((FunctionVariable) new IsMale());
//        DescriptionVisitors descriptionVisitors = DescriptionVisitors.builder()
//                .addDoubles("length", "diameter", "height", "whole_weight",
//                        "shucked_weight", "viscera_weight", "shell_weight")
//                .addDoublePrimitives("length", "diameter", "height", "whole_weight",
//                        "shucked_weight", "viscera_weight", "shell_weight")
//                .addInteger("rings")
//                .addIntegerPrimitive("rings")
//                .addBoolean("is_male")
//                .addBooleanPrimitive("is_male")
//                .build();
//        try(Connection connection = derbyRule.openConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT * from abalone");
//            RecordReader reader = new TransformReader(new ResultSetRecordReader(ps.executeQuery()), variables)){
//            while(reader.next()){
//                descriptionVisitors.visit(reader);
//            }
//        }
//        System.out.println(descriptionVisitors);
//    }
//
//    private static class IsMale extends BooleanPrimitiveFunctionVariable{
//
//        protected IsMale() {
//            super("is_male");
//        }
//
//        @Override
//        public boolean getBooleanPrimitive(GetColumnValue record) throws Exception {
//            return "M".equalsIgnoreCase(record.getString("sex"));
//        }
//    }
}
