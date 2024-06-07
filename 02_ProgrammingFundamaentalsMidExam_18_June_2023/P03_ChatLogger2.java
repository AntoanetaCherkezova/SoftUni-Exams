package MidExam_18_Jun_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P03_ChatLogger2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> chatLogger = new ArrayList<>();
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String[] commandParts = command.split("\\s+");
            String commandType = commandParts[0];
            String message = commandParts[1];

            switch (commandType) {
                case "Chat":
                    chatLogger.add(message);
                    break;
                case "Delete":
                    chatLogger.remove(message);
                    break;
                case "Edit":
                    String editVersion = commandParts[2];
                    if (chatLogger.contains(message)) {
                        int index = chatLogger.indexOf(message);
                        chatLogger.set(index, editVersion);
                    }
                    break;
                case "Pin":
                    if (chatLogger.contains(message)) {
                        chatLogger.remove(message);
                        chatLogger.add(message);
                    }
                    break;
                case "Spam":
                    List<String> commandPartsList = Arrays.asList(command.split(" "));
                    List<String> messageSpam = commandPartsList.subList(1, commandPartsList.size());
                    chatLogger.addAll(messageSpam);
                    break;
            }

            command = scanner.nextLine();
        }

        for (String item : chatLogger) {
            System.out.println(item);
        }

    }
}
