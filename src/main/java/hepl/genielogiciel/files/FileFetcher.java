package hepl.genielogiciel.files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFetcher implements FileVisitor {

    private List<Path> files;
    private String extension;

    public Iterable<Path> fetch(Path start, String extension){
        files = new ArrayList<>();
        this.extension = extension;
        try{
            Files.walkFileTree(start, this);
        }catch(IOException ex){

        }
        return files;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object o, BasicFileAttributes basicFileAttributes) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object o, BasicFileAttributes basicFileAttributes) throws IOException {
        Path path = (Path) o;
        if (path.toString().endsWith(extension)){
            files.add(path);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object o, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object o, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
