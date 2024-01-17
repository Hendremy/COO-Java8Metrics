package hepl.genielogiciel;

import hepl.genielogiciel.cli.CliPresenter;
import hepl.genielogiciel.cli.Presenter;
import hepl.genielogiciel.files.*;
import hepl.genielogiciel.metrics.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Please specify the path to the target Java project");
        }else{
            new Main().scanProject(args[0]);
        }
    }

    private final Presenter presenter;
    private final ConfigReader configReader;
    private final ClassReader classReader;
    private final Map<String, Class<?>> availableMetrics;
    private final MetricFactory metricFactory;

    private Main(){
        presenter = new CliPresenter(System.out);
        configReader = new IniConfigReader();
        classReader = new Java8ClassReader();
        availableMetrics = new HashMap<>()
        {{
            put(ATFDJava8Metric.ID, ATFDJava8Metric.class);
            put(WMCJava8Metric.ID, WMCJava8Metric.class);
        }};
        metricFactory = new Java8MetricFactory(availableMetrics);
    }

    private void scanProject(String projectPath){
        Path configPath = Paths.get("src/main/resources/config.ini").toAbsolutePath();
        String workingDir = System.getProperty("user.dir");
        Path projectAbsPath = Paths.get(workingDir, projectPath).toAbsolutePath();

        Path[] classPaths = new Path[]{};

        try{
            Map<String, Double> config = configReader.read(configPath);
            Metric metric = metricFactory.create(config.keySet());

            for(Path classPath : classPaths){
                calcMetrics(classPath, metric, config);
            }

        }catch (MetricFactoryException | ConfigReaderException ex){
            presenter.present(ex);
        }
    }


    private void calcMetrics(Path filePath, Metric metric, Map<String, Double> config){
        try {
            ParseTree tree = classReader.read(filePath);
            Map<String, Double> metricValues = new HashMap<>();
            metric.calculate(tree, metricValues);
            presenter.presentMetrics(metricValues, config, filePath.toString());
        }catch(ClassReaderException ex){
            presenter.present(ex);
        }
    }
}

