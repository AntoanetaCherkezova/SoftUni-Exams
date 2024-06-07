package MidExam_18_Jun_2023;

import java.util.Scanner;

public class P01_TheHuntingGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());;
        int players = Integer.parseInt(scanner.nextLine());
        double totalEnergy = Double.parseDouble(scanner.nextLine());
        double waterPerDay = Double.parseDouble(scanner.nextLine());
        double foodPerDay = Double.parseDouble(scanner.nextLine());
        double totalWater = days * players * waterPerDay;
        double totalFood = days * players * foodPerDay;
        int countWaterDay = 0;
        int countfoodDay = 0;

        for (int i = 0; i < days; i++) {
            double spentEnergy = scanner.nextDouble();
            totalEnergy = totalEnergy - spentEnergy;

            if (totalEnergy <= 0.0) {
                break;
            }

            countWaterDay++;
            if (countWaterDay >= 2) {
                totalWater -= totalWater * 0.3;
                totalEnergy += totalEnergy * 0.05;
                countWaterDay = 0;
            }

            countfoodDay++;
            if (countfoodDay >= 3) {
                totalFood -= (totalFood / players);
                totalEnergy += totalEnergy * 0.1;
                countfoodDay = 0;
            }
        }

        if (totalEnergy >= 1) {
            System.out.printf("You are ready for the quest. You will be left with - %.2f energy!", totalEnergy);
        } else {
            System.out.printf("You will run out of energy. You will be left with %.2f food and %.2f water.", totalFood, totalWater);
        }


    }

}
