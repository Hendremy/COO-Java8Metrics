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
    private Set<String> foreignAccesses;

    public ATFDJava8Metric(){
        super();
    }

    public ATFDJava8Metric(Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, Double> metrics){
        foreignAccesses = new HashSet<>();
        walkTree(tree);
        metrics.put(ID, (double) foreignAccesses.size());
        super.calculate(tree, metrics);
    }

    @Override
    // Property accessed directly, example : int num = obj.value  OR  obj.value = 5
    public void enterExpressionName(Java8Parser.ExpressionNameContext ctx) {
        Java8Parser.AmbiguousNameContext ambiguousNameContext = ctx.ambiguousName();

        if(ctx.ambiguousName() != null && ctx.getChildCount() >= 3){
            String ambiguousName = ambiguousNameContext.getText();
            String fieldName = ctx.getChild(2).getText();
            updateMetric(ambiguousName + "." + fieldName);
        }
    }

    @Override
    // Getter/setter used in statement, example: String name = obj.getName();
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        Java8Parser.TypeNameContext typeNameContext = ctx.typeName();
        if(typeNameContext != null && ctx.getChildCount() >= 3){
            String typeName = typeNameContext.getText();
            String methodName = ctx.getChild(2).getText();
            if(isGetterOrSetter(methodName)){
                updateMetric(typeName + "." + methodName);
            }
        }
    }

    @Override
    // Only getter/setter used on statement, example: object.getName();
    public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        Java8Parser.TypeNameContext typeNameContext = ctx.typeName();
        if(typeNameContext != null) {
            String typeName = typeNameContext.getText();
            if (!typeName.equals("this") && ctx.getChildCount() >= 3) {
                String methodName = ctx.getChild(2).getText();
                if (isGetterOrSetter(methodName)) {
                    updateMetric(typeName + "." + methodName);
                }
            }
        }
    }

    private boolean isGetterOrSetter(String methodName){
        return methodName.startsWith("get") || methodName.startsWith("set");
    }

    private void updateMetric(String invocation){
        foreignAccesses.add(invocation);
    }
}
