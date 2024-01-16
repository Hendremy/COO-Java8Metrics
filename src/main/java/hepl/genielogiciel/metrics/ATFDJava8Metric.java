package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class ATFDJava8Metric extends Java8Metric{

    private final String id = "ATFD";
    private final String description = "Access to Foreign Data";
    private double value;

    public ATFDJava8Metric(){
        super();
    }

    public ATFDJava8Metric(Java8Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, String> metrics){
        value = 0;
        walkTree(tree);
        metrics.put(id, String.valueOf(value));
        super.calculate(tree, metrics);
    }

}
