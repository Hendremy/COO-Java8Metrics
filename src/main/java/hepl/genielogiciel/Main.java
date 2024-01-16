package hepl.genielogiciel;

import hepl.genielogiciel.cli.CliPresenter;
import hepl.genielogiciel.cli.Presenter;
import hepl.genielogiciel.file.*;
import hepl.genielogiciel.metrics.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Please specify the path to the target Java class");
        }else{
            calcMetrics(args[0]);
        }
    }

    private static void calcMetrics(String pathToClass){
        String workingDir = System.getProperty("user.dir");
        Path classPath = Paths.get(workingDir, pathToClass).toAbsolutePath();
        Path configPath = Paths.get("src/main/resources/config.ini").toAbsolutePath();

        ConfigReader configReader = new IniConfigReader();
        ClassReader classReader = new Java8ClassReader();
        MetricFactory metricFactory = new Java8MetricFactory();
        Presenter presenter = new CliPresenter(System.out);

        try {
            Map<String, Double> config = configReader.read(configPath);
            Map<String, Double> metrics = new HashMap<>();

            Metric metric = createMetrics(config.keySet(), metricFactory);

            metric.calculate(classReader.read(classPath), metrics);
            presenter.presentMetrics(metrics, config);
        }catch(ConfigReaderException | ClassReaderException | MetricFactoryException ex){
            presenter.present(ex);
        }
    }

    private static Metric createMetrics(Set<String> metricNames, MetricFactory factory) throws MetricFactoryException{
        Metric metric = null;

        for(String metricName : metricNames){
            metric = factory.create(metricName, metric);
        }

        return metric;
    }
}

