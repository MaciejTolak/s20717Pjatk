import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> map = Files.lines(Paths.get("unixdict.txt")).collect(Collectors.groupingBy(x -> {
            char[] tab = x.toCharArray();
            Arrays.sort(tab);
            return new String(tab);
        }));
        int x = map.entrySet().stream().max((a, b) -> Integer.compare(a.getValue().size(), b.getValue().size())).get().getValue().size();
        map.entrySet().stream().filter(z -> {
            if (z.getValue().size() == x) {
                return true;
            } else {
                return false;
            }
        }).map(z -> {
            z.getValue().sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            return z;
        }).sorted(new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o1.getValue().get(0).compareTo(o2.getValue().get(0));
            }
        }).forEach(y ->{
            String s="";
            for (int i = 0; i <y.getValue().size() ; i++) {
                s+=" "+y.getValue().get(i);
            }
            System.out.println(s);
        });
    }
}
