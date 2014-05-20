package analattice.stats.description;

public interface PrintableDescription {
    String header();

    void toString(StringBuilder stringBuilder);
}
