package mazeGeneration;

import java.util.TreeMap;

public class MazeGenerator {
    private final int ROWS;
    private final int COLUMNS;
    private final int innerRows;
    private final int innerColumns;
    private final Graph graph;
    private final int[][] maze;

    public MazeGenerator(int rows, int columns) {
        ROWS = rows;
        COLUMNS = columns;

        innerRows = (ROWS % 2 == 1) ? ROWS / 2 : ROWS / 2 - 1;
        innerColumns = (COLUMNS % 2 == 1) ? COLUMNS / 2 : COLUMNS / 2 - 1;
        graph = new Graph(innerRows * innerColumns);

        maze = new int[rows][columns];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                maze[i][j] = 1;
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public void generateMaze() {
        int[][] vertexes = getVertexes();
        graph.addGraphEdges(vertexes);
        TreeMap<Integer, Integer> usedEdges = graph.getUsedEdgesByPrim();

        drawEntrancePath();
        drawExitPath();
        drawInnerArea(vertexes, usedEdges);
    }

    private int[][] getVertexes() {
        int[][] vertexes = new int[innerRows][innerColumns];
        int index = 0;
        for (int i = 0; i < innerRows; i++) {
            for (int j = 0; j < innerColumns; j++) {
                vertexes[i][j] = index++;
            }
        }

        return vertexes;
    }

    private void drawEntrancePath() {
        maze[0][1] = 0;
    }

    private void drawExitPath() {
        if (COLUMNS % 2 == 0) {
            if (ROWS % 2 == 0) {
                maze[ROWS - 2][COLUMNS - 3] = 0;
            }
            maze[ROWS - 1][COLUMNS - 3] = 0;
        } else {
            if (ROWS % 2 == 0) {
                maze[ROWS - 2][COLUMNS - 2] = 0;
            }
            maze[ROWS - 1][COLUMNS - 2] = 0;
        }
    }

    private void drawInnerArea(int[][] innerGraph, TreeMap<Integer, Integer> usedEdges) {
        for (int i = 0; i < innerRows; i++) {
            for (int j = 0; j < innerColumns; j++) {
                drawPathOnCurrentCell(i, j);

                if (innerGraph[i][j] == 0) {
                    continue;
                }

                int currentVertex = innerGraph[i][j];
                int destinationVertex = usedEdges.get(currentVertex);

                if (destinationVertex == currentVertex + innerColumns) {
                    maze[2 + i * 2][1 + j * 2] = 0;
                } else if (destinationVertex == currentVertex - innerColumns) {
                    maze[i * 2][1 + j * 2] = 0;
                } else if (destinationVertex == currentVertex + 1) {
                    maze[1 + i * 2][2 + j * 2] = 0;
                } else if (destinationVertex == currentVertex - 1) {
                    maze[1 + i * 2][j * 2] = 0;
                }
            }
        }
    }

    private void drawPathOnCurrentCell(int row, int column) {
        maze[1 + row * 2][1 + column * 2] = 0;
    }
}
