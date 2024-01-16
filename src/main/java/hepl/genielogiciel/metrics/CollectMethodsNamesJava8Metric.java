package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectMethodsNamesJava8Metric extends Java8Metric {
    private final String name = "Collect methods names";
    private List<String> methodsNames = new ArrayList<>();

    public CollectMethodsNamesJava8Metric(){
        super();
    }

    public CollectMethodsNamesJava8Metric(Java8Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, String> metrics){
        walkTree(tree);
        metrics.put(name, String.join(", ", methodsNames));
        super.calculate(tree, metrics);
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx){
        TerminalNode node = ctx.Identifier();
        String methodName = node.getText();
        methodsNames.add(methodName);
    }

    public boolean hasMethod(String name){
        return methodsNames.contains(name);
    }
}
