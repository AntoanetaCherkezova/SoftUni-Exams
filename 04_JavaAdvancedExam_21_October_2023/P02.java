package Exam_21_October_2023;

import java.util.Scanner;

public class P02 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        int captainRow = -1;
        int captainCol = -1;

        for (int i = 0; i < size; i++) {
            char[] row = scanner.nextLine().toCharArray();
            matrix[i] = row;
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 'S') {
                    captainRow = i;
                    captainCol = j;
                }
            }
        }

        int fishCaught = 0;
        boolean outOfBounds = false;

        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    if (captainRow - 1 >= 0) {
                        captainRow--;
                    } else {
                        captainRow = size - 1;
                        outOfBounds = true;
                    }
                    break;
                case "down":
                    if (captainRow + 1 < size) {
                        captainRow++;
                    } else {
                        captainRow = 0;
                        outOfBounds = true;
                    }
                    break;
                case "left":
                    if (captainCol - 1 >= 0) {
                        captainCol--;
                    } else {
                        captainCol = size - 1;
                        outOfBounds = true;
                    }
                    break;
                case "right":
                    if (captainCol + 1 < size) {
                        captainCol++;
                    } else {
                        captainCol = 0;
                        outOfBounds = true;
                    }
                    break;
                case "collect the nets":
                    if (outOfBounds) {
                        int fishRequired = 20 - fishCaught;
                        System.out.printf("You didn't catch enough fish and didn't reach the quota! You need %d tons of fish more.%n", fishRequired);
                        System.out.println("Amount of fish caught: " + fishCaught + " tons.");
                        printMatrix(matrix);
                        return;
                    }
                    break;
            }

            char cell = matrix[captainRow][captainCol];
            if (cell == 'W') {
                System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]%n", captainRow, captainCol);
                return;
            } else if (Character.isDigit(cell)) {
                int fishInPassage = Character.getNumericValue(cell);
                fishCaught += fishInPassage;
                matrix[captainRow][captainCol] = '-';
            }

            if (outOfBounds) {
                outOfBounds = false;
            }
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(new String(row));
        }

    }
}
