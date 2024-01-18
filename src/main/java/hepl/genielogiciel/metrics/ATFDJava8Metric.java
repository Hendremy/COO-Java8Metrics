package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ATFDJava8Metric extends Java8Metric{

    public final static String ID = "ATFD";
    private final String description = "Access to Foreign Data";
    private double value;

    private Set<String> usedForeignAccesses;

    public ATFDJava8Metric(){
        super();
    }

    public ATFDJava8Metric(Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, Double> metrics){
        value = 0;
        usedForeignAccesses = new HashSet<>();
        walkTree(tree);
        metrics.put(ID, value);
        super.calculate(tree, metrics);
    }

    @Override
    public void enterElementValuePair(Java8Parser.ElementValuePairContext ctx) {
        TerminalNode id = ctx.Identifier();
        String s = id.toString();
        // System.out.println(s);
    }

    @Override
    public void enterFieldAccess(Java8Parser.FieldAccessContext ctx) {
        var s = ctx.Identifier();
        // var p = ctx.primary();
        // System.out.println(s.getText());
        // System.out.println(p.getText());
    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        var methodNameCtx = ctx.methodName();
        String methodName = methodNameCtx == null ? "" : methodNameCtx.toString();
        //System.out.println(methodName);
        if(methodName.startsWith("get") || methodName.startsWith("set")){
            value++;
        }
    }
}
