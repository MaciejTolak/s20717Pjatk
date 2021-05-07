/**
 * @author Tolak Maciej S20717
 */

package zad1;


import java.util.*;

public class Main {

    static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                .when(sel -> {
                    if (sel.startsWith("WAW"))
                        return true;
                    else
                        return false;
                })
                .mapEvery(
                        map -> {
                            String[] tab = map.split("\\s");
                            return "to " + tab[1] + " - price in PLN: " + (int)(Integer.parseInt(tab[2]) * xrate);
                        }
                );
    }

    public static void main(String[] args) {
        List<String> dest = Arrays.asList(
                "bleble bleble 2000",
                "WAW HAV 1200",
                "xxx yyy 789",
                "WAW DPS 2000",
                "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
        for (String r : result) System.out.println(r);
    }
}
