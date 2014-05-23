package analyticslattice.stats.description;

public class BooleanDescription implements PrintableDescription {

    private int totalCount = 0;
    private int countFalse = 0;
    private int countTrue = 0;
    private int countNull = 0;

    public BooleanDescription() {
    }

    public void visit(Boolean x) {
        totalCount++;
        if (x == null) {
            countNull++;
        } else {
            if (x) {
                countTrue++;
            } else {
                countFalse++;
            }
        }
    }

    public int getTotalCount() {
        return totalCount;
    }


    public int getCountNull() {
        return countNull;
    }

    public int getCountFalse() {
        return countFalse;
    }

    public int getCountTrue() {
        return countTrue;
    }

    @Override
    public String header() {
        return "totalCount\tcountFalse\tcountTrue\tcountNull";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(totalCount).append("\t")
                .append(countFalse).append("\t")
                .append(countTrue).append("\t")
                .append(countNull);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(header()).append("\n");
        toString(stringBuilder);
        return stringBuilder.toString();
    }
}
