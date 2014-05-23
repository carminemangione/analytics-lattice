package analyticslattice.stats.description;

import analyticslattice.stats.histogram.Histogram;

import java.util.Set;

public class HistogramDescription<E> {

    private final Histogram<E> histogram;

    public HistogramDescription() {
        this(Histogram.DEFAULT_SIZE);
    }

    public HistogramDescription(int histogramMaxDistinctValues) {
        this.histogram = new Histogram<E>(histogramMaxDistinctValues);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("totalCount\tcountNull");
        Set<E> elements = histogram.elements();
        for (E e : elements) {
            stringBuilder.append("\t").append(e.toString());
        }
        stringBuilder.append("\n")
        .append(histogram.getTotalCount()).append("\t")
        .append(histogram.countOf(null));
        for (E element : elements) {
            stringBuilder.append("\t").append(histogram.countOf(element));
        }
        return stringBuilder.toString();
    }
}
