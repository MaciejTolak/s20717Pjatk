package Kasia.zad1;

public class Purchase {
    private String id;
    private String imie;
    private String nazwisko;
    private String towar;
    private double ilosc;
    private double cena;
    private double cenaCalkowita;

    public Purchase(String id, String imie, String nazwisko, String towar, double ilosc, double cena) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.towar = towar;
        this.ilosc = ilosc;
        this.cena = cena;
        cenaCalkowita = ilosc*cena;
    }

    public String getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public double getCenaCalkowita() {
        return cenaCalkowita;
    }
    @Override
    public String toString() {
        return id + ";" + nazwisko + " " + imie + ";" + towar + ";" + ilosc + ";" + cena;
    }
}
