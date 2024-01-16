package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class WMCJava8Metric extends Java8Metric {

    public final static String ID = "WMC";
    private final String description = "Weighted Method Count";
    private double value;

    public WMCJava8Metric(){
        super();
    }

    public WMCJava8Metric(Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, Double> metrics){
        value = 0;
        walkTree(tree);
        metrics.put(ID, value);
        super.calculate(tree, metrics);
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        value++;
    }
}
