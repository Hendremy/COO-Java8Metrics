package hepl.genielogiciel.file;

import hepl.genielogiciel.antlr.Java8Lexer;
import hepl.genielogiciel.antlr.Java8Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Java8ClassReader implements ClassReader{

    @Override
    public ParseTree read(Path filePath) throws ClassReaderException{
        ParseTree tree = null;
        try{
            tree = parse(Files.readString(filePath));
        }catch(IOException ex){
            throw new ClassReaderException("Failed to read");
        }
        return tree;
    }

    private ParseTree parse(String content){
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(content));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        return parser.compilationUnit();
    }
}
