import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int len = scanner.nextInt();

        if (len == 1) {
            System.out.println(flag);
            return;
        }

        int[] numbers = new int[len];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }
        
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] < numbers[i+1]) {
                continue;
            } else {
                flag = false;
                break;
            }
        }
        System.out.println(flag);
    }
}
