package hepl.genielogiciel;

import hepl.genielogiciel.metrics.CollectMethodsNamesJava8Metric;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectMethodsNamesTest {
    ParseTree tree;
    CollectMethodsNamesJava8Metric listener = new CollectMethodsNamesJava8Metric(null);
    @Before
    public void setUp() throws Exception {
        String javaClassContent =
                "public class GreatClass { void greatJob(){} void littleJob(){} }";
        Java8Lexer java8Lexer = new Java8Lexer
                (CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        tree = parser.compilationUnit();
    }
    @Test
    public void collectMethodsNames() {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        assertTrue(listener.hasMethod("greatJob"));
        assertFalse(listener.hasMethod("method"));
    }
}

