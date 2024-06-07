package MidExam_18_Jun_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P03_ChatLogger1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> chatLogger = new ArrayList<>();
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String[] commandParts = command.split("\\s+");
            String commandType = commandParts[0];

            switch (commandType) {
                case "Chat":
                    String messageChat = commandParts[1];
                    chatLogger.add(messageChat);
                    break;
                case "Delete":
                    String messageDelete = commandParts[1];
                    chatLogger.remove(messageDelete);
                    break;
                case "Edit":
                    String message = commandParts[1];
                    String editVersion = commandParts[2];
                    if (chatLogger.contains(message)) {
                        int index = chatLogger.indexOf(message);
                        chatLogger.set(index, editVersion);
                    }
                    break;
                case "Pin":
                    String messagePin = commandParts[1];
                    if (chatLogger.contains(messagePin)) {
                        chatLogger.remove(messagePin);
                        chatLogger.add(messagePin);
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
