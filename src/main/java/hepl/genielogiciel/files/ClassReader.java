package hepl.genielogiciel.files;

import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.Path;

public interface ClassReader {
    ParseTree read(Path filePath) throws ClassReaderException;
}
