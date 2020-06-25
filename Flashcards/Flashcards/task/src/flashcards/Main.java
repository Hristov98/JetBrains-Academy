package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlashCardGenerator flashCardGenerator = new FlashCardGenerator();

        for (int i = 0; i < args.length; i++) {
            if ("-import".equals(args[i])) {
                flashCardGenerator.importCards(args[i + 1]);
            }
        }

        Scanner scan = new Scanner(System.in);
        boolean isActive = true;
        String instruction = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";

        while (isActive) {
            System.out.println(instruction);
            flashCardGenerator.addLineToLog(instruction);

            String action = scan.nextLine();
            flashCardGenerator.addLineToLog(action);

            switch (action) {
                case "add": {
                    flashCardGenerator.addCard();
                    break;
                }
                case "remove": {
                    flashCardGenerator.removeCard();
                    break;
                }
                case "import": {
                    flashCardGenerator.importCards();
                    break;
                }
                case "export": {
                    flashCardGenerator.exportCards();
                    break;
                }
                case "ask": {
                    flashCardGenerator.askForRandomCardDefinitions();
                    break;
                }
                case "log": {
                    flashCardGenerator.logText();
                    break;
                }
                case "hardest card": {
                    flashCardGenerator.printHardestCard();
                    break;
                }
                case "reset stats": {
                    flashCardGenerator.resetMistakes();
                    break;
                }
                case "exit": {
                    isActive = false;
                    System.out.println("Bye bye!");
                    for (int i = 0; i < args.length; i++) {
                        if ("-export".equals(args[i])) {
                            flashCardGenerator.exportCards(args[i + 1]);
                        }
                    }
                }
            }
        }
    }
}

