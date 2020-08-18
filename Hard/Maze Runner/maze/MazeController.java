package maze;

import mazeGeneration.MazeGenerator;
import mazePath.MazeSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class MazeController {
    private boolean mazeIsGenerated;
    private int[][] maze;
    private final Scanner input;

    MazeController(Scanner input) {
        mazeIsGenerated = false;
        this.input = input;
    }

    public void generateMaze() {
        System.out.println("Enter the size of a new maze");
        int size = input.nextInt();

        MazeGenerator generator = new MazeGenerator(size, size);
        generator.generateMaze();

        maze = generator.getMaze();
        mazeIsGenerated = true;

        displayMaze();
    }

    public void loadMazeFromFile() {
        String fileName = input.next();

        try {
            if (!fileName.contains("txt")) {
                System.out.println("Cannot load the maze. It has an invalid format");
                return;
            }

            Scanner fileScanner = new Scanner(new File(fileName));
            String mazeText = readTextFromFile(fileScanner);
            generateMazeFromFile(mazeText);
            fileScanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.printf("The file %s does not exist\n", fileName);
        }

        mazeIsGenerated = true;
    }

    private String readTextFromFile(Scanner fileScanner) {
        String mazeText = "";
        while (fileScanner.hasNextLine()) {
            mazeText += fileScanner.nextLine() + "\n";
        }

        return mazeText;
    }

    private void generateMazeFromFile(String mazeText) {
        String[] mazeRows = mazeText.split("\n");
        maze = new int[mazeRows.length][mazeRows.length];

        for (int i = 0; i < mazeRows.length; i++) {
            char[] mazeRow = mazeRows[i].toCharArray();
            int mazeIndex = 0;
            for (int j = 0; j < mazeRows[i].length(); j += 2) {
                if (mazeRow[j] == '\u2588' && mazeRow[j + 1] == '\u2588') {
                    maze[i][mazeIndex++] = 1;
                } else {
                    maze[i][mazeIndex++] = 0;
                }
            }
        }
    }

    public void saveMazeToFile() {
        String fileName = input.next();

        if (mazeIsGenerated) {
            try {
                Formatter formatter = new Formatter(new File(fileName));
                writeMazeToFile(formatter);
                formatter.close();
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.printf("The file %s does not exist\n", fileName);
            }
        } else {
            System.out.println("Incorrect option. Please try again");
        }
    }

    private void writeMazeToFile(Formatter formatter) {
        for (int[] rows : maze) {
            for (int cell : rows) {
                if (cell == 1) {
                    formatter.format("\u2588\u2588");
                } else {
                    formatter.format("  ");
                }
            }
            formatter.format("%n");
        }
    }

    public void displayMaze() {
        if (mazeIsGenerated) {
            for (int[] rows : maze) {
                for (int cell : rows) {
                    displayCell(cell);
                }
                System.out.println();
            }
        } else {
            System.out.println("Incorrect option. Please try again");
        }
    }

    private void displayCell(int cell) {
        if (cell == 1) {
            displayWall();
        } else {
            displayOpenSpace();
        }
    }

    private void displayWall() {
        System.out.print("\u2588\u2588");
    }

    private void displayOpenSpace() {
        System.out.print("  ");
    }

    public void displayUserMenu() {
        System.out.print("=== Menu ===\n" +
                "1. Generate a new maze\n" +
                "2. Load a maze\n");

        if (mazeIsGenerated) {
            System.out.print("3. Save the maze\n" +
                    "4. Display the maze\n" +
                    "5. Find the escape\n");
        }

        System.out.print("0. Exit\n");
    }

    public void findEscapeRoute() {
        if (mazeIsGenerated) {
            MazeSolver solver = new MazeSolver(maze);
            solver.solveMaze();
            int[][] solvedMaze = solver.getSolvedMaze();
            setMaze(solvedMaze);
            displaySolvedMaze();
        } else {
            System.out.println("Incorrect option. Please try again");
        }
    }

    private void setMaze(int[][] newMaze) {
        maze = newMaze;
    }

    private void displaySolvedMaze() {
        if (mazeIsGenerated) {
            for (int[] rows : maze) {
                for (int cell : rows) {
                    displaySolvedCell(cell);
                }
                System.out.println();
            }
        } else {
            System.out.println("Incorrect option. Please try again");
        }
    }

    private void displaySolvedCell(int cell) {
        if (cell == 1) {
            displayWall();
        } else if (cell == 2) {
            displayPathToExit();
        } else {
            displayOpenSpace();
        }
    }

    private void displayPathToExit() {
        System.out.print("//");
    }
}
