package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public interface Metric {
    public void calculate(ParseTree tree, Map<String, String> metrics);
}
