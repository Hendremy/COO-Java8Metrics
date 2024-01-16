package hepl.genielogiciel.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class IniConfigReader implements ConfigReader{
    @Override
    public Map<String, Double> read(Path filePath) throws ConfigReaderException{
        Map<String, Double> config = new HashMap<>();

        try(BufferedReader reader = Files.newBufferedReader(filePath)){
            String line;
            String[] splitLine;
            while(reader.ready()){
                line = reader.readLine();
                splitLine = line.split("=");
                config.put(splitLine[0], Double.parseDouble(splitLine[1]));
            }
        }catch(IOException ex){
            throw new ConfigReaderException(ex.getMessage());
        }
        return config;
    }
}
