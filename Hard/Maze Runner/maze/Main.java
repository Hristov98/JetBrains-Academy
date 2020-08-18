package maze;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MazeController controller = new MazeController(input);
        boolean exitIsNotSelected = true;

        while (exitIsNotSelected) {
            controller.displayUserMenu();

            int userChoice = input.nextInt();
            switch (userChoice) {
                case 1: {
                    controller.generateMaze();
                    break;
                }
                case 2: {
                    controller.loadMazeFromFile();
                    break;
                }
                case 3: {
                    controller.saveMazeToFile();
                    break;
                }
                case 4: {
                    controller.displayMaze();
                    break;
                }
                case 5: {
                    controller.findEscapeRoute();
                    break;
                }
                case 0: {
                    System.out.println("Bye!");
                    exitIsNotSelected = false;
                    break;
                }
                default: {
                    System.out.println("Incorrect option. Please try again");
                }
            }
            System.out.println();
        }
    }
}
