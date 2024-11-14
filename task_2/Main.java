import java.util.Scanner;
 
public class Main {
   
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double a = in.nextDouble();
        double b = in.nextDouble();
          
        double d = 
            (Math.max(2 * a, b) - 10 * Math.sqrt(Math.min(a, 4 + b)) + 4.2 * Math.min(a, 4 + b)) 
            / 
            (1 + (Math.max(2 * a, b)) / (Math.min(a * 4, b)));

        System.out.println(d);

        in.close();
    }
}