package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MatrixProcessor processor = new MatrixProcessor();
        boolean exitIsNotSelected = true;

        while (exitIsNotSelected) {
            displayMenu();
            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1: {
                    processor.sumTwoMatrices();
                    break;
                }
                case 2: {
                    processor.multiplyMatrixByScalar();
                    break;
                }
                case 3: {
                    processor.multiplyMatrices();
                    break;
                }
                case 4: {
                    processor.transposeMatrix();
                    break;
                }
                case 5: {
                    processor.calculateDeterminant();
                    break;
                }
                case 6: {
                    processor.inverseMatrix();
                    break;
                }
                case 0: {
                    exitIsNotSelected = false;
                    break;
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.print("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n" +
                "Your choice: ");
    }
}
