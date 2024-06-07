package FinalExam_30_07_2023;

import java.util.Scanner;

public class P01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputMessage = scanner.nextLine();

        String command = scanner.nextLine();

        while (!command.equals("Finish")) {
            if (command.contains("Replace")) {
                String currentChar = command.split(" ")[1];
                String newChar = command.split(" ")[2];
                inputMessage = inputMessage.replace(currentChar, newChar);
                System.out.println(inputMessage);

            } else if (command.contains("Cut")) {
                int startIndex = Integer.parseInt(command.split(" ")[1]);
                int endIndex = Integer.parseInt(command.split(" ")[2]);

                if(startIndex >= 0 && startIndex <= inputMessage.length() && endIndex >= 0 && endIndex <= inputMessage.length()) {
                    String substring = inputMessage.substring(startIndex, endIndex + 1);
                    inputMessage = inputMessage.replace(substring, "");
                    System.out.println(inputMessage);
                } else {
                    System.out.println("Invalid indices!");
                }

            } else if (command.contains("Make")) {
                String upperOrLower = command.split(" ")[1];

                if(upperOrLower.equals("Upper")){
                    inputMessage = inputMessage.toUpperCase();
                    System.out.println(inputMessage);
                } else if (upperOrLower.equals("Lower")){
                    inputMessage = inputMessage.toLowerCase();
                    System.out.println(inputMessage);
                }
                
            } else if (command.contains("Check")) {
                String checkString = command.split(" ")[1];
                if (inputMessage.contains(checkString)) {
                    System.out.printf("Message contains %s%n", checkString);
                } else {
                    System.out.printf("Message doesn't contain %s%n", checkString);
                }

            } else if (command.contains("Sum")) {
                int startIndex = Integer.parseInt(command.split(" ")[1]);
                int endIndex = Integer.parseInt(command.split(" ")[2]);

                if(startIndex >= 0 && startIndex <= inputMessage.length() && endIndex >= 0 && endIndex <= inputMessage.length()) {
                    String substring = inputMessage.substring(startIndex, endIndex + 1);

                    int sum = 0;
                    for (char symbol : substring.toCharArray()) {
                        sum = sum + (int) symbol;
                    }
                    System.out.println(sum);

                } else {
                   System.out.println("Invalid indices!");
                }
            }

            command = scanner.nextLine();
        }
    }

}
