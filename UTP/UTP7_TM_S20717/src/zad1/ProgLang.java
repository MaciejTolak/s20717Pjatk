package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

import java.util.stream.Collectors;

public class ProgLang {

    private File file;
    private Map<String, Collection<String>> map1;
    private Map<String, Collection<String>> map2;

    public ProgLang(String nazwaPliku) throws FileNotFoundException {
        file = new File(nazwaPliku);
        map1 = new LinkedHashMap();
        map2 = new LinkedHashMap();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tab = line.split("\\t");
            Collection<String> collection = new ArrayList<>();
            for (String string : Arrays.asList(tab)) {
                if (!collection.contains(string)) {
                    collection.add(string);
                }
            }

            collection.remove(tab[0]);
            map1.put(tab[0], collection);
            funkcjaTmpProx(tab);
        }
    }

    public Map<String, Collection<String>> getLangsMap() {
        return map1;
    }

    public Map<String, Collection<String>> getProgsMap() {

        return map2;

    }

    public Map<String, Collection<String>> getLangsMapSortedByNumOfProgs() {

        Map<String, Collection<String>> mapTmp = new LinkedHashMap<>();

        mapTmp = map1.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));

        return sorted(mapTmp, (t1, t2) -> {
            return t2.getValue().size() - t1.getValue().size();
        });

    }

    public Map<String, Collection<String>> getProgsMapSortedByNumOfLangs() {

        Map<String, Collection<String>> mapTmp = new LinkedHashMap<>();

        mapTmp = map2.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));


        return sorted(mapTmp, (t1, t2) -> {
            return t2.getValue().size() - t1.getValue().size();
        });
    }

    public Map<String, Collection<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(map1, v1 -> v1.getValue().size() > n);
    }


    public static <T, K> Map<T, K> sorted(Map<T, K> map, Comparator<Map.Entry<T, K>> comparator) {

        return map.entrySet().stream().sorted(comparator).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public static <T, K> Map<T, K> filtered(Map<T, K> map, Predicate<Map.Entry<T, K>> predicate) {
        return map.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    private void funkcjaTmpProx(String[] tab) {

        List<String> list = new ArrayList<>();

        for (int i = 1; i < tab.length; i++) {
            if (!list.contains(tab[i])) {
                list.add(tab[i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (!map2.containsKey(list.get(i))) {
                Collection<String> collection = new ArrayList<>();
                map2.putIfAbsent(list.get(i), collection);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!map2.get(list.get(i)).contains(tab[0])) {
                map2.get(list.get(i)).add(tab[0]);
            }
        }


    }
}
