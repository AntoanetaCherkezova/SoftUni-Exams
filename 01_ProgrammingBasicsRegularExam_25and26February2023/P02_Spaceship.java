package ProgrammingBasicsOnlineRegularExam_25and26February2023;

import java.util.Scanner;

public class P02_Spaceship {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double width = Double.parseDouble(scanner.nextLine());
        double lenght = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());
        double averageHeight = Double.parseDouble(scanner.nextLine());

        double rocketSpace = width * lenght * height;
        double roomSpace = (averageHeight + 0.4) * 2 * 2;
        double peopleCount = Math.floor(rocketSpace / roomSpace);

        if (peopleCount < 3) {
            System.out.println("The spacecraft is too small.");
        } else if (peopleCount <= 10) {
            System.out.printf("The spacecraft holds %.0f astronauts.%n", peopleCount);
        } else {
            System.out.println("The spacecraft is too big.");
        }
    }
}
