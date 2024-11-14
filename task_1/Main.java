import java.util.Scanner;
 
public class Main {
   
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int x = in.nextInt();
        int y = in.nextInt();
          
        if (x + y > 20) {
            System.out.println(Math.pow(x, 2) * 3);
        } else {
            System.out.println(Math.pow(y, 3));
        }

        in.close();
    }
}