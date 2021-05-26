import java.util.ArrayList;
import java.util.List;

public class Cluster {
    List<Double> wartosci;
    List<Vector> list;

    public Cluster(List<Double> wartosci) {
        this.wartosci = wartosci;
        list = new ArrayList<>();
    }

    public boolean check(){
        int counter = 0;
        double tmp;
        for(int i =0; i<wartosci.size(); i++){
            tmp =0;
            for(Vector vector : list){
                tmp += vector.wartosci.get(i);
            }
            tmp = tmp/list.size();
            if(tmp == wartosci.get(i)){
                counter++;
            }
        }
        if(counter == wartosci.size()){
            return true;
        }else{
            return false;
        }
    }

    public void changeCentroid(){
        double tmp;
        for(int i =0; i<wartosci.size(); i++) {
            tmp = 0;
            for (Vector vector : list) {
                tmp += vector.wartosci.get(i);
            }
            tmp = tmp/list.size();
            this.wartosci.set(i,tmp);
        }
        list.clear();

    }

}
