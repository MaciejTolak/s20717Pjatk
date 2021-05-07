package Kasia.zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {

    private String plik;
    private List<String> listaTXT;
    private List<List<String>> finalnaLista;


    public Anagrams(String plik) {
        this.plik = plik;
        listaTXT = new ArrayList<>();

        readFile();

        finalnaLista = getSortedByAnQty();
    }


    public List<List<String>> getSortedByAnQty() {
        List<List<String>> finalListTmp = new ArrayList<>();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < listaTXT.size(); i++) {
            if (!list.contains(listaTXT.get(i))) {
                List<String> listTmp = new ArrayList<>();
                for (int j = 0; j < listaTXT.size(); j++) {
                    char tab1[] = listaTXT.get(i).toCharArray();
                    char tab2[] = listaTXT.get(j).toCharArray();
                    Arrays.sort(tab1);
                    Arrays.sort(tab2);
                    if (new String(tab1).equals(new String(tab2))) {
                        listTmp.add(listaTXT.get(j));
                        list.add(listaTXT.get(j));

                    }
                }
                listTmp.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                finalListTmp.add(listTmp);
            }
        }
        finalListTmp.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o2.size() - o1.size();
            }
        }.thenComparing(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        }));
        return finalListTmp;
    }

    public String getAnagramsFor(String word) {
        String string = "";
        List<String> list = new ArrayList<>();
        string += word + ": ";
        if (listaTXT.contains(word)) {
            for (int i = 0; i < listaTXT.size(); i++) {
                char tab1[] = word.toCharArray();
                char tab2[] = listaTXT.get(i).toCharArray();
                Arrays.sort(tab1);
                Arrays.sort(tab2);
                if (new String(tab1).equals(new String(tab2)) && !listaTXT.get(i).equals(word)) {
                    list.add(listaTXT.get(i));
                }
            }
        } else {
            return new String(word + ": null");


        }
        string += list;
        return string;


    }


    public void readFile() {
        try {
            Scanner scanner = new Scanner(new File(plik));
            while (scanner.hasNextLine()) {
                String txt = scanner.nextLine();
                String tab[] = txt.split(" ");
                for (int i = 0; i < tab.length; i++) {
                    listaTXT.add(tab[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
