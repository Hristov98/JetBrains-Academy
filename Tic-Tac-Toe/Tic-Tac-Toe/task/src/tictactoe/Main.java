package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] symbolMatrix = new char[3][3];
        for (int i = 0; i < symbolMatrix.length; i++) {
            for (int j = 0; j < symbolMatrix.length; j++) {
                symbolMatrix[i][j] = ' ';
            }
        }

        boolean playerXTurn = true;
        while (true) {
            printMatrix(symbolMatrix);

            if (playerXTurn) {
                playerXTurn = false;
                enterCell(symbolMatrix, 'X');
                printMatrix(symbolMatrix);
                if (gameOverReached(symbolMatrix)) break;
            } else {
                playerXTurn = true;
                enterCell(symbolMatrix, 'O');
                printMatrix(symbolMatrix);
                if (gameOverReached(symbolMatrix)) break;
            }
        }
    }

    private static void printMatrix(char[][] symbolMatrix) {
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", symbolMatrix[0][0], symbolMatrix[0][1], symbolMatrix[0][2]);
        System.out.printf("| %s %s %s |\n", symbolMatrix[1][0], symbolMatrix[1][1], symbolMatrix[1][2]);
        System.out.printf("| %s %s %s |\n", symbolMatrix[2][0], symbolMatrix[2][1], symbolMatrix[2][2]);
        System.out.println("---------");
    }

    private static void enterCell(char[][] symbols, char symbol) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the coordinates: ");

            try {
                int first = input.nextInt();
                int second = input.nextInt();

                if (!areValidCoordinates(first, second)) {
                    System.out.println("You should enter numbers!");
                } else {
                    int[] cell = flipCell90Degrees(first, second);
                    if (symbols[cell[0]][cell[1]] != ' ' && symbols[cell[0]][cell[1]] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        symbols[cell[0]][cell[1]] = symbol;
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static boolean areValidCoordinates(int row, int column) {
        return 1 <= row && row <= 3 && 1 <= column && column <= 3;
    }

    private static int[] flipCell90Degrees(int row, int column) {
        int[] flippedCoordinates = new int[0];
        int realRow = row - 1;
        int realColumn = column - 1;

        if (realRow == 0 && realColumn == 0) {
            flippedCoordinates = new int[]{2, 0};
        }
        if (realRow == 0 && realColumn == 1) {
            flippedCoordinates = new int[]{1, 0};
        }
        if (realRow == 0 && realColumn == 2) {
            flippedCoordinates = new int[]{0, 0};
        }
        if (realRow == 1 && realColumn == 0) {
            flippedCoordinates = new int[]{2, 1};
        }
        if (realRow == 1 && realColumn == 1) {
            flippedCoordinates = new int[]{1, 1};
        }
        if (realRow == 1 && realColumn == 2) {
            flippedCoordinates = new int[]{0, 1};
        }
        if (realRow == 2 && realColumn == 0) {
            flippedCoordinates = new int[]{2, 2};
        }
        if (realRow == 2 && realColumn == 1) {
            flippedCoordinates = new int[]{1, 2};
        }
        if (realRow == 2 && realColumn == 2) {
            flippedCoordinates = new int[]{0, 2};
        }

        return flippedCoordinates;
    }

    private static boolean gameOverReached(char[][] symbolMatrix) {
        if (gameIsADraw(symbolMatrix)) {
            System.out.println("Draw");
            return true;
        } else if (gameWinnerIsX(symbolMatrix)) {
            System.out.println("X wins");
            return true;
        } else if (gameWinnerIsO(symbolMatrix)) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

    private static boolean gameIsADraw(char[][] symbols) {
        return !threeInARowX(symbols) && !threeInARowO(symbols) && allCellsAreFilled(symbols);
    }

    private static boolean gameWinnerIsX(char[][] symbols) {
        return threeInARowX(symbols);
    }

    private static boolean gameWinnerIsO(char[][] symbols) {
        return threeInARowO(symbols);
    }

    private static boolean threeInARowX(char[][] symbols) {
        if (symbols[0][0] == 'X' && symbols[0][1] == 'X' && symbols[0][2] == 'X') {
            return true;
        }
        if (symbols[1][0] == 'X' && symbols[1][1] == 'X' && symbols[1][2] == 'X') {
            return true;
        }
        if (symbols[2][0] == 'X' && symbols[2][1] == 'X' && symbols[2][2] == 'X') {
            return true;
        }
        if (symbols[0][0] == 'X' && symbols[1][0] == 'X' && symbols[2][0] == 'X') {
            return true;
        }
        if (symbols[0][1] == 'X' && symbols[1][1] == 'X' && symbols[2][1] == 'X') {
            return true;
        }
        if (symbols[0][2] == 'X' && symbols[1][2] == 'X' && symbols[2][2] == 'X') {
            return true;
        }
        if (symbols[0][0] == 'X' && symbols[1][1] == 'X' && symbols[2][2] == 'X') {
            return true;
        }
        return symbols[0][2] == 'X' && symbols[1][1] == 'X' && symbols[2][0] == 'X';
    }

    private static boolean threeInARowO(char[][] symbols) {
        if (symbols[0][0] == 'O' && symbols[0][1] == 'O' && symbols[0][2] == 'O') {
            return true;
        }
        if (symbols[1][0] == 'O' && symbols[1][1] == 'O' && symbols[1][2] == 'O') {
            return true;
        }
        if (symbols[2][0] == 'O' && symbols[2][1] == 'O' && symbols[2][2] == 'O') {
            return true;
        }
        if (symbols[0][0] == 'O' && symbols[1][0] == 'O' && symbols[2][0] == 'O') {
            return true;
        }
        if (symbols[0][1] == 'O' && symbols[1][1] == 'O' && symbols[2][1] == 'O') {
            return true;
        }
        if (symbols[0][2] == 'O' && symbols[1][2] == 'O' && symbols[2][2] == 'O') {
            return true;
        }
        if (symbols[0][0] == 'O' && symbols[1][1] == 'O' && symbols[2][2] == 'O') {
            return true;
        }
        return symbols[0][2] == 'O' && symbols[1][1] == 'O' && symbols[2][0] == 'O';
    }

    private static boolean allCellsAreFilled(char[][] symbols) {
        for (char[] symbol : symbols) {
            for (int j = 0; j < symbols.length; j++) {
                if (symbol[j] == ' ' || symbol[j] == '_') {
                    return false;
                }
            }
        }

        return true;
    }
}
