package hepl.genielogiciel.cli;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;

public class CliPresenter implements Presenter{

    private final PrintStream out;

    public CliPresenter(PrintStream out){
        this.out = out;
    }
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_RESET = "\u001B[0m";

    @Override
    public void presentMetrics(Map<String, Double> metrics, Map<String, Double> maxValues, String filePath) {
        out.println("\n\n" + filePath);
        presentMetrics(metrics, maxValues);
    }

    public void presentMetrics(Map<String, Double> metrics, Map<String, Double> maxValues){
        for(String metricId : metrics.keySet()){
            presentMetric(metricId, metrics.get(metricId), maxValues.get(metricId));
        }
    }

    public void presentMetric(String id, double actualValue, double maxValue){
        String line;
        if(actualValue <= (maxValue * 80/100)){
            line = String.format("%s : %.2f", id, actualValue);
        }else if(actualValue <= maxValue){
            line = String.format("%s%s : %.2f       < ! > maximum %.2f < ! >",ANSI_YELLOW, id, actualValue, maxValue);
        }else{
            line = String.format("%s%s : %.2f       / ! \\ maximum %.2f / ! \\",ANSI_RED, id, actualValue, maxValue);
        }
        line += ANSI_RESET;
        out.println(line);
    }

    public void present(Exception ex){
        out.printf("%s%s : %s%s", ANSI_RED, ex.getMessage(), Arrays.toString(ex.getStackTrace()), ANSI_RESET);
    }
}
