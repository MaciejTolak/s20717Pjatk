import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    public static double size;
    public   List<Item> iteamsInBag;
    public static List<Item> list;

    public Knapsack(double size, List<Item> list){
        this.size = size;
        iteamsInBag = new ArrayList<>();
        this.list = list;
    }

    public  double stworzTablice(){
        int n = list.size();
        int W = (int) size;
        double[][] tab = new double[n+1][W +1];

        for (int j = 0; j <= W; j++) {
            tab[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (list.get(i-1).weight > j) {
                    tab[i][j] = tab[i - 1][j];
                } else {
                    tab[i][j] = Math.max(
                            tab[i - 1][j],
                            tab[i - 1][(int) (j - list.get(i-1).weight)] + list.get(i-1).Value);
                }
            }
        }

       putItemsInBag(tab);

        return tab[n][W];
    }

    public void putItemsInBag(double[][] tab) {
        int n = list.size();
        int W = (int) size;

        int suma = 0;
        int i = n;
        int j = W;

        while (i > 0) {

            if (tab[i][j] == tab[i - 1][j])
                i = i - 1;
            else {

                if (suma + list.get(i-1).weight <= size) {
                    this.iteamsInBag.add(list.get(i-1));
                    suma += list.get(i-1).weight;
                    j = (int) (j - list.get(i-1).weight);
                    i = i - 1;
                } else
                    i = i - 1;

            }
        }
    }


}
