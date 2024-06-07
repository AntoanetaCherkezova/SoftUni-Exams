package MidExam_18_Jun_2023;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_GPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> commands = Arrays.stream(scanner.nextLine().split("\\|\\|"))
                .collect(Collectors.toList());

        int initialFuel = Integer.parseInt(scanner.nextLine());
        int initialAmmunition = Integer.parseInt(scanner.nextLine());
        boolean missionFailed = false;

        for (String command : commands) {
            String[] commandParts = command.split("\\s+");
            String commandType = commandParts[0];


            switch (commandType) {
                case "Travel":
                    int commandValue = Integer.parseInt(commandParts[1]);
                    if (initialFuel >= commandValue) {
                        initialFuel -= commandValue;
                        System.out.printf("The spaceship travelled %d light-years.%n", commandValue);
                    } else {
                        System.out.println("Mission failed.");
                        missionFailed = true;
                    }
                    break;
                case "Enemy":
                    int commandValueEnemy = Integer.parseInt(commandParts[1]);
                    if (initialAmmunition >= commandValueEnemy) {
                        initialAmmunition -= commandValueEnemy;
                        System.out.printf("An enemy with %d armour is defeated.%n", commandValueEnemy);
                    } else if (commandValueEnemy * 2 < initialFuel) {
                        initialFuel = commandValueEnemy * 2;
                        System.out.printf("An enemy with %d armour is outmaneuvered.%n", commandValueEnemy);
                    } else {
                        System.out.println("Mission failed.");
                        missionFailed = true;
                    }
                    break;
                case "Repair":
                    int commandValueRepair = Integer.parseInt(commandParts[1]);
                    initialFuel += commandValueRepair;
                    initialAmmunition += commandValueRepair * 2;
                    System.out.printf("Ammunitions added: %d.%n", commandValueRepair * 2);
                    System.out.printf("Fuel added: %d.%n", commandValueRepair);
                    break;
                case "Titan":
                    System.out.println("You have reached Titan, all passengers are safe.");
                    break;
            }

            if (missionFailed) {
                break;
            }
        }
    }
}


