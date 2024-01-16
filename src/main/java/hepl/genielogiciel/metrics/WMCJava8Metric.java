package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class WMCJava8Metric extends Java8Metric {

    private final String name = "Weighted Method Count";
    private int value;

    public WMCJava8Metric(){
        super();
    }

    public WMCJava8Metric(Java8Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, String> metrics){
        value = 0;
        walkTree(tree);
        metrics.put(name,String.valueOf(value));
        super.calculate(tree, metrics);
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        value++;
    }
}
