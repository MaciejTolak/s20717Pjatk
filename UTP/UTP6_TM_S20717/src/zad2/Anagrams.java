/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {
    private String file;
    private List<String> listTxt;
    private  List<List<String>> finalList;


    public Anagrams(String file) {
        this.file = file;
        listTxt = new ArrayList<>();

        readFile();

        finalList = getSortedByAnQty();
    }



    public List<List<String>> getSortedByAnQty (){
        List<List<String>> finalListTmp = new ArrayList<>();

        List<String> list = new ArrayList<>();
        for (int i = 0; i <listTxt.size() ; i++) {
            if(!list.contains(listTxt.get(i))){
                List <String> listTmp = new ArrayList<>();
                for (int j = 0; j <listTxt.size() ; j++) {
                    char tab1 [] = listTxt.get(i).toCharArray();
                    char tab2 [] = listTxt.get(j).toCharArray();
                    Arrays.sort(tab1);
                    Arrays.sort(tab2);
                    if(new String(tab1).equals(new String(tab2))){
                        listTmp.add(listTxt.get(j));
                        list.add(listTxt.get(j));

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

    public String getAnagramsFor(String word){
        String string = "";
        List<String> list = new ArrayList<>();
        string+=word+": ";
        if(!listTxt.contains(word)){
            return new String(word+": null");
        }else{
            for (int i = 0; i <listTxt.size() ; i++) {
                char tab1 [] = word.toCharArray();
                char tab2 [] = listTxt.get(i).toCharArray();
                Arrays.sort(tab1);
                Arrays.sort(tab2);
                if(new String(tab1).equals(new String(tab2)) && !listTxt.get(i).equals(word)){
                    list.add(listTxt.get(i));
                }
            }
            string+= list;
            return string;
        }

    }

    public void readFile() {
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                String txt = scanner.nextLine();
                String tab[] = txt.split(" ");
                for (int i = 0; i <tab.length ; i++) {
                    listTxt.add(tab[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
