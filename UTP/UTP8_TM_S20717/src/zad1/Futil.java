package zad1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Futil {


    public static void processDir(String dirName, String resultFileName) {
        try {
            Path path1 = Paths.get(resultFileName);
            Path dir = Paths.get(dirName);


            if (!Files.exists(path1)) {
                Files.createFile(path1);
            }

            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    List<String> list;
                    Charset charset = Charset.forName("Cp1250");
                    Charset standardCharsets = StandardCharsets.UTF_8;
                    list = Files.readAllLines(file,charset);
                    Files.write(path1,list, standardCharsets,StandardOpenOption.APPEND);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }


            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

