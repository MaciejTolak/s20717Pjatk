package Kasia.zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomersPurchaseSortFind {

    private List<Purchase> lista;
    private List<Purchase> listaTmp;

    public CustomersPurchaseSortFind() {
        lista = new ArrayList<>();
        listaTmp = new ArrayList<>();
    }

    public void showSortedBy(String string) {
        switch (string) {

            case "Nazwiska":
            Collections.sort(lista, new Comparator<Purchase>() {
                @Override
                public int compare(Purchase o1, Purchase o2) {
                    return o1.getNazwisko().compareTo(o2.getNazwisko());
                }
            }.thenComparing(new Comparator<Purchase>() {
                @Override
                public int compare(Purchase o1, Purchase o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            }));

            System.out.println("Nazwiska ");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }
            System.out.println();
            break;


            case"Koszty":
            Collections.sort(lista, new Comparator<Purchase>() {
                @Override
                public int compare(Purchase o1, Purchase o2) {
                    return (int) (o2.getCenaCalkowita() - o1.getCenaCalkowita());
                }
            }.thenComparing(new Comparator<Purchase>() {
                @Override
                public int compare(Purchase o1, Purchase o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            }));
            System.out.println("Koszty ");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i) + " (koszt: " + lista.get(i).getCenaCalkowita() + ")");
            }
            System.out.println();
            break;
        }
     }



    public void showPurchaseFor(String string){
        List<Purchase> listaFianlna = new ArrayList<>();
        System.out.println("Klient "+string);

        for (Purchase p : listaTmp){
            if(clientEquals(string,p)){
                listaFianlna.add(p);
            }
        }
        for (int i = 0; i <listaFianlna.size() ; i++) {
            System.out.println(listaFianlna.get(i));
        }
        System.out.println();
    }


    public void readFile(String string){
        try {
            Scanner scanner = new Scanner(new File(string));
            while (scanner.hasNextLine()){
                String tekst =scanner.nextLine();
                String tab1[] = tekst.split(";");
                String tab2[] = tab1[1].split(" ");
                lista.add(new Purchase(tab1[0], tab2[1], tab2[0], tab1[2], Double.parseDouble(tab1[3]), Double.parseDouble(tab1[4])));
                listaTmp.add(new Purchase(tab1[0], tab2[1], tab2[0], tab1[2], Double.parseDouble(tab1[3]), Double.parseDouble(tab1[4])));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean clientEquals (String string,Purchase purchase){
        return purchase.getId().equals(string);
    }



}
