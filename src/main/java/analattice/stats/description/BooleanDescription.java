package analattice.stats.description;

public class BooleanDescription implements PrintableDescription {

    private int countObserved = 0;
    private int countFalse = 0;
    private int countTrue = 0;
    private int countNull = 0;

    public BooleanDescription() {
    }

    public void visit(Boolean x) {
        countObserved++;
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

    public int getCountObserved() {
        return countObserved;
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
        return "countTotal\tcountFalse\tcountTrue\tcountNull";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(countObserved).append("\t")
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
