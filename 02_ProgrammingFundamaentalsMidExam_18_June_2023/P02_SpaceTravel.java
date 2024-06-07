package MidExam_18_Jun_2023;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_SpaceTravel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> initialList = Arrays.stream(scanner.nextLine().split("\\|\\|"))
                .collect(Collectors.toList());

        int initialFuel = Integer.parseInt(scanner.nextLine());
        int initialAmmunition = Integer.parseInt(scanner.nextLine());


        for (int i = 0; i < initialList.size(); i++) {
            String command = initialList.get(i);
            String commandType = command.split("\\s+")[0];


            if (commandType.equals("Travel")) {
                int commandNumber = Integer.parseInt(command.split(" ")[1]);
                if (initialFuel >= commandNumber) {
                    initialFuel -= commandNumber;
                    System.out.printf("The spaceship travelled %d light-years.%n", commandNumber);
                } else {
                    System.out.println("Mission failed.");
                    return;
                }

            } else if (commandType.equals("Enemy")) {
                int commandNumber = Integer.parseInt(command.split(" ")[1]);
                if (initialAmmunition >= commandNumber) {
                    initialAmmunition -= commandNumber;
                    System.out.printf("An enemy with %d armour is defeated.%n", commandNumber);
                } else {
                    if (commandNumber * 2 < initialFuel) {
                        System.out.printf("An enemy with %d armour is outmaneuvered.%n", commandNumber);
                        initialFuel -= commandNumber * 2;
                    } else {
                        System.out.println("Mission failed.");
                        return;
                    }
                }

            } else if (commandType.equals("Repair")) {
                int commandNumber = Integer.parseInt(command.split(" ")[1]);
                initialFuel += commandNumber;
                initialAmmunition += commandNumber * 2;
                System.out.printf("Ammunitions added: %d.%n", commandNumber * 2);
                System.out.printf("Fuel added: %d.%n", commandNumber);

            } else if (commandType.equals("Titan")) {
                    System.out.println("You have reached Titan, all passengers are safe.");
            }
        }
    }
}
