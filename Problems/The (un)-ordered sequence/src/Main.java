import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String [] buffer = input.split(" ");
        int [] numbers = new int [buffer.length - 1];
        int count;

//        if(numbers.length == 1) {
//            System.out.println("false");
//            return;
//        }

        for(int i = 0; i < buffer.length - 1; i++) {
            numbers[i] = Integer.parseInt(buffer[i]);
        }

        /*count = 0;
        for (int i = 0; i < numbers.length - 1; i++, count++) {
            if(numbers[i] == numbers[i+1]) {
                continue;
            } else {
                break;
            }
        }

        if(count == numbers.length - 1) {
            System.out.println("false");
            return;
        }*/

        count = 0;
        for (int i = 0; i < numbers.length - 1; i++, count++) {
            if(numbers[i] >= numbers[i+1]) {
                continue;
            } else {
                break;
            }
        }
        if(count == numbers.length - 1) {
            System.out.println("true");
            return;
        } else {
            count = 0;
            for (int i = 0; i < numbers.length - 1; i++, count++) {
                if(numbers[i] <= numbers[i+1]) {
                    continue;
                } else {
                    break;
                }
            }
        }
        if(count == numbers.length - 1) {
            System.out.println("true");
            return;
        } else {
            System.out.println("false");
        }

    }
}