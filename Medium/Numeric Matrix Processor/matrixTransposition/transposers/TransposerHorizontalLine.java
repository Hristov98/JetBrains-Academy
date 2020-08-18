package matrixTransposition.transposers;

public class TransposerHorizontalLine implements TransposerMethod {
    @Override
    public double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transposedMatrix[i][j] = matrix[matrix.length - 1 - i][j];
            }
        }

        return transposedMatrix;
    }
}
