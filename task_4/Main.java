import java.util.Random;
import java.util.Vector;

public class RandomArrayExample {

    public static void main(String[] args) {
        final int rows = 5;
        final int cols = 5;
        int[][] randomNumbers = generateRandomNumbers(rows, cols);

        System.out.println("Generated numbers: ");
        printArray(randomNumbers, rows, cols);


        System.out.println("\nResult: ");
        Vector<Integer> result = getMaxColumnValues(randomNumbers, rows, cols);
        for (int i = 0; i < cols; i++) {
            System.out.println("Max value for " + i + " column = " + result.elementAt(i));
        }
    }

    public static int[][] generateRandomNumbers(final int rows, final int cols) {
        int[][] randomNumbers = new int[rows][cols];

        Random r = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                randomNumbers[i][j] = r.nextInt(100);
            }
        }

        return randomNumbers;
    }

    public static void printArray(int[][] array, final int rows, final int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static Vector<Integer> getMaxColumnValues(int[][] array, int rows, int cols) {
        Vector<Integer> v = new Vector<>();

        for (int x = 0; x < cols; x++) {
            int max = array[0][x];
            for (int y = 0; y < rows; y++) {
                if (array[y][x] > max) {
                    max = array[y][x];
                }
            }
            v.addElement(max);
        }

        return v;
    }
}
