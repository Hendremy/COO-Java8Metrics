package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8Parser;
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

    @Override
    public void enterFieldAccess(Java8Parser.FieldAccessContext ctx) {
        value++;
    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        String methodName = ctx.methodName().toString();
        if(methodName.startsWith("get") || methodName.startsWith("set")){
            value++;
        }
    }
}
