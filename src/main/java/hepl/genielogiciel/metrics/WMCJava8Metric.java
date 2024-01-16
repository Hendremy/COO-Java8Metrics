package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class WMCJava8Metric extends Java8Metric {
    public WMCJava8Metric(Java8Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, String> metrics){
        super.calculate(tree, metrics);
    }
}
