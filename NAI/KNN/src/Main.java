import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        int choice;
        int k;

        List<Flower> trainList;
        List<Flower> testList;

        trainList = TrainList(args[0]);
        testList = TrainList(args[1]);

        double[] tabAvg_TrainList = avg(trainList);


        double[] tabOdchylenie_TrainList = odchylenieStandardowe(trainList, tabAvg_TrainList);


        normalizacja(trainList, tabAvg_TrainList, tabOdchylenie_TrainList);
        normalizacja(testList, tabAvg_TrainList, tabOdchylenie_TrainList);

        do {
            System.out.println("1 - Obliczenie dokladnosci na podstawie trening listy dla test listy");
            System.out.println("2 - Podanie swoich wymiarów dla wektora");
            System.out.println("0 - Exit");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Podaj hiperparametr");
                    k = scanner.nextInt();
                    int counter = 0;
                    String tmp;
                    for (Flower flower : testList) {
                        tmp = odlegloscEuklidesowa(trainList, flower, k);
                        System.out.println("Wynik w pliku: " + flower.getName() + " program wykazal: " + tmp);
                        if (flower.getName().equals(tmp)) {
                            counter++;
                        }
                    }
                    System.out.println("Dokladnosc wynosi: " + (((double) counter / (double) testList.size())));
                    break;

                case 2:
                    double SL;
                    double SW;
                    double PL;
                    double PW;

                    System.out.println("Podaj Sepal length: ");
                    SL = scanner.nextDouble();
                    System.out.println("Podaj Sepal width: ");
                    SW = scanner.nextDouble();
                    System.out.println("Podaj Petal Length: ");
                    PL = scanner.nextDouble();
                    System.out.println("Podaj Petal width: ");
                    PW = scanner.nextDouble();
                    System.out.println("Podaj parametr k: ");
                    k = scanner.nextInt();

                    tmp = odlegloscEuklidesowa(trainList, tabAvg_TrainList, tabOdchylenie_TrainList, SL, SW, PL, PW, k);

                    System.out.println(tmp);


                    break;

            }
        } while (choice != 0);


    }


    public static double[] avg(List<Flower> list) {
        double tabAvg[] = new double[4];
        double counter = 0;
        double suma = 0;

        //srednia dla sepal_legth
        for (Flower flower : list) {
            suma += flower.getSepal_length();
            counter++;
        }
        tabAvg[0] = (suma / counter);
        counter = 0;
        suma = 0;

        //srednia dla sepal_width
        for (Flower flower : list) {
            suma += flower.getSepal_width();
            counter++;
        }
        tabAvg[1] = (suma / counter);
        counter = 0;
        suma = 0;
        //srednia dla petal_length
        for (Flower flower : list) {
            suma += flower.getPetal_length();
            counter++;
        }
        tabAvg[2] = (suma / counter);
        counter = 0;
        suma = 0;
        //srednia dla petal_width
        for (Flower flower : list) {
            suma += flower.getPetal_width();
            counter++;
        }
        tabAvg[3] = (suma / counter);


        return tabAvg;
    }

    public static double[] odchylenieStandardowe(List<Flower> list, double[] tabAvg) {
        double tabOdchylenia[] = new double[4];
        double suma = 0;
        //odchylenie dla sepal_length
        for (Flower flower : list) {
            suma += Math.pow((flower.getSepal_length() - tabAvg[0]), 2);

        }
        tabOdchylenia[0] = Math.sqrt(suma / list.size());
        suma = 0;


        //odchylenie dla sepal_width
        for (Flower flower : list) {
            suma += Math.pow((flower.getSepal_width() - tabAvg[1]), 2);

        }
        tabOdchylenia[1] = Math.sqrt(suma / list.size());
        suma = 0;


        //odchylenie dla Petal_length
        for (Flower flower : list) {
            suma += Math.pow((flower.getPetal_length() - tabAvg[2]), 2);

        }
        tabOdchylenia[2] = Math.sqrt(suma / list.size());
        suma = 0;


        //odchylenie dla petal_width
        for (Flower flower : list) {
            suma += Math.pow((flower.getPetal_width() - tabAvg[3]), 2);

        }
        tabOdchylenia[3] = Math.sqrt(suma / list.size());


        return tabOdchylenia;
    }

    public static void normalizacja(List<Flower> list, double[] tabAvg, double[] tabOdchylenie) {

        //normalizacja dla Sepal_length
        for (Flower flower : list) {

            flower.setSepal_length_nor((flower.getSepal_length() - tabAvg[0]) / tabOdchylenie[0]);

        }

        //normalizacja dla sepal_width
        for (Flower flower : list) {
            flower.setSepal_width_nor((flower.getSepal_width() - tabAvg[1]) / tabOdchylenie[1]);
        }

        //normalizacja dla petal_length
        for (Flower flower : list) {
            flower.setPetal_length_nor((flower.getPetal_length() - tabAvg[2]) / tabOdchylenie[2]);
        }

        //normalizacja dla petal_width
        for (Flower flower : list) {
            flower.setPetal_width_nor((flower.getPetal_width() - tabAvg[3]) / tabOdchylenie[3]);
        }

    }

    public static String odlegloscEuklidesowa(List<Flower> trainList, Flower tmpFlower, int k) {
        String string = "";
        int counterIS = 0;
        int counterIVe = 0;
        int counterIVi = 0;

        for (Flower flower : trainList) {
            flower.setOdleglosc(Math.sqrt(Math.pow(tmpFlower.getSepal_length_nor() - flower.getSepal_length_nor(), 2) + Math.pow(tmpFlower.getSepal_width_nor() - flower.getSepal_width_nor(), 2) + Math.pow(tmpFlower.getPetal_length_nor() - flower.getPetal_length_nor(), 2) + Math.pow(tmpFlower.getPetal_width_nor() - flower.getPetal_width_nor(), 2)));

        }

        Collections.sort(trainList, Comparator.comparingDouble(Flower::getOdleglosc));

        for (int i = 0; i < k; i++) {

            if (trainList.get(i).getName().equals("Iris-versicolor")) {
                counterIVe++;
            } else if (trainList.get(i).getName().equals("Iris-setosa")) {
                counterIS++;
            } else if (trainList.get(i).getName().equals("Iris-virginica")) {
                counterIVi++;
            }
        }

        if (counterIVi > counterIS && counterIVi > counterIVe) {
            string = "Iris-virginica";
        } else if (counterIS > counterIVi && counterIS > counterIVe) {
            string = "Iris-setosa";
        } else if (counterIVe > counterIS && counterIVe > counterIVi) {
            string = "Iris-versicolor";
        }else{
            string= trainList.get(0).getName();
        }

        return string;
    }

    public static String odlegloscEuklidesowa(List<Flower> trainList, double[] tabAvgTrain, double[] tabOdchylenieTrain, double SL, double SW, double PL, double PW, int k) {
        String string = "";
        int counterIS = 0;
        int counterIVe = 0;
        int counterIVi = 0;

        SL = normalizacjaDlaJednegoWektora(SL, tabAvgTrain[0], tabOdchylenieTrain[0]);
        SW = normalizacjaDlaJednegoWektora(SW, tabAvgTrain[1], tabOdchylenieTrain[1]);
        PL = normalizacjaDlaJednegoWektora(PL, tabAvgTrain[2], tabOdchylenieTrain[2]);
        PW = normalizacjaDlaJednegoWektora(PW, tabAvgTrain[3], tabOdchylenieTrain[3]);

        for (Flower flower : trainList) {
            flower.setOdleglosc(Math.sqrt(Math.pow(flower.getSepal_length_nor() - SL, 2) + Math.pow(flower.getSepal_width_nor() - SW, 2) + Math.pow(flower.getPetal_length_nor() - PL, 2) + Math.pow(flower.getPetal_width_nor() - PW, 2)));

        }

        Collections.sort(trainList, Comparator.comparingDouble(Flower::getOdleglosc));

        for (int i = 0; i < k; i++) {
            if (trainList.get(i).getName().equals("Iris-versicolor")) {
                counterIVe++;
            } else if (trainList.get(i).getName().equals("Iris-setosa")) {
                counterIS++;
            } else if (trainList.get(i).getName().equals("Iris-virginica")) {
                counterIVi++;
            }
        }

        if (counterIVi > counterIS && counterIVi > counterIVe) {
            string = "Iris-virginica";
        } else if (counterIS > counterIVi && counterIS > counterIVe) {
            string = "Iris-setosa";
        } else if (counterIVe > counterIS && counterIVe > counterIVi) {
            string = "Iris-versicolor";
        }else{
            string= trainList.get(0).getName();
        }

        return string;
    }

    public static double normalizacjaDlaJednegoWektora(double x, double y, double z) {

        return (x - y) / z;

    }


    public static List<Flower> TrainList(String dir) throws FileNotFoundException {

        List<Flower> tmp = new ArrayList<>();
        Scanner scanner = new Scanner(new File(dir));
        while (scanner.hasNextLine()) {
            String[] tab = scanner.nextLine().split(",");
            Flower flower = new Flower(tab[0], Double.parseDouble(tab[1]), Double.parseDouble(tab[2]), Double.parseDouble(tab[3]), Double.parseDouble(tab[4]), tab[5]);
            tmp.add(flower);
        }
        return tmp;
    }


}
