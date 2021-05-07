package zad1;

public class Panstwo {

    String nazwa,stolica;
    int populacja;

    public Panstwo(String nazwa,String stolica,int populacja){
        this.nazwa = nazwa;
        this.stolica = stolica;
        this.populacja = populacja;
    }

    public String getNazwa() {
        return nazwa;
    }


    public String getStolica() {
        return stolica;
    }


    public int getPopulacja() {
        return populacja;
    }

}
