import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static String correct = "Iris-setosa";
    static Perceptron perceptron;

    static int matched = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        List<String[]> listString = listCreator(args[0]);
        List<String []> listTest = listCreator(args[1]);
        int all = listString.size();

        perceptron = new Perceptron(listString.get(0).length - 2);

        System.out.println("Podaj procent bledu: ");
        double t = scanner.nextDouble();
        double acc = (100-t)/100;

        while ((double)matched /(double) all < acc) {

            System.out.print("NOWY TEST     poprawnych wyników: ");

            matched = 0;

            for (String[] tab : listString) {
                train(tab);
            }

            System.out.println(matched);
        }


        do {
            System.out.println("1 - wlasny wektor");
            System.out.println("2 - Trening");
            System.out.println("0 - Exit");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    List<Double> listWektor = new ArrayList<>();

                    for(int i = 0; i<listTest.get(0).length-2;i++){
                        System.out.print("Podaj "+i+ " wymiar: ");
                        listWektor.add(scanner.nextDouble());
                    }

                    if(perceptron.TestDlaWektora(listWektor)){
                        System.out.println("Ten wektor wskazuje na iris-setosa");
                    }else{
                        System.out.println("Ten wektor nie wskazuje na iris-setosa");
                    }

                    break;

                case 2:
                    int suma = 0;
                    for(String [] tab : listTest){
                        System.out.println("Dla wektora: "+tab[0] + " program zwrocił wartosc: "+ test(tab));
                         suma += test(tab);
                    }
                    System.out.println("Dokladnosc testu wyniosła: "+ (double)suma/(double)listTest.size());

                    break;

            }
        } while (choice != 0);


    }

    public static void train(String[] tab) {

        int d = 0;
        String odp = tab[tab.length - 1];
        if (odp.equals(correct)) {
            d = 1;
        }
        if (perceptron.Train(Converter(tab), d)) {
            matched++;
        }
    }



    public static int test(String [] tab){
        int d = 0;
        String odp = tab[tab.length - 1];
        if (odp.equals(correct)) {
            d = 1;
        }
        if(perceptron.Test(Converter(tab),d)){
            return 1;
        }else
        {
            return 0;
        }
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

}
