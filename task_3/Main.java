import java.util.Random;

public class RandomArrayExample {

    public static void main(String[] args) {
        final int length = 10;

        System.out.println("Generated numbers: ");
        int[] randomNumbers = generateRandomNumbers(10);
        printArray(randomNumbers);

        System.out.println("Allowed numbers: ");
        int[] allowedNumbers = new int[] { randomNumbers[0], randomNumbers[1] };
        printArray(allowedNumbers);


        int allowedCount = countAllowed(randomNumbers, allowedNumbers);
        System.out.println("Count of the allowed numbers: " + allowedCount);
    }

    public static int[] generateRandomNumbers(final int length) {
        int[] randomNumbers = new int[length];

        Random r = new Random();

        for (int i = 0; i < length; i++) {
            randomNumbers[i] = r.nextInt(100);
        }

        return randomNumbers;
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static int countAllowed(int[] numbers, int[] allowedNumbers) {
        int allowedCount = 0;

        for (int number : numbers) {
            for (int allowed : allowedNumbers) {
                if (number == allowed) {
                    allowedCount++;
                    break;
                }
            }
        }

        return allowedCount;
    }
}