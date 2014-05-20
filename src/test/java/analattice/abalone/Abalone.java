package analattice.abalone;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Abalone {

    public static ImmutableList<Abalone> fromCSVs(InputStream in){
        CSVReader csvReader = null;
        try{
            csvReader = new CSVReader(new InputStreamReader(in));
            ImmutableList.Builder<Abalone> builder = new ImmutableList.Builder<>();
            String[] csv;
            while((csv = csvReader.readNext()) != null){
                builder.add(fromCSV(csv));
            }
            return builder.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(csvReader != null){
                    csvReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Abalone fromCSV(String[] csv) {
        if (csv.length != 9) {
            throw new IllegalArgumentException("Must have 9 csv columns");
        }
        return new Abalone(Sex.valueOf(csv[0]),
                Double.valueOf(csv[1]),
                Double.valueOf(csv[2]),
                Double.valueOf(csv[3]),
                Double.valueOf(csv[4]),
                Double.valueOf(csv[5]),
                Double.valueOf(csv[6]),
                Double.valueOf(csv[7]),
                Integer.valueOf(csv[8])
        );
    }

    private final Sex sex;
    private final double length;
    private final double diameter;
    private final double height;
    private final double wholeWeight;

    private final double shuckedWeight;
    private final double visceraWeight;
    private final double shellWeight;
    private final int rings;

    public Abalone(Sex sex, double length, double diameter,
                   double height, double wholeWeight, double shuckedWeight,
                   double visceraWeight, double shellWeight, int rings) {
        this.sex = sex;
        this.length = length;
        this.diameter = diameter;
        this.height = height;
        this.wholeWeight = wholeWeight;
        this.shuckedWeight = shuckedWeight;
        this.visceraWeight = visceraWeight;
        this.shellWeight = shellWeight;
        this.rings = rings;
    }

    public void bind(PreparedStatement ps) throws SQLException {
        int i = 0;
        ps.setString(++i, sex.name());
        ps.setDouble(++i, length);
        ps.setDouble(++i, diameter);
        ps.setDouble(++i, height);
        ps.setDouble(++i, wholeWeight);

        ps.setDouble(++i, shuckedWeight);
        ps.setDouble(++i, visceraWeight);
        ps.setDouble(++i, shellWeight);
        ps.setInt(++i, rings);
    }

    public static enum Sex {
        M,
        F,
        I
    }

}
