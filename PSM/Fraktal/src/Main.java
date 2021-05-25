import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static String rule1 = "F+[[X]-X]-F[-FX]+X";
    public static String rule2 = "FF";
    public static int angle = 90 - 25;
    public static double x = 0;
    public static double y = 0;
    public static double alfa = Math.toRadians(angle);

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String word = wordCreator(n);
        toFile(word);

    }

    public static String wordCreator(int n){
        String word = "X";
        for(int i = 0; i < n; i++){
            word = word.replaceAll("F", rule2);
            word = word.replaceAll("X", rule1);
        }
        System.out.println(word);
        return word;

    }

    public static void toFile(String word) throws IOException {
        Files.deleteIfExists(Paths.get("result.txt"));
        Stack<Point> stack = new Stack<>();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.txt"));
        bufferedWriter.write(x+";"+y+"\n");
        for(int i = 0; i < word.length(); i++){
            String oneChar = String.valueOf(word.charAt(i));
            if(oneChar.equals("+")){
                alfa = alfa + Math.toRadians(25);
                bufferedWriter.write(x + ";"+ y + "\n");
            }else if(oneChar.equals("-")){
                alfa = alfa - Math.toRadians(25);
                bufferedWriter.write(x + ";"+ y + "\n");
            }else if (oneChar.equals("[")){
                Point point = new Point(x,y,alfa);
                stack.push(point);
            }else if(oneChar.equals("]")){
                Point point = stack.pop();
                x = point.getX();
                y = point.getY();
                alfa = point.getAlfa();
                bufferedWriter.write("; \n");
            }else if(oneChar.equals("F")){
                x = x+Math.cos(alfa);
                y = y+Math.sin(alfa);
                bufferedWriter.write(x + ";"+ y + "\n");
            }
        }
        bufferedWriter.close();
}
}
