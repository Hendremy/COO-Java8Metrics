package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public interface Metric {
    void calculate(ParseTree tree, Map<String, Double> metrics);
}
