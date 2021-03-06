/**
 * @author Tolak Maciej S20717
 */

package zad3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class
Main {
    public static void main(String[] args) {
        /*<--
         *  definicja operacji w postaci lambda-wyrażeń:
         *  - flines - zwraca listę wierszy z pliku tekstowego
         *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
         *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
         *  - sum - zwraca sumę elmentów listy liczb całkowitych
         */

        Function<String, List<String>> flines = (e -> {
            List<String> lista = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(new File(e));
                String line;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    lista.add(line);
                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            return lista;
        });

        Function<List<String>, String> join = (e -> {
            String line = "";
            for (String s : e) {
                line += s;
            }
            return line;
        });

        Function<String, List<Integer>> collectInts = (e -> {
            Pattern pattern = Pattern.compile("-?\\d+");
            Matcher matcher = pattern.matcher(e);
            List<Integer> lista = new ArrayList<>();

            while (matcher.find()) {
                lista.add(Integer.parseInt(matcher.group()));
            }
            return lista;
        });

        Function<List<Integer>, Integer> sum = (e -> {
            int x = 0;
            for (Integer i : e) {
                x += i;
            }
            return x;
        });


        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

    }
}
