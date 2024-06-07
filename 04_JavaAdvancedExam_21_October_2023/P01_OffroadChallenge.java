package Exam_21_October_2023;

import java.util.*;
import java.util.stream.Collectors;

public class P01_OffroadChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> initialFuelStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(initialFuelStack::push);

        Queue<Integer> fuelConsumptionQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        Queue<Integer> neededFuelQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));


        boolean enoughFuel = true;
        List<String> counterList = new ArrayList<>();
        StringBuilder counter = new StringBuilder();
        int countReached = 0;

        for (int i = 1; i <= 4; i++) {
            int firstFuel = initialFuelStack.peek();
            int firstConsumption = fuelConsumptionQueue.peek();
            int firstNeededFuel = neededFuelQueue.peek();

            int diff = firstFuel - firstConsumption;

            if (diff >= firstNeededFuel) {
                initialFuelStack.pop();
                fuelConsumptionQueue.poll();
                neededFuelQueue.poll();
                counterList.add("Altitude " + i);
                counter.append("Altitude " + i + ", ");
                countReached++;
                System.out.printf("John has reached: Altitude %d%n", i);
            } else {
                System.out.printf("John did not reach: Altitude %d%n", i);
                enoughFuel = false;
                break;
            }
        }

        if (enoughFuel) {
            System.out.println("John has reached all the altitudes and managed to reach the top!");
        } else {
            System.out.println("John failed to reach the top.");
            if (neededFuelQueue.size() == 4) {
                System.out.println("John didn't reach any altitude.");
            } else {
                System.out.print("Reached altitudes: ");
                System.out.println(counterList.toString().replaceAll("[\\[\\]]", ""));
              /*  System.out.println(counter.toString().replaceAll("[\\[\\]]", ""));*/
               /* for (int j = 1; j <= countReached; j++) {
                    System.out.printf("Altitude %d, ", j);
                }*/

            }
        }
    }
}
