
package zad1;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Map<String, List<String>> m = Files.lines(Paths.get("unixdict.txt")).collect(Collectors.groupingBy(x -> {
            char[] t = x.toCharArray();
            Arrays.sort(t);
            return new String(t);
        }));
        int x = m.entrySet().stream().max(Comparator.comparingInt(b -> b.getValue().size())).get().getValue().size();
        m.entrySet().stream().filter(z -> (z.getValue().size() == x)).map(z -> {
            z.getValue().sort(Comparator.naturalOrder());
            return z;
        }).sorted(Comparator.comparing(o -> o.getValue().get(0))).forEach(h -> System.out.println(String.join(" ", h.getValue())));


        lista.stream().filter(warunek).sorted(porządek).forEach(System.out::println);
    }
}
