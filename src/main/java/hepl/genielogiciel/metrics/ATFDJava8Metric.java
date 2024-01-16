package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class ATFDJava8Metric extends Java8Metric{

    public final static String ID = "ATFD";
    private final String description = "Access to Foreign Data";
    private double value;

    public ATFDJava8Metric(){
        super();
    }

    public ATFDJava8Metric(Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, Double> metrics){
        value = 0;
        walkTree(tree);
        metrics.put(ID, value);
        super.calculate(tree, metrics);
    }

}
