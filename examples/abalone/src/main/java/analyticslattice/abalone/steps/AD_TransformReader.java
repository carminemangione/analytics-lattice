package analyticslattice.abalone.steps;

import analyticslattice.abalone.AbaloneDerbyDB;
import analyticslattice.abalone.variables.IsMaleVar;
import analyticslattice.abalone.variables.Sex;
import analyticslattice.abalone.variables.SexVar;
import analyticslattice.reader.TransformReader;
import analyticslattice.reader.factory.QueryRecordReaderFactory;
import analyticslattice.variable.FunctionVariable;
import com.google.common.collect.ImmutableList;

public class AD_TransformReader {

    private static final ImmutableList<FunctionVariable> variables = ImmutableList.<FunctionVariable>of(
            new SexVar(),
            new IsMaleVar()
    );

    public static void main(String[] args) throws Exception {
        try (AbaloneDerbyDB db = new AbaloneDerbyDB()) {
            QueryRecordReaderFactory factory = new QueryRecordReaderFactory(db,
                    "SELECT * from abalone");
            try (TransformReader reader = new TransformReader(factory.openReader(), variables)) {
                for (int i = 0; reader.next() && i < 10; i++) {
                    Sex sex = reader.getCustom(SexVar.NAME);
                    Boolean isMale = reader.getBoolean(IsMaleVar.NAME);
                    System.out.println(SexVar.NAME + "=" + sex.name() + "\t" + IsMaleVar.NAME + "=" + isMale);
                }
            }
        }
    }
}
