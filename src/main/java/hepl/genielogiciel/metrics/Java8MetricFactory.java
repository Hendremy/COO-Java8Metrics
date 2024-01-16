package hepl.genielogiciel.metrics;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Java8MetricFactory implements MetricFactory{

    private final Map<String, Class<?>> availableMetrics;
    public Java8MetricFactory(Map<String, Class<?>> availableMetrics){
        this.availableMetrics = availableMetrics;
    }
    public Metric create(String metricType) throws MetricFactoryException {
        return create(metricType, null);
    }

    public Metric create(String metricType, Metric wrappee) throws MetricFactoryException{
        Metric metric;
        Class<?> metricClass = availableMetrics.get(metricType);

        if(metricClass == null){
            throw new MetricFactoryException("Unsupported metric type : " + metricType);
        }

        try{
            metric = (Metric) metricClass.getDeclaredConstructor(Metric.class).newInstance(wrappee);
        }catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex){
            throw new MetricFactoryException(ex.getMessage());
        }

        return metric;
    }

    public Metric create(Iterable<String> ids) throws MetricFactoryException{
        Metric metric = null;
        for(String id : ids){
            metric = create(id, metric);
        }
        return metric;
    }
}
