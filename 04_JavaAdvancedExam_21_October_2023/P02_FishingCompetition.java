package Exam_21_October_2023;

import java.util.Scanner;

public class P02_FishingCompetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        int captainRow = -1;
        int captainCol = -1;


        for (int i = 0; i < size; i++) {
            char[] row = scanner.nextLine().toCharArray();
            for (int j = 0; j < size; j++) {
                matrix[i] = row;
                if (matrix[i][j] == 'S') {
                    captainRow = i;
                    captainCol = j;
                }
            }
        }
        int sumTonsFish = 0;
        String command = scanner.nextLine();

        while (!command.equals("collect the nets")) {

            int currentRow = captainRow;
            int currentColumn = captainCol;
            if (matrix[captainRow][captainCol] == 'S') {
                matrix[captainRow][captainCol] = '-';
            }

            if (command.equals("right")) {
                captainCol++;
                if (isOutOfBounds(matrix,captainRow,captainCol)){
                    captainCol = 0;
                }
            } else if (command.equals("left")) {
                captainCol--;
                if (isOutOfBounds(matrix,captainRow,captainCol)){
                    captainCol = size - 1;
                }
            } else if (command.equals("up")) {
                captainRow--;
                if (isOutOfBounds(matrix,captainRow,captainCol)){
                    captainRow = size - 1;
                }
            } else if (command.equals("down")) {
                captainRow++;
                if (isOutOfBounds(matrix,captainRow,captainCol)){
                    captainRow = 0;
                }
            }
            if (matrix[captainRow][captainCol] == 'W') {
                sumTonsFish = 0;
                break;
            }
            if (matrix[captainRow][captainCol] != 'W' && matrix[captainRow][captainCol] != '-') {
                char fishPassage = matrix[captainRow][captainCol];
                int fishPassageInt = Integer.parseInt(String.valueOf(fishPassage));
                sumTonsFish += fishPassageInt;
                matrix[captainRow][captainCol] = '-';
            }

            command = scanner.nextLine();
        }
        if(command.equals("collect the nets")) {
            matrix[captainRow][captainCol] = 'S';

        }

        if (sumTonsFish == 0 && !command.equals("collect the nets")) {
            System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]%n", captainRow, captainCol);

        } else {
            if (sumTonsFish >= 20) {
                System.out.println("Success! You managed to reach the quota!");
            } else {
                System.out.printf("You didn't catch enough fish and didn't reach the quota! You need %d tons of fish more.\n", 20 - sumTonsFish);
            }
            if(sumTonsFish > 0) {
                System.out.printf("Amount of fish caught: %d tons.\n", sumTonsFish);
            }
            printMatrix(matrix);
        }

       /* 4
        ---S
        ----
        9-5-
        34--
        collect the nets*/


    }
    private static boolean isOutOfBounds(char[][] matrix, int rows, int column) {
        return rows >= matrix.length || rows < 0 || column >= matrix.length || column < 0;
    }
    private static void printMatrix (char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
