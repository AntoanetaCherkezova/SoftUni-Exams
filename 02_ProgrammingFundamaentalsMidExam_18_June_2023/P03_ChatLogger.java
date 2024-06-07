package MidExam_18_Jun_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P03_ChatLogger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> stringList = new ArrayList<>();
        String input = scanner.nextLine();

        while(!input.equals("end")) {
            String[] command = input.split("\\s+");
            String commandType = command[0];
            String message = command[1];
            switch (commandType) {
                case "Chat":
                    stringList.add(message);
                    break;
                case "Delete":
                    stringList.remove(message);
                    break;
                case "Edit":
                    String editedVersion = command[2];
                    if (stringList.contains(message)) {
                        int indexOfElement = stringList.indexOf(message);
                        stringList.set(indexOfElement, editedVersion);
                    }
                    break;
                case "Pin":
                    if (stringList.contains(message)) {
                        stringList.remove(message);
                        stringList.add(message);
                    }
                    break;
                case "Spam":
                    List<String> spamMessages = new ArrayList<>();
                    for (int i = 1; i < command.length; i++) {
                        spamMessages.add(command[i]);
                    }
                    stringList.addAll(spamMessages);
                    break;
            }
            input = scanner.nextLine();
        }

        for (String message : stringList) {
            System.out.println(message);
        }
    }
}
