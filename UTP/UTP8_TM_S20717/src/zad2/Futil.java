package zad2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            Path path1 = Paths.get(resultFileName);
            Path dir = Paths.get(dirName);


            if (!Files.exists(path1)) {
                Files.createFile(path1);
            }
            Stream<Path> stream =  Files.walk(dir);
            stream.filter(a -> !Files.isDirectory(a)).collect(Collectors.toList()).forEach(a -> {
                try {
                    List<String> list;
                    Charset charset = Charset.forName("Cp1250");
                    Charset standardCharsets = StandardCharsets.UTF_8;
                    list = Files.readAllLines(a,charset);
                    Files.write(path1,list, standardCharsets,StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
