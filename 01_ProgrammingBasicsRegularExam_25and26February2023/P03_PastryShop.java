package ProgrammingBasicsOnlineRegularExam_25and26February2023;

import java.util.Scanner;

public class P03_PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sweet = scanner.nextLine();
        int sweetCount = Integer.parseInt(scanner.nextLine());
        int date = Integer.parseInt(scanner.nextLine());

        double price = 0;

        switch (sweet) {
            case "Cake":
                if (date <= 15) {
                    price = sweetCount * 24;
                } else {
                    price = sweetCount * 28.70;
                }
                break;
            case "Souffle":
                if (date <= 15) {
                    price = sweetCount * 6.66;
                } else {
                    price = sweetCount * 9.80;
                }
                break;
            case "Baklava":
                if (date <= 15) {
                    price = sweetCount * 12.60;
                } else {
                    price = sweetCount * 16.98;
                }
                break;
        }

        if (date <= 22) {
            if (price >= 100 && price <= 200) {
                price = price * 0.85;
            } else if (price > 200) {
                price = price * 0.75;
            }
        }
        if (date <= 15) {
            price = price * 0.9;
        }

        System.out.printf("%.2f", price);

    }
}
