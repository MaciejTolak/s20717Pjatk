import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int correct = 2;
    static Perceptron perceptron;
    static int matched = 0;
    static int matched2 = 0;
    static int matched4 = 0;
    static int all = 0;
    static int all2 = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<String[]> listTraining = listCreator(args[0]);
        List<String[]> listTest = listCreator(args[1]);
        all = listTest.size();
        perceptron = new Perceptron(listTraining.get(0).length - 2);
        System.out.println("Podaj procent bledu: ");
        double [][] macierz;
        double t = scanner.nextDouble();
        double acc = (100 - t) / 100;
        while ((double) matched / (double) all < acc) {
            System.out.print("NOWY TEST     poprawnych wyników: ");
            matched = 0;
            matched2 = 0;
            matched4 = 0;
            all2 = 0;

            for (String[] tab : listTraining) {
                train(tab);
            }
            for (String[] tab : listTest) {
                test(tab);
            }
            System.out.println(matched);
        }

        System.out.println("Dokładność: " + accuracy(matched2, matched4, all));
        System.out.println("Precyzja: " + precision(matched2, all2, all, matched4));
        System.out.println("Pelnosc: " + pelnosc(matched2, all2));
        System.out.println("F-miara: "+ fmiara(precision(matched2, all2, all, matched4),pelnosc(matched2, all2)));
        macierz = macierzOmylek(matched2, all2, all, matched4);
        System.out.println("Macierz omylek");
        for(int i = 0;i<macierz.length;i++){
            for (int j = 0; j<macierz[i].length;j++){
                System.out.print(macierz[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void train(String[] tab) {

        int d = 0;
        int odp = Integer.parseInt(tab[tab.length - 1]);

        if (odp == correct) {
            d = 1;
        }
        perceptron.Train(Converter(tab), d);


    }

    public static List<String[]> listCreator(String path) throws FileNotFoundException {
        List<String[]> list = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine().split(","));
        }

        return list;
    }

    public static List<Double> Converter(String[] tab) {

        List<Double> list = new ArrayList<>();
        for (int i = 1; i < tab.length - 1; i++) {
            list.add(Double.parseDouble(tab[i]));
        }
        return list;
    }

    public static void test(String[] tab) {
        int d = 0;
        int odp = Integer.parseInt(tab[tab.length - 1]);
        if (odp == 2) {
            all2++;
    }
        if (odp == correct) {
            d = 1;
        }
        if (perceptron.Test(Converter(tab), d)) {

            matched++;
            if (odp == 2) {
                matched2++;
            } else {
                matched4++;
            }
        }
    }

    public static double accuracy(int matched2, int matched4, int all) {
        return ((double) matched2 + (double) matched4) / (double) all;
    }

    public static double precision(int matched2, int all2, int all, int matched4) {
        return (double) matched2 / (double) (matched2 + (all - all2 - matched4));
    }

    public static double pelnosc(int matched2, int all2) {
        return (double) matched2 / (double) (all2);
    }
    public static double fmiara(double P, double R){
        return (2*P*R)/(P+R);
    }

    public static double[][] macierzOmylek(int matched2, int all2, int all, int matched4){
        double [][] tab = new double[3][3];

        tab[0][0] =0;
        for(int i=1;i<tab.length-1;i++){
            tab[i][0] = 2;
            tab[0][i] = 2;
        }
        for(int i=2;i<tab.length;i++){
            tab[i][0] = 4;
            tab[0][i] = 4;
        }
        tab[1][1] = matched2;
        tab[1][2] = all - all2 - matched4;
        tab[2][1] = all2 -matched2;
        tab[2][2] = matched4;

        return tab;
    }
}
