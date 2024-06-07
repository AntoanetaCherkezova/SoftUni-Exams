package ProgrammingBasicsOnlineRegularExam_25and26February2023;

import java.util.Scanner;

public class P01_MiningRig {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pricePerCard = Integer.parseInt(scanner.nextLine());
        int pricePerTransition = Integer.parseInt(scanner.nextLine());
        double electricityConsumed = Double.parseDouble(scanner.nextLine());
        double profitPerCard = Double.parseDouble(scanner.nextLine());

        double totalPriceCard = pricePerCard * 13;
        double totalPriceTransition = pricePerTransition * 13;
        double totalMoneySpent = totalPriceCard + totalPriceTransition + 1000;
        double profitPerDay = (profitPerCard - electricityConsumed) * 13;

        double returnDays = Math.ceil(totalMoneySpent / profitPerDay);
        System.out.printf("%.0f%n", totalMoneySpent);
        System.out.printf("%.0f", returnDays);
    }
}
