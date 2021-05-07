/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Service {

    private String country;

    public Service(String country) {
        this.country = country;
    }

    public String getWeather(String city){

        try{
            return getWeatherTMP(city);
        }catch (IOException | ParseException ex){
            ex.printStackTrace();
        }



     return null;
    }


    public double getRateFor(String currency){

        try{
            return getRateForTMP(currency);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getNBPRate(){

        try {
            return getNBPRateTMP();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        return 0;
    }


    public String getWeatherTMP(String city) throws IOException, ParseException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=d78025d6ae585735f289fe0f23684493&units=metric");
        System.out.println(url);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String tmp;
        String all = "";
        while((tmp = bufferedReader.readLine())!= null){
            all+=tmp;
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(all);


        return jsonObject.toJSONString();
    }

    public Double getRateForTMP(String currency) throws IOException, ParseException {


        URL url = new URL("https://api.exchangerate.host/latest?base="+getCurrencyCode()+"&symbols="+currency);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String tmp;
        String all = "";
        while((tmp = bufferedReader.readLine())!= null){
            all+=tmp;
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(all);

       Map <String, Double> map = (Map<String, Double>) jsonObject.get("rates");

       return map.get(currency);

    }

    public double getNBPRateTMP() throws IOException, ParseException {

        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/A/"+getCurrencyCode()+"/");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String tmp;
        String all = "";
        while((tmp = bufferedReader.readLine())!= null){
            all+=tmp;
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(all);

        JSONArray jsonArray = (JSONArray) jsonObject.get("rates");

        Map<String, Double> map = (Map<String, Double>) jsonArray.get(0);

        return map.get("mid");

    }

    private String getCurrencyCode() {
        Currency currency = Currency.getInstance(new Locale("",getCountryCode()));
        return currency.getCurrencyCode();
    }

    private String getCountryCode(){
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            countries.put((new Locale("en", iso).getDisplayCountry(new Locale("en", iso))), iso);
        }
        return countries.get(country);
    }

    public String getCountry() {
        return country;
    }
}

