package life.evolutionAlgorithm;

public class ConcreteGenerationAlgorithm implements GenerationAlgorithm {
    @Override
    public boolean[][] calculateNextGeneration(boolean[][] gameWorld) {
        boolean[][] nextGeneration = new boolean[gameWorld.length][gameWorld.length];

        for (int i = 0; i < nextGeneration.length; i++) {
            for (int j = 0; j < nextGeneration.length; j++) {
                if (cellIsAlive(gameWorld, i, j) && livingCellHasEnoughNeighbours(gameWorld, i, j)) {
                    nextGeneration[i][j] = true;
                } else {
                    nextGeneration[i][j] = cellIsDead(gameWorld, i, j) && deadCellHasEnoughNeighbours(gameWorld, i, j);
                }
            }
        }

        return nextGeneration;
    }

    private boolean cellIsAlive(boolean[][] gameWorld, int i, int j) {
        return gameWorld[i][j];
    }

    private boolean cellIsDead(boolean[][] gameWorld, int i, int j) {
        return !gameWorld[i][j];
    }

    private boolean livingCellHasEnoughNeighbours(boolean[][] gameWorld, int i, int j) {
        return calculateLivingNeighbours(gameWorld, i, j) == 2 ||
                calculateLivingNeighbours(gameWorld, i, j) == 3;
    }

    private boolean deadCellHasEnoughNeighbours(boolean[][] gameWorld, int i, int j) {
        return calculateLivingNeighbours(gameWorld, i, j) == 3;
    }

    private int calculateLivingNeighbours(boolean[][] gameWorld, int i, int j) {
        int livingNeighbours = 0;
        int period = gameWorld.length;

        for (int row = i - 1; row <= i + 1; row++) {
            for (int column = j - 1; column <= j + 1; column++) {
                if (row == i && column == j) {
                    continue;
                }

                if (cellIsAlive(gameWorld, (row + period) % period, (column + period) % period)) {
                    livingNeighbours++;
                }
            }
        }

        return livingNeighbours;
    }
}
