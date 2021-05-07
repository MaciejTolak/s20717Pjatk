/**
 * @author Tolak Maciej S20717
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomersPurchaseSortFind {

    private List<Purchase> list;
    private List<Purchase> listTmp;

    public CustomersPurchaseSortFind() {
        list = new ArrayList<>();
        listTmp = new ArrayList<>();
    }

    public void showSortedBy(String string) {

        switch (string) {
            case "Nazwiska":
                Collections.sort(list, new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        return o1.getSurname().compareTo(o2.getSurname());
                    }
                }.thenComparing(new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                }));

                System.out.println("Nazwiska ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                }
                System.out.println();
                break;

            case "Koszty":
                Collections.sort(list, new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        return (int) (o2.getSumPrice() - o1.getSumPrice());
                    }
                }.thenComparing(new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                }));
                System.out.println("Koszty ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i) + " (koszt: " + list.get(i).getSumPrice() + ")");
                }
                System.out.println();
                break;
        }


    }

    public void showPurchaseFor(String string) {

        List<Purchase> listFianl = new ArrayList<>();
        System.out.println("Klient " + string);
        for (Purchase p : listTmp){
            if(clientEquals(string,p)){
                listFianl.add(p);
            }
        }

        for (int i = 0; i <listFianl.size() ; i++) {
            System.out.println(listFianl.get(i));
        }
        System.out.println();

    }

    public void readFile(String string) {
        try {
            Scanner scanner = new Scanner(new File(string));
            while (scanner.hasNextLine()) {
                String txt = scanner.nextLine();
                String tab1[] = txt.split(";");
                String tab2[] = tab1[1].split(" ");
                list.add(new Purchase(tab1[0], tab2[1], tab2[0], tab1[2], Double.parseDouble(tab1[3]), Double.parseDouble(tab1[4])));
                listTmp.add(new Purchase(tab1[0], tab2[1], tab2[0], tab1[2], Double.parseDouble(tab1[3]), Double.parseDouble(tab1[4])));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean clientEquals(String string,Purchase purchase){
        return purchase.getId().equals(string);
    }
}
