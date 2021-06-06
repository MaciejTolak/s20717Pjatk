import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Item> list = new ArrayList<>();
    public static int maxObjetosc = 150;

    public static void main(String[] args) {
        try {
            list = listCreator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Knapsack knapsack = new Knapsack(maxObjetosc,list);

        double bestValue = knapsack.stworzTablice();
        double bestWeight = 0;
        for(Item item : knapsack.iteamsInBag){
            bestWeight += item.weight;
        }

        double allValue = 0;
        double allWeight = 0;

        for(Item item : list){
            allValue+= item.Value;
            allWeight += item.weight;
        }

        System.out.println("ALL VALUE: "+ allValue+"\nALL WEIGHT: "+ allWeight+"\nBEST VALUE: "+bestValue+"\nBEST WEIGHT: "+ bestWeight);
    }


    public static List<Item> listCreator() throws FileNotFoundException {
        File file = new File("knapsack.csv");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] result = line.split(",");
            Item item = new Item(Double.parseDouble(result[0]) , Double.parseDouble(result[1]));
            list.add(item);
        }
        return list;
    }



}
