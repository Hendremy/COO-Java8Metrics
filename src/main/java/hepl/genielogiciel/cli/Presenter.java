package hepl.genielogiciel.cli;

import java.util.Map;

public interface Presenter {
    void presentMetrics(Map<String, Double> metrics, Map<String, Double> maxValues, String filePath);
    void presentMetrics(Map<String, Double> metrics, Map<String, Double> maxValues);
    void presentMetric(String id, double value, double maxValue);
    void present(Exception ex);
}
