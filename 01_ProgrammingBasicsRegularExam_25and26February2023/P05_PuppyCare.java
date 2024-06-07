package ProgrammingBasicsOnlineRegularExam_25and26February2023;

import java.util.Scanner;

public class P05_PuppyCare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int availableKgFood = Integer.parseInt(scanner.nextLine());
        String command  = scanner.nextLine();
        int sumEatenGrFood = 0;

        while (!command.equals("Adopted")) {
            int eatenGrFood = Integer.parseInt(command);

            sumEatenGrFood += eatenGrFood;

            command = scanner.nextLine();
        }

        availableKgFood = availableKgFood * 1000;
        int diff = Math.abs(availableKgFood - sumEatenGrFood);
        if (availableKgFood >= sumEatenGrFood) {
            System.out.printf("Food is enough! Leftovers: %d grams.", diff);
        } else {
            System.out.printf("Food is not enough. You need %d grams more.", diff);
        }
    }

}
