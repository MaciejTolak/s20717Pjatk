import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    private double bias = 0.5;

    private double learningRate = 0.01;
    List<Double> wagi = new ArrayList<>();


    public Perceptron(int dai){

        GenerateWeights(dai);
    }

    public void GenerateWeights(int diamensions){

        wagi = new ArrayList<Double>();
        for(int i = 0;i<diamensions;i++){
            double waga = Math.random()*(5-2+1) + 2;
            wagi.add(waga);
        }

    }

    public boolean Train(List<Double> list, int d){

        int y = 0;

        if(Scalar(list) >=bias)
        {
            y=1;
        }

        if(d!=y){
            ModifyWeights(list,d,y);
            return false;
        }else{
            return true;
    }



    }

    public boolean Test(List<Double> list, int d){
        int y = 0;

        if(Scalar(list) >=bias)
        {
            y=1;
        }
        if(d!=y){
            return false;
        }else{
            return true;
        }

    }

    public boolean TestDlaWektora(List<Double> list){


        return Scalar(list) >=bias;


    }


    public double Scalar(List<Double> list){

        double result = 0;

        for(int i = 0;i<list.size();i++){
            result += wagi.get(i) * list.get(i);
        }

        return result;
    }

    public void ModifyWeights(List<Double> list,int d, int y){
        for(int i=0;i<list.size();i++){
            wagi.set(i, wagi.get(i) + (d - y)* list.get(i) * learningRate);
        }
        bias -= (d - y) * learningRate;
    }


    public static void Info(List<Double> list, List<Double> listmp, double bias, int d, int y){
        System.out.println("input");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

        System.out.println("Wagi"+ listmp);
            System.out.println("Bias: "+bias);
            System.out.println("d: "+ d);
            System.out.println("y "+ y);
            System.out.println();

    }

}
