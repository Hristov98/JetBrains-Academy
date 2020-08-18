package search;

public class Main {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.processCommandLineArguments(args);

        boolean exitNotSelected = true;
        while (exitNotSelected) {
            searchEngine.printMenu();

            int userChoice = searchEngine.getUserChoice();

            switch (userChoice) {
                case 1: {
                    searchEngine.search();
                    break;
                }
                case 2: {
                    searchEngine.printAll();
                    break;
                }
                case 0: {
                    System.out.println("Bye!");
                    exitNotSelected = false;
                    break;
                }
                default: {
                    System.out.println("Incorrect option! Try again.");
                }
            }
        }
    }
}
