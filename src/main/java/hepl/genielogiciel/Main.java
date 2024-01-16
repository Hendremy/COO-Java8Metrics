package hepl.genielogiciel;

import hepl.genielogiciel.file.ClassReader;
import hepl.genielogiciel.file.ClassReaderException;
import hepl.genielogiciel.file.Java8ClassReader;
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

    private static void calcMetrics(String classPath){
        String workingDir = System.getProperty("user.dir");
        Path filePath = Paths.get(workingDir, classPath).toAbsolutePath();

        ClassReader classReader = new Java8ClassReader();
        Map<String, String> metrics = new HashMap<>();
        Metric metric = new ATFDJava8Metric(new WMCJava8Metric(new CollectMethodsNamesJava8Metric()));

        try{
            metric.calculate(classReader.read(filePath), metrics);
            presentMetrics(metrics);
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

