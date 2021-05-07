package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TableModel extends AbstractTableModel {
    private String line;
    private String fileName;
    private File plik;


    ArrayList<Panstwo> ListaPanstw;
    ArrayList<String> nazwyKolumn;


    TableModel(String fileName){
        this.fileName = fileName;
        plik = new File(fileName);
        Scanner in = null;
        ListaPanstw= new ArrayList<>();
        nazwyKolumn = new ArrayList<>();
        try {
            in = new Scanner(plik);
            line=in.nextLine();
            String [] tab1 = line.split("\\t");
            for(String s:tab1){
                nazwyKolumn.add(s);
                System.out.println(s);
            }
            while (in.hasNextLine()){
                line = in.nextLine();
                String [] tab = line.split("\\t");
                Panstwo p = new Panstwo(tab[0],tab[1],Integer.parseInt(tab[2]));
                ListaPanstw.add(p);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getRowCount() {
        return ListaPanstw.size();
    }

    @Override
    public int getColumnCount() {
        return nazwyKolumn.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==2){
            return true;
        }else
            return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(rowIndex==2){
            ListaPanstw.get(rowIndex).populacja = ((Integer)aValue);


        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (nazwyKolumn.get(columnIndex)){
            case"Country":
              return   ListaPanstw.get(rowIndex).getNazwa();

            case "Capital":
                return  ListaPanstw.get(rowIndex).getStolica();

            case "Population":
                return ListaPanstw.get(rowIndex).getPopulacja();

            case "Flag":
                return new ImageIcon(new ImageIcon("src/zad1/Image/flagapolski.png").getImage());
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0,columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        return nazwyKolumn.get(column);
    }
}
