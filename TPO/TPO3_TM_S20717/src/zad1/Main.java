/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;


import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Italy");
    String weatherJson = s.getWeather("Rome");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();

    SwingUtilities.invokeLater(() -> {
      Window window = new Window(weatherJson, rate1,rate2,s.getCountry());
    });

  }
}
