package zad1;

import javax.swing.*;

public class CountryTable extends JTable {

    private String nazwaPliku;
    JTable tabela;

    public CountryTable(String nazwaPliku){
        this.nazwaPliku = nazwaPliku;
    }

    public JTable create(){
        tabela = new JTable();
        tabela.setRowHeight(40);
        tabela.setModel(new TableModel(nazwaPliku));
        tabela.setDefaultRenderer(Integer.class,new MyTableRenderer());
        return tabela;


    }

}
