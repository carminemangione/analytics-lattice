package analyticslattice.stats.description;

public interface PrintableDescription {
    String header();

    void toString(StringBuilder stringBuilder);
}
