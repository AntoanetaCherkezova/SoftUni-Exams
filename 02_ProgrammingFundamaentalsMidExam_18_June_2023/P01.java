package MidExam_18_Jun_2023;

import java.util.Scanner;

public class P01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int players = Integer.parseInt(scanner.nextLine());
        double energy = Double.parseDouble(scanner.nextLine());;
        double waterPerDay = Double.parseDouble(scanner.nextLine());
        double foodPerDay = Double.parseDouble(scanner.nextLine());
        double totalWater = days * players * waterPerDay;
        double totalFood = days * players * foodPerDay;


        for (int i = 1; i <= days; i++) {
            double currentEnergy = Double.parseDouble(scanner.nextLine());
            energy = energy - currentEnergy;

            if (energy <= 0) {
                break;
            }

            if (i % 2 == 0) {
                energy = energy * 1.05;
                totalWater = totalWater * 0.7;
            }

            if (i % 3 == 0) {
                totalFood = totalFood - (totalFood / players);
                energy = energy * 1.1;
            }
        }

        if (energy > 0) {
            System.out.printf("You are ready for the quest. You will be left with - %.2f energy!%n", energy);
        } else {
            System.out.printf("You will run out of energy. You will be left with %.2f food and %.2f water.", totalFood, totalWater);
        }
    }
}
