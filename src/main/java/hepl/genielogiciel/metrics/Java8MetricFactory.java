package hepl.genielogiciel.metrics;

public class Java8MetricFactory implements MetricFactory{
    public Metric create(String metricType){
        return create(metricType, null);
    }

    public Java8Metric create(String metricType, Metric wrappee){
        Java8Metric metric = null;
        switch(metricType){
            case "ATFD":
                metric = new ATFDJava8Metric(wrappee);
                break;
            case "WMC":
                metric = new WMCJava8Metric(wrappee);
                break;
        }
        return metric;
    }
}
