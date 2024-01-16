package hepl.genielogiciel.metrics;

public interface MetricFactory {
    Metric create(String id);
    Metric create(String id, Metric wrappee);
}
