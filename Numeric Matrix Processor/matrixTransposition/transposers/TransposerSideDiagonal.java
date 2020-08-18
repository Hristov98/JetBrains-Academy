package matrixTransposition.transposers;

public class TransposerSideDiagonal implements TransposerMethod {
    @Override
    public double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < transposedMatrix.length; i++) {
            for (int j = 0; j < transposedMatrix[i].length; j++) {
                transposedMatrix[i][j] = matrix[matrix.length - 1 - j][matrix[i].length - 1 - i];
            }
        }

        return transposedMatrix;
    }
}