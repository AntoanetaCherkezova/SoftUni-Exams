package FinalExam_30_07_2023;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Map<String, Integer> usernameMap = new LinkedHashMap<>();
        while (!command.equals("Log out")) {
            String username = command.split(": ")[1];
            if(command.contains("New follower")) {
                if (!usernameMap.containsKey(username)) {
                    usernameMap.put(username, 0);
                }

            } else if (command.contains("Like")) {
                int countLike = Integer.parseInt(command.split(": ")[2]);

                if (!usernameMap.containsKey(username)) {
                    usernameMap.put(username, countLike);

                } else {
                    int currentLikeAndComment = usernameMap.get(username);
                    usernameMap.put(username, currentLikeAndComment + countLike);
                }

            } else if (command.contains("Comment")) {

                if (!usernameMap.containsKey(username)) {
                    usernameMap.put(username, 1);
                } else {
                    int currentLikeAndComment = usernameMap.get(username);
                    usernameMap.put(username, currentLikeAndComment + 1);
                }

            } else if (command.contains("Blocked")) {
                if (usernameMap.containsKey(username)) {
                    usernameMap.remove(username);
                } else {
                    System.out.printf("%s doesn't exist.%n", username);
                }

            }
            command = scanner.nextLine();
        }

        System.out.printf("%d followers%n", usernameMap.size());
        usernameMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
