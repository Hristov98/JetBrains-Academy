package matrixTransposition.transposers;

public class TransposerVerticalLine implements TransposerMethod {
    @Override
    public double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transposedMatrix[i][j] = matrix[i][matrix[i].length - 1 - j];
            }
        }

        return transposedMatrix;
    }
}
