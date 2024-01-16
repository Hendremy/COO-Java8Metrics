package hepl.genielogiciel.file;

import java.nio.file.Path;
import java.util.Map;

public interface ConfigReader {

    Map<String, Double> read(Path filePath) throws ConfigReaderException;
}
