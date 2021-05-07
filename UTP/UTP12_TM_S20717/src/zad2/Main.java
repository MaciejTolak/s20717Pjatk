package zad2;
import zad1.Pack.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class <zad1.Pack.Main> mainClass = zad1.Pack.Main.class;
        Class <Buffer> bufferClass = zad1.Pack.Buffer.class;
        Class <Producer> producerClass = zad1.Pack.Producer.class;
        Class <Consumer> consumerClass = zad1.Pack.Consumer.class;

        data(mainClass);
        data(bufferClass);
        data(producerClass);
        data(consumerClass);

        Buffer buffer = Buffer.class.getConstructor(int.class).newInstance(2);
        buffer.getClass().getMethod("get").invoke(buffer);

    }
    public static void data(Class klasa){
        Field field [] = klasa.getFields();
        Constructor constructor [] = klasa.getDeclaredConstructors();
        Method method [] = klasa.getMethods();
        Class subClass = klasa.getSuperclass();


        for (int i = 0; i < method.length ; i++) {
            System.out.println(method[i]);
        }
        System.out.println();

        for (int i = 0; i < constructor.length ; i++) {
            if(constructor[i].getParameterCount()>0){
                System.out.println(constructor[i]);
            }
        }
        System.out.println();
        for (int i = 0; i < field.length ; i++) {
            System.out.println(field[i]);
        }

        System.out.println();

        while(subClass!=null){
            System.out.println(subClass.getName());
            subClass = subClass.getSuperclass();
        }

        System.out.println();


        Field [] fieldTmp = klasa.getSuperclass().getFields();

        for (int i = 0; i < fieldTmp.length ; i++) {
            System.out.println(fieldTmp[i]);
        }


    }
}
