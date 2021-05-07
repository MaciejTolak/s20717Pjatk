/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;


public class Purchase {
    private String id;
    private String name;
    private String surname;
    private String commodity;
    private double count;
    private double price;
    private double sumPrice;

    public Purchase(String id, String name, String surname, String commodity, double count, double price) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.commodity = commodity;
        this.count = count;
        this.price = price;
        sumPrice = count*price;
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    @Override
    public String toString() {
        return id + ";" + surname + " " + name + ";" + commodity + ";" + count + ";" + price;
    }
}
