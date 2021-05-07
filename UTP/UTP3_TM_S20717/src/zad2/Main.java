/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad2;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream().filter(sel -> {
      if (sel.startsWith("WAW"))
        return true;
      else
        return false;
    }).map(map -> {
      String[] tab = map.split("\\s");
      return "to " + tab[1] + " - price in PLN: " + (int)(Integer.parseInt(tab[2]) * ratePLNvsEUR);
    }).collect(Collectors.toList());


    for (String r : result) System.out.println(r);
  }
}
