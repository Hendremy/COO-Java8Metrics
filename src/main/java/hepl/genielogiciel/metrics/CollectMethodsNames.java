package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8BaseListener;
import hepl.genielogiciel.antlr.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectMethodsNames extends Java8Metric {
    private List<String> methodsNames = new ArrayList<>();

    public CollectMethodsNames(Java8Metric wrappee) {
        super(wrappee);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, String> metrics){
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
