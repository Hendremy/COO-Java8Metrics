package hepl.genielogiciel;

import hepl.genielogiciel.file.*;
import hepl.genielogiciel.metrics.ATFDJava8Metric;
import hepl.genielogiciel.metrics.CollectMethodsNamesJava8Metric;
import hepl.genielogiciel.metrics.Metric;
import hepl.genielogiciel.metrics.WMCJava8Metric;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
        Path configPath = Paths.get(workingDir, "src/main/resources/config.ini").toAbsolutePath();

        ConfigReader configReader = new IniConfigReader();
        ClassReader classReader = new Java8ClassReader();

        try {
            Map<String, Double> config = configReader.read(configPath);
            Map<String, String> metrics = new HashMap<>();

            Metric metric = new ATFDJava8Metric(new WMCJava8Metric(new CollectMethodsNamesJava8Metric()));

            metric.calculate(classReader.read(classPath), metrics);
            presentMetrics(metrics);
        }catch(ConfigReaderException ex){
            System.out.println("Error while loading config file : " + ex.getMessage());
            ex.printStackTrace();
        }catch(ClassReaderException ex){
            System.out.println("Error while parsing file, check path or file integrity : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void presentMetrics(Map<String, String> metrics){
        for(var entry : metrics.entrySet()){
            String line = String.format("%s : %s", entry.getKey(), entry.getValue());
            System.out.println(line);
        }
    }
}

