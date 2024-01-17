package hepl.genielogiciel;

import static org.junit.Assert.assertEquals;

import hepl.genielogiciel.antlr.Java8Lexer;
import hepl.genielogiciel.antlr.Java8Parser;
import hepl.genielogiciel.metrics.ATFDJava8Metric;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class ATFDJava8MetricTests {

    ATFDJava8Metric metric;
    Java8Parser parser;

    @Before
    public void setUp(){
        metric = new ATFDJava8Metric();
    }

    private ParseTree parseClass(String content){
        Java8Lexer java8Lexer = new Java8Lexer
                (CharStreams.fromString(content));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        parser = new Java8Parser(tokens);
        return parser.compilationUnit();
    }

    @Test
    public void scanGreatClass(){
        String greatClass =
                "public class GreatClass {\n" +
                "    void greatJob(){}\n" +
                "\n" +
                "    void littleJob(){}\n" +
                "}";
        ParseTree tree = parseClass(greatClass);
        Map<String, Double> metrics = new HashMap<>();
        metric.calculate(tree, metrics);

        assertEquals(0.0, metrics.get(ATFDJava8Metric.ID), 0.1);
    }

    @Test
    public void scanMessyClass(){
        String messyClass =
                "public class MessyClass {\n" +
                "    private GreatClass great;\n" +
                "    void messyJob(){\n" +
                "        great.value = 5;\n" +
                "        great.name = \"Lol\";\n" +
                "    }\n" +
                "\n" +
                "    void superMessyJob(){\n" +
                "        int i = great.getValue();\n" +
                "        String s = great.getName();\n" +
                "        great.value = 5;\n" +
                "        int a = 5;\n" +
                "    }\n" +
                "\n" +
                "    int testFun(){\n" +
                "        return 0;\n" +
                "    }\n" +
                "}";

        ParseTree tree = parseClass(messyClass);
        Map<String, Double> metrics = new HashMap<>();
        metric.calculate(tree, metrics);

        assertEquals(4.0, metrics.get(ATFDJava8Metric.ID), 0.1);
    }
}
