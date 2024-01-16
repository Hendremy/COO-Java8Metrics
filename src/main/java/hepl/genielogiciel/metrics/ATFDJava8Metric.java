package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

public class ATFDJava8Metric extends Java8Metric{

    public ATFDJava8Metric(Java8Metric wrappee) {
        super(wrappee);
    }

    public String calculate(ParseTree tree){
        return "Helo";
    }

}
