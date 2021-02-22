import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double m = (double) scanner.nextInt();
        double p = (double) scanner.nextInt();
        double k = (double) scanner.nextInt();
        int yearCount = 0;
        double currentDeposit = m;
        //System.out.println("m p k as double: " + m + " " + p + " " + k);
        while(currentDeposit < k) {
            currentDeposit = currentDeposit + currentDeposit * (p * 0.01);
            yearCount++;
            //System.out.println("depStatus: " + currentDeposit + " years are passed: " + yearCount);
        }      
        System.out.println(yearCount);
    }
}
