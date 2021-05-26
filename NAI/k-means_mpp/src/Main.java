import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        List<Vector> listOfVectors = Converter(listCreator("bankloans 1.csv"));
        List<Cluster> listOfCLusters = new ArrayList<>();

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int rand = (int) (Math.random() * listOfVectors.size() - 1);
            listOfCLusters.add(new Cluster(listOfVectors.get(rand).wartosci));
        }

        while (counter != listOfCLusters.size()) {
            for (Cluster cluster : listOfCLusters){
                cluster.clearList();
            }
            Grouping(listOfVectors, listOfCLusters);
            counter = 0;
            for (Cluster cluster : listOfCLusters) {

                if (cluster.check()) {
                    counter++;
                }
                    cluster.changeCentroid();
            }

        }

        for (Cluster cluster : listOfCLusters) {
            for (double x : cluster.wartosci) {
                System.out.print(x + ",");
            }

            System.out.println(" "+cluster.list.size());
            System.out.println();
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

    public static List<Vector> Converter(List<String[]> listString) {

        List<Vector> listOfVectors = new ArrayList<>();
        for (String[] tab : listString) {
            List<Double> wartosci = new ArrayList<>();
            for (int i = 0; i < tab.length; i++) {
                wartosci.add(Double.parseDouble(tab[i]));
            }
            listOfVectors.add(new Vector(wartosci));
        }

        return listOfVectors;
    }

    public static void Grouping(List<Vector> listOfVectors, List<Cluster> listOfClusters) {

        for (Vector vector : listOfVectors) {

            Cluster closestCluster = null;
            double mindistance = 0;
            for (Cluster cluster : listOfClusters) {
                double res = euklides(vector.wartosci, cluster.wartosci);

                if (mindistance == 0) {
                    closestCluster = cluster;
                    mindistance = res;
                } else if (mindistance > res) {
                    closestCluster = cluster;
                    mindistance = res;
                }
            }
            closestCluster.list.add(vector);
        }

    }

    public static double euklides(List<Double> wartosciClienta, List<Double> wartosciCentroida) {
        double result = 0;

        for (int i = 0; i < wartosciCentroida.size(); i++) {

            result += Math.pow((wartosciClienta.get(i) - wartosciCentroida.get(i)), 2);
        }
        result = Math.sqrt(result);

        return result;
    }

}
