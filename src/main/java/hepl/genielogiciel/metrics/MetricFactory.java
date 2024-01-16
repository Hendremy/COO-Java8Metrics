package hepl.genielogiciel.metrics;

public interface MetricFactory {
    Metric create(String id) throws MetricFactoryException;
    Metric create(String id, Metric wrappee) throws MetricFactoryException;
}
