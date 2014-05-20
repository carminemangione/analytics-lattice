package analattice.stats.description;

public class BooleanPrimitiveDescription implements PrintableDescription {

    private int countObserved = 0;
    private int countFalse = 0;
    private int countTrue = 0;

    public BooleanPrimitiveDescription() {
    }

    public void visit(boolean x) {
        countObserved++;
        if (x) {
            countTrue++;
        } else {
            countFalse++;
        }
    }

    public int getCountObserved() {
        return countObserved;
    }

    public int getCountFalse() {
        return countFalse;
    }

    public int getCountTrue() {
        return countTrue;
    }

    @Override
    public String header() {
        return "countTotal\tcountFalse\tcountTrue";
    }

    @Override
    public void toString(StringBuilder stringBuilder) {
        stringBuilder
                .append(countObserved).append("\t")
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
