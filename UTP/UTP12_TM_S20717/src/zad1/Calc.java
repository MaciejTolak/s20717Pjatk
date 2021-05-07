/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    private Dzialania dzialania;
    private Dodawanie dodawanie;
    private Odejmowanie odejmowanie;
    private Mnozenie mnozenie;
    private Dzielenie dzielenie;
    private Map<String, Method> map;
    private String [] tab;
    private Map<String, Object> map2;

    public Calc() {
        map = new HashMap<>();
        dzialania = new Dzialania();
        dodawanie = new Dodawanie();
        odejmowanie = new Odejmowanie();
        mnozenie = new Mnozenie();
        dzielenie = new Dzielenie();
        map2 = new HashMap<>();

        map2.put("+",dodawanie);
        map2.put("-",odejmowanie);
        map2.put("*",mnozenie);
        map2.put("/",dzielenie);

    }

    public String doCalc(String string){
        tab = string.split("\\s");
        try {
            map.put("+",dodawanie.getClass().getDeclaredMethod("dodaj", BigDecimal.class, BigDecimal.class));
            map.put("-",odejmowanie.getClass().getDeclaredMethod("odejmowanie", BigDecimal.class, BigDecimal.class));
            map.put("*",mnozenie.getClass().getDeclaredMethod("pomnoz", BigDecimal.class, BigDecimal.class));
            map.put("/",dzielenie.getClass().getDeclaredMethod("podziel", BigDecimal.class, BigDecimal.class));
            return ""+map.get(tab[1]).invoke(map2.get(tab[1]),BigDecimal.valueOf(Double.parseDouble(tab[0])),BigDecimal.valueOf(Double.parseDouble(tab[2])));
        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid command to calc";
        }



    }
}
