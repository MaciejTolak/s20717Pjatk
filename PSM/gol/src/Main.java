
import java.util.ArrayList;
import java.util.Scanner;


class Gol {

    private final char Alive = '$';
    private final char Dead = '.';

    private int rows;
    private int colms;

    private boolean[][] matrix;
    private ArrayList<Integer> RegulaPrzyzywania = new ArrayList<>();
    private ArrayList<Integer> RegulaOzywania = new ArrayList<>();


    public Gol(int rows, int colms) {

        this.rows = rows;
        this.colms = colms;

        matrix = new boolean[rows][colms];
    }

    public void printMatrix(){

        for(boolean wiersz[] : matrix){
            for(boolean element : wiersz){
                if(element){
                    System.out.print(Alive +" ");
                }else{
                    System.out.print(Dead +" ");
                }
            }

            System.out.println();
        }
    }


    public void startingCells() {

        //losowane miejsca
       /* for (int i = 0; i< colms; i++){

            matrix[(int)(Math.random()* rows)][(int)(Math.random()* colms)] = true;

        }
        
        */
        //Komorki ustawione na sztywno

        matrix[5][5] = true; matrix[5][7] = true; matrix[5][9] = true;
        matrix[6][5] = true; matrix[6][9] = true;
        matrix[7][5] = true; matrix[7][9] = true;
        matrix[8][5] = true; matrix[8][9] = true;
        matrix[9][5] = true; matrix[9][7] = true; matrix[9][9] = true;


    }

    private int numberOfNeighbor(int x, int y) {
        int neighbor = 0;
        for(int i = x-1; i <= x+1; i++) {
            int tmpX = i;
            if(i < 0)
                tmpX = rows - 1;
            if(i> (rows - 1) )
                tmpX = 0;

            for(int j = y-1; j <= y+1; j++) {

                if((i == x && j == y))
                   continue;
                int tmpY = j;
                if (j < 0)
                    tmpY = colms -1;
                if(j> colms - 1)
                    tmpY = 0;
                if(matrix[tmpX][tmpY]) neighbor++;
            }
        }
        return neighbor;
    }


    private boolean evolve(int x, int y) {


        int neighbor = numberOfNeighbor(x, y);

        if(matrix[x][y]) {

            if(RegulaPrzyzywania.contains(neighbor))
                return true;
            else
                return false;

        } else {
            if(RegulaOzywania.contains(neighbor))
                return true;
        }

        return false;
    }


    public void nextGen() {

        boolean[][] matrixTMP = new boolean[rows][colms];


        for(int i = 0; i < rows; i++)
            for(int j = 0; j < colms; j++)
                matrixTMP[i][j] = evolve(i, j);

        matrix = matrixTMP;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wymiary planszy");
        Gol gol = new Gol(scanner.nextInt(), scanner.nextInt());
        System.out.println("Podaj dla jakiej liczby sasiadow zywa komorka przezyje. (Podaj 0 jezeli chcesz skonczyć dodawać reguly)");
        while(true){
            int x = scanner.nextInt();
            if(x>0 && x<=8){
                gol.RegulaPrzyzywania.add(x);
            }else{
                break;
            }

        }
        System.out.println("Podaj dla jakiej liczby sasiadow martwa komorka ozyje. (Podaj 0 jezeli chcesz skonczyć dodawać reguly)");
        while(true){
            int x = scanner.nextInt();
            if(x>0 && x<=8){
                gol.RegulaOzywania.add(x);
            }else{
                break;
            }

        }

        System.out.println("Gra w życie - [Enter] generuje następne pokolenie, dowolny znak i [Enter] kończy grę.");

        gol.startingCells();

        gol.printMatrix();


        while(scanner.nextLine().equals("")) {
            gol.nextGen();
            gol.printMatrix();
        }
    }
}