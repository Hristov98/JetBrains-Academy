package mazePath;

import java.util.ArrayDeque;
import java.util.Deque;

public class MazeSolver {
    private final int[][] mazeToSolve;
    private final PathCoordinate entry;
    private final PathCoordinate exit;

    public MazeSolver(int[][] mazeToSolve) {
        this.mazeToSolve = mazeToSolve;

        entry = new PathCoordinate(0, 1);
        if (mazeToSolve[0].length % 2 == 0) {
            exit = new PathCoordinate(mazeToSolve.length - 1, mazeToSolve[0].length - 3);
        } else {
            exit = new PathCoordinate(mazeToSolve.length - 1, mazeToSolve[0].length - 2);
        }
    }

    public void solveMaze() {
        boolean[][] isTraversed = new boolean[mazeToSolve.length][mazeToSolve.length];
        Deque<PathCoordinate> coordinateQueue = new ArrayDeque<>();
        coordinateQueue.offerFirst(entry);

        while (!coordinateQueue.isEmpty()) {
            PathCoordinate currentCell = coordinateQueue.pollLast();
            if (!cellLocationIsValid(currentCell) || isTraversed[currentCell.getRow()][currentCell.getColumn()]) {
                continue;
            }

            if (cellIsAWall(currentCell)) {
                isTraversed[currentCell.getRow()][currentCell.getColumn()] = true;
                continue;
            }

            if (cellIsExit(currentCell)) {
                buildPathFromEntryToExit(currentCell);
            }

            addAdjacentCellsToQueue(coordinateQueue, currentCell);
            isTraversed[currentCell.getRow()][currentCell.getColumn()] = true;
        }
    }

    private boolean cellLocationIsValid(PathCoordinate coordinate) {
        return 0 <= coordinate.getRow() && coordinate.getRow() <= mazeToSolve.length - 1 &&
                0 < coordinate.getColumn() && coordinate.getColumn() < mazeToSolve[0].length - 1;
    }

    private boolean cellIsAWall(PathCoordinate coordinate) {
        return mazeToSolve[coordinate.getRow()][coordinate.getColumn()] == 1;
    }

    private boolean cellIsExit(PathCoordinate coordinate) {
        return (exit.getRow() == coordinate.getRow())
                && (exit.getColumn() == coordinate.getColumn());
    }

    private void buildPathFromEntryToExit(PathCoordinate currentCell) {
        PathCoordinate iterator = currentCell;

        while (iterator != null) {
            mazeToSolve[iterator.getRow()][iterator.getColumn()] = 2;
            iterator = iterator.getParent();
        }
    }

    private void addAdjacentCellsToQueue(Deque<PathCoordinate> coordinateQueue, PathCoordinate currentCell) {
        coordinateQueue.offerFirst(new PathCoordinate(currentCell.getRow() - 1, currentCell.getColumn(), currentCell));
        coordinateQueue.offerFirst(new PathCoordinate(currentCell.getRow() + 1, currentCell.getColumn(), currentCell));
        coordinateQueue.offerFirst(new PathCoordinate(currentCell.getRow(), currentCell.getColumn() - 1, currentCell));
        coordinateQueue.offerFirst(new PathCoordinate(currentCell.getRow(), currentCell.getColumn() + 1, currentCell));
    }

    public int[][] getSolvedMaze() {
        return mazeToSolve;
    }
}
