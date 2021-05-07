import java.util.Comparator;

public class Flower  {
    private String Id;
    private double sepal_length;
    private double sepal_width;
    private double petal_length;
    private double petal_width;
    private String name;

    private double sepal_length_nor;
    private double sepal_width_nor;
    private double petal_length_nor;
    private double petal_width_nor;

    private double odleglosc;

    public Flower(String Id, double sepal_length, double sepal_width, double petal_length, double petal_width, String name) {
        this.Id = Id;
        this.sepal_length = sepal_length;
        this.sepal_width = sepal_width;
        this.petal_length = petal_length;
        this.petal_width = petal_width;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public double getSepal_length() {
        return sepal_length;
    }

    public double getSepal_width() {
        return sepal_width;
    }

    public double getPetal_length() {
        return petal_length;
    }

    public double getPetal_width() {
        return petal_width;
    }

    public double getSepal_length_nor() {
        return sepal_length_nor;
    }

    public void setSepal_length_nor(double sepal_length_nor) {
        this.sepal_length_nor = sepal_length_nor;
    }

    public double getSepal_width_nor() {
        return sepal_width_nor;
    }

    public void setSepal_width_nor(double sepal_width_nor) {
        this.sepal_width_nor = sepal_width_nor;
    }

    public double getPetal_length_nor() {
        return petal_length_nor;
    }

    public void setPetal_length_nor(double petal_length_nor) {
        this.petal_length_nor = petal_length_nor;
    }

    public double getPetal_width_nor() {
        return petal_width_nor;
    }

    public void setPetal_width_nor(double petal_width_nor) {
        this.petal_width_nor = petal_width_nor;
    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }


}

