package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList <T> extends ArrayList<T> {

    public XList(Collection <T> coll){
        for(T t : coll){
         this.add(t);
        }
    }

    public XList(T ... tab){
        for(int i=0;i<tab.length;i++){
            this.add(tab[i]);
        }
    }


    public static <T> XList <T> of(Collection <T> coll){
        return new XList(coll);
    }

    public static <T> XList<T> of(T ... tab){
        return new XList(tab);
    }

    public static <T> XList<T> charsOf(String string){

        List<Character> list = new ArrayList<>();
        for (int i = 0; i <string.length() ; i++) {
            list.add(string.charAt(i));
        }
       return new XList(list);

    }
    public static <T> XList<T> tokensOf(String ... string){

        List<String> list = new ArrayList<>();

        if(string.length==1){
            String tab [] = string[0].split(" ");
            for (int i = 0; i < tab.length ; i++) {
                list.add(tab[i]);
            }
        }else if(string.length==2){
            String tab [] = string[0].split(string[1]);
            for (int i = 0; i < tab.length ; i++) {
                list.add(tab[i]);
            }
        }

        return new XList(list);
    }

    public XList <T> union(Collection<T> coll){

        List<T> list = new ArrayList<>(this);

        list.addAll(coll);
        return new XList(list);

    }

    public XList <T> union(T ... t){

        List<T> list = new ArrayList<>(this);

        for (int i = 0; i <t.length ; i++) {
            list.add(t[i]);
        }
        return new XList(list);
    }

    public XList diff(Collection<T> coll){
        List<T> list = new ArrayList<>(this);
        list.removeAll(coll);
        return new XList(list);
    }


    public XList unique(){

        List<T> list = new ArrayList<>();
        for (T t: this) {
            if(!list.contains(t)){
                list.add(t);
            }
        }
        return new XList(list);
    }

    public<K> XList<K> collect(Function <T,K> function){
        List<K> list = new ArrayList<>();
        for(T t : this){
            list.add(function.apply(t));
        }
        return new XList(list);
    }

    public String join (String ... tab){
        String string="";

        List<T> list = new ArrayList<>(this);
        if(tab.length == 0){
            for (int i = 0; i <list.size(); i++) {
                string+=list.get(i);
            }
        }else if(tab.length==1){
            for (int i = 0; i < list.size(); i++) {
                string+=list.get(i);
                if(i<list.size()-1){
                    string+=tab[0];
                }
            }
        }
        return string;
    }


    public void forEachWithIndex(BiConsumer<T,Integer> biConsumer){

        for (int i = 0; i <this.size() ; i++) {
            biConsumer.accept(this.get(i),i);
        }

    }

    public  XList<T> combine(){
        List<T> list = new ArrayList<>();
        return new XList(list);
    };


}
