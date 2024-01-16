package hepl.genielogiciel;

import hepl.genielogiciel.antlr.Java8Lexer;
import hepl.genielogiciel.antlr.Java8Listener;
import hepl.genielogiciel.antlr.Java8Parser;
import hepl.genielogiciel.metrics.ATFDJava8Metric;
import hepl.genielogiciel.metrics.Metric;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String javaClassContent =
                "public class GreatClass { void greatJob(){} void littleJob(){} }";
        Java8Lexer java8Lexer = new Java8Lexer
                (CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();
        Map<String, String> metrics = new HashMap<>();

        Metric listener = new ATFDJava8Metric();
        listener.calculate(metrics, tree);

        for(var entry : metrics.entrySet()){
            String line = String.format("%s : %s", entry.getKey(), entry.getValue());
            System.out.println();
        }
    }
}

