package hepl.genielogiciel.files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CustomFileVisitor implements FileVisitor {
    @Override
    public FileVisitResult preVisitDirectory(Object o, BasicFileAttributes basicFileAttributes) throws IOException {
        return null;
    }

    @Override
    public FileVisitResult visitFile(Object o, BasicFileAttributes basicFileAttributes) throws IOException {
        return null;
    }

    @Override
    public FileVisitResult visitFileFailed(Object o, IOException e) throws IOException {
        return null;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object o, IOException e) throws IOException {
        return null;
    }
}
