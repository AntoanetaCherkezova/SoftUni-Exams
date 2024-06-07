package ProgrammingBasicsOnlineRegularExam_25and26February2023;

import java.util.Scanner;

public class P06_GoldMine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int locationCount = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= locationCount; i++) {

          double expectedAverageYieldPerDay = Double.parseDouble(scanner.nextLine());
          int daysCount = Integer.parseInt(scanner.nextLine());

          double averageYield = 0;

          for (int j = 1; j <= daysCount; j++) {
              double yieldPerDay = Double.parseDouble(scanner.nextLine());
              averageYield = averageYield + yieldPerDay;
          }

          averageYield = averageYield / daysCount;

          if (averageYield >= expectedAverageYieldPerDay) {
              System.out.printf("Good job! Average gold per day: %.2f.%n", averageYield);
          } else {
              System.out.printf("You need %.2f gold.%n", expectedAverageYieldPerDay - averageYield);
          }
        }

    }
}
