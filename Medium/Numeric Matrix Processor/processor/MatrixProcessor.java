package processor;

import matrixTransposition.TransposerFactory;
import matrixTransposition.transposers.TransposerMethod;
import matrixTransposition.transposers.TransposerMainDiagonal;

import java.util.Scanner;

public class MatrixProcessor {
    private final Scanner input;

    MatrixProcessor() {
        input = new Scanner(System.in);
    }

    public void sumTwoMatrices() {
        double[][] firstMatrix = getMatrixFromInput();
        double[][] secondMatrix = getMatrixFromInput();

        System.out.println("The addition result is: ");
        if (dimensionsMatch(firstMatrix, secondMatrix)) {
            double[][] matrixSum = getSumOfMatrices(firstMatrix, secondMatrix);
            printMatrix(matrixSum);
        } else {
            System.out.println("ERROR");
        }
    }

    private double[][] getMatrixFromInput() {
        System.out.println("Enter size of matrix: ");
        int rows = input.nextInt();
        int columns = input.nextInt();
        double[][] matrix = new double[rows][columns];

        System.out.println("Enter matrix: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = input.nextFloat();
            }
        }

        return matrix;
    }

    private boolean dimensionsMatch(double[][] firstMatrix, double[][] secondMatrix) {
        return firstMatrix.length == secondMatrix.length
                && firstMatrix[0].length == secondMatrix[0].length;
    }

    private double[][] getSumOfMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] matrixSum = new double[firstMatrix.length][firstMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[i].length; j++) {
                matrixSum[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }

        return matrixSum;
    }

    private void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("%.2f ", element);
            }
            System.out.println();
        }
    }

    public void multiplyMatrixByScalar() {
        double[][] matrix = getMatrixFromInput();
        System.out.println("Enter constant: ");
        double scalar = input.nextDouble();

        System.out.println("The multiplication result is: ");
        double[][] product = getProductOfScalar(matrix, scalar);
        printMatrix(product);
    }

    private double[][] getProductOfScalar(double[][] matrix, double scalar) {
        double[][] product = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                product[i][j] = matrix[i][j] * scalar;
            }
        }

        return product;
    }

    public void multiplyMatrices() {
        double[][] firstMatrix = getMatrixFromInput();
        double[][] secondMatrix = getMatrixFromInput();

        System.out.println("The multiplication result is: ");
        if (multiplicationIsPossible(firstMatrix, secondMatrix)) {
            double[][] product = getProductOfMatrices(firstMatrix, secondMatrix);
            printMatrix(product);
        } else {
            System.out.println("ERROR");
        }
    }

    private boolean multiplicationIsPossible(double[][] firstMatrix, double[][] secondMatrix) {
        return firstMatrix[0].length == secondMatrix.length;
    }

    private double[][] getProductOfMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] product = new double[firstMatrix.length][secondMatrix[0].length];

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[i].length; j++) {
                double dotSum = 0;
                for (int k = 0; k < firstMatrix[i].length; k++) {
                    dotSum += firstMatrix[i][k] * secondMatrix[k][j];
                }

                product[i][j] = dotSum;
            }
        }

        return product;
    }

    public void transposeMatrix() {
        System.out.println("\n" +
                "1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n");
        System.out.println("Your choice: ");
        int userChoice = input.nextInt();
        double[][] matrix = getMatrixFromInput();

        System.out.println("The result is: ");
        TransposerFactory factory = new TransposerFactory();
        TransposerMethod transposer = factory.getTransposer(userChoice);

        matrix = transposer.transpose(matrix);
        printMatrix(matrix);
    }

    public void calculateDeterminant() {
        double[][] matrix = getMatrixFromInput();
        System.out.println("The result is:\n" + getDeterminantOfMatrix(matrix));
    }

    private double getDeterminantOfMatrix(double[][] matrix) {
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        if (matrix.length == 3) {
            return getDeterminantByTriangle(matrix);
        }

        double determinant = 0;
        int sign = 1;
        for (int i = 0; i < matrix.length; i++) {
            double[][] reducedMatrix = getReducedMatrix(matrix, 0, i);
            determinant += matrix[0][i] * sign * getDeterminantOfMatrix(reducedMatrix);
            sign *= -1;
        }

        return determinant;
    }

    private double[][] getReducedMatrix(double[][] matrix, int excludedRow, int excludedColumn) {
        double[][] reducedMatrix = new double[matrix.length - 1][matrix.length - 1];
        int rowIndex = 0;
        int columnIndex;
        for (int i = 0; i < matrix.length; i++) {
            columnIndex = 0;
            if (i == excludedRow) {
                continue;
            }

            for (int j = 0; j < matrix.length; j++) {
                if (j == excludedColumn) {
                    continue;
                }

                reducedMatrix[rowIndex][columnIndex++] = matrix[i][j];
            }

            rowIndex++;
        }

        return reducedMatrix;
    }

    private double getDeterminantByTriangle(double[][] matrix) {
        return matrix[0][0] * matrix[1][1] * matrix[2][2] +
                matrix[1][0] * matrix[2][1] * matrix[0][2] +
                matrix[0][1] * matrix[1][2] * matrix[2][0] -
                matrix[2][0] * matrix[1][1] * matrix[0][2] -
                matrix[2][1] * matrix[1][2] * matrix[0][0] -
                matrix[1][0] * matrix[0][1] * matrix[2][2];
    }

    public void inverseMatrix() {
        double[][] matrix = getMatrixFromInput();
        double scalar = 1 / getDeterminantOfMatrix(matrix);
        TransposerMethod transposer = new TransposerMainDiagonal();

        double[][] cofactorMatrix = getCofactorMatrix(matrix);
        cofactorMatrix = transposer.transpose(cofactorMatrix);
        cofactorMatrix = getProductOfScalar(cofactorMatrix, scalar);
        printMatrix(cofactorMatrix);
    }

    private double[][] getCofactorMatrix(double[][] matrix) {
        double[][] cofactorMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int sign = ((i + j + 2) % 2 == 0) ? 1 : -1;
                double[][] reducedMatrix = getReducedMatrix(matrix, i, j);
                cofactorMatrix[i][j] = sign * getDeterminantOfMatrix(reducedMatrix);
            }
        }

        return cofactorMatrix;
    }
}
