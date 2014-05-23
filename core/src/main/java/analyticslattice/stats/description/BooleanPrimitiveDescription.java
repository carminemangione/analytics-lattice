package analyticslattice.stats.description;

public class BooleanPrimitiveDescription implements PrintableDescription {

    private int totalCount = 0;
    private int countFalse = 0;
    private int countTrue = 0;

    public BooleanPrimitiveDescription() {
    }

    public void visit(boolean x) {
        totalCount++;
        if (x) {
            countTrue++;
        } else {
            countFalse++;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCountFalse() {
        return countFalse;
    }

    public int getCountTrue() {
        return countTrue;
    }

    @Override
    public String header() {
        return "totalCount\tcountFalse\tcountTrue";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(totalCount).append("\t")
                .append(countFalse).append("\t")
                .append(countTrue);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(header()).append("\n");
        toString(stringBuilder);
        return stringBuilder.toString();
    }
}
