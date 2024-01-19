package hepl.genielogiciel.files;

import java.nio.file.Path;
import java.util.Map;

public interface ConfigReader {

    Map<String, Double> read(String fileName) throws ConfigReaderException;
}
