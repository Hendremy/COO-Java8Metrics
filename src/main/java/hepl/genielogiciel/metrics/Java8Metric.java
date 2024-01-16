package hepl.genielogiciel.metrics;

import hepl.genielogiciel.antlr.Java8BaseListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Map;

public abstract class Java8Metric extends Java8BaseListener implements Metric {

    protected Metric wrappee;

    public Java8Metric(){
        this.wrappee = null;
    }

    public Java8Metric(Metric wrappee){
        this.wrappee = wrappee;
    }

    protected void walkTree(ParseTree tree){
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
    }

    @Override
    public void calculate(ParseTree tree, Map<String, Double> metrics){
        if (wrappee != null){
            this.wrappee.calculate(tree, metrics);
        }
    }
}
