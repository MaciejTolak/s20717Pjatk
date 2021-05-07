package zad1;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {

    public static void processDir(String dirString, String resultString)  {


        try {
            FileChannel channelOut = FileChannel.open(Paths.get(resultString), StandardOpenOption.CREATE,StandardOpenOption.WRITE);


            Files.walkFileTree(Paths.get(dirString),new SimpleFileVisitor<Path>(){
               public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                   FileChannel channelIn = FileChannel.open(path);
                   ByteBuffer buffer = ByteBuffer.allocate((int) channelIn.size());
                   channelIn.read(buffer);
                   buffer.flip();
                   CharBuffer charBuffer = Charset.forName("Cp1250").decode(buffer);
                   channelOut.write(StandardCharsets.UTF_8.encode(charBuffer));
                   channelIn.close();
                   return FileVisitResult.CONTINUE;
               }
            });




            channelOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
