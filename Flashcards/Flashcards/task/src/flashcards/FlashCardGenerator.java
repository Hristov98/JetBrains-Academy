package flashcards;

import java.io.*;
import java.util.*;

public class FlashCardGenerator {
    private LinkedHashMap<String, String> cardMap;
    private LinkedHashMap<String, Integer> mistakesPerCard;
    private ArrayList<String> textLog;
    private Scanner input;

    FlashCardGenerator() {
        cardMap = new LinkedHashMap<>();
        mistakesPerCard = new LinkedHashMap<>();
        textLog = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void addCard() {
        System.out.println("The card:");
        textLog.add("The card:\n");

        String cardTerm = input.nextLine();
        textLog.add(cardTerm + "\n");

        if (cardMap.containsKey(cardTerm)) {
            String outputTerm = String.format("The card \"%s\" already exists.\n\n", cardTerm);
            System.out.print(outputTerm);
            textLog.add(outputTerm);

        } else {
            System.out.println("The definition of the card:");
            textLog.add("The definition of the card:\n");

            String cardDefinition = input.nextLine();
            textLog.add(cardDefinition + "\n");

            if (cardMap.containsValue(cardDefinition)) {
                String outputDefinition = String.format("The definition \"%s\" already exists.\n\n", cardDefinition);
                System.out.print(outputDefinition);
                textLog.add(outputDefinition);
            } else {
                cardMap.put(cardTerm, cardDefinition);
                mistakesPerCard.put(cardTerm, 0);

                String outputDefinition = String.
                        format("The pair (\"%s\":\"%s\") has been added.\n\n", cardTerm, cardDefinition);
                System.out.print(outputDefinition);
                textLog.add(outputDefinition);
            }
        }
    }

    public void removeCard() {
        System.out.println("The card:");
        textLog.add("The card:");

        String cardTerm = input.nextLine();
        textLog.add(cardTerm + "\n");

        if (cardMap.containsKey(cardTerm)) {
            cardMap.remove(cardTerm);
            mistakesPerCard.remove(cardTerm);

            System.out.println("The card has been removed.\n");
            textLog.add("The card has been removed.\n\n");
        } else {
            String outputTerm = String.format("Can't remove \"%s\": there is no such card.\n\n", cardTerm);
            System.out.print(outputTerm);
            textLog.add(outputTerm);
        }
    }

    public void importCards() {
        System.out.println("File name:");
        textLog.add("File name:\n");

        String fileName = input.nextLine();
        textLog.add(fileName + "\n");

        loadCardsFromFilename(fileName);
    }

    public void importCards(String fileName) {
        loadCardsFromFilename(fileName);
    }

    private void loadCardsFromFilename(String fileName) {
        File importedFile = new File(fileName);

        int count = 0;
        try (Scanner fileScanner = new Scanner(importedFile)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] strArr = line.split(" ");

                if (strArr.length > 3) {
                    cardMap.put(strArr[0] + " " + strArr[1], strArr[2]);
                    mistakesPerCard.put(strArr[0] + " " + strArr[1], Integer.parseInt(strArr[3]));
                } else {
                    cardMap.put(strArr[0], strArr[1]);
                    mistakesPerCard.put(strArr[0], Integer.parseInt(strArr[2]));
                }

                count++;
            }

            String output = String.format("%d cards have been loaded.\n\n", count);
            System.out.print(output);
            textLog.add(output);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.\n");
            textLog.add("File not found.\n\n");
        }
    }

    public void exportCards() {
        System.out.println("File name:");
        textLog.add("File name:\n");

        String fileName = input.nextLine();
        textLog.add(fileName + "\n");

        exportCardsToFileName(fileName);
    }

    public void exportCards(String fileName) {
        exportCardsToFileName(fileName);
    }

    private void exportCardsToFileName(String fileName) {
        File exportedFile = new File(fileName);

        try (PrintWriter writer = new PrintWriter(exportedFile)) {
            for (Map.Entry<String, String> card : cardMap.entrySet()) {
                writer.printf("%s %s %d\n", card.getKey(), card.getValue(),
                        mistakesPerCard.get(card.getKey()));
            }

            String output = String.format("%d cards have been saved.\n\n", cardMap.size());
            System.out.print(output);
            textLog.add(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askForRandomCardDefinitions() {
        System.out.println("How many times to ask?");
        textLog.add("How many times to ask?");

        int timesAsked = input.nextInt();
        input.nextLine();
        textLog.add(timesAsked + "\n");


        Random rand = new Random();
        for (int i = 0; i < timesAsked; i++) {
            int randomNumber = rand.nextInt(cardMap.size());

            int index = 0;
            for (Map.Entry<String, String> card : cardMap.entrySet()) {
                if (index == randomNumber) {
                    askForSpecificCard(card);
                    break;
                } else {
                    index++;
                }
            }
        }
    }

    private void askForSpecificCard(Map.Entry<String, String> card) {
        String output = String.format("Print the definition of \"%s\":\n", card.getKey());
        System.out.print(output);
        textLog.add(output);

        String answer = input.nextLine();
        textLog.add(answer + "\n");

        if (card.getValue().equals(answer)) {
            System.out.println("Correct answer.");
            textLog.add("Correct answer.");
        } else if (cardMap.containsValue(answer)) {
            String outputWrong = String.format("Wrong answer. The correct one is \"%s\", " +
                    "you've just written the definition of \"%s\"\n\n", card.getValue(), getKey(answer));
            System.out.print(outputWrong);
            textLog.add(outputWrong);

            mistakesPerCard.put(card.getKey(), mistakesPerCard.get(card.getKey()) + 1);
        } else {
            String outputWrong = String.format("Wrong answer. The correct one is \"%s\":\n\n", card.getValue());
            System.out.print(outputWrong);
            textLog.add(outputWrong);

            mistakesPerCard.put(card.getKey(), mistakesPerCard.get(card.getKey()) + 1);
        }

    }

    private String getKey(String value) {
        for (Map.Entry<String, String> card : cardMap.entrySet()) {
            if (card.getValue().equals(value)) {
                return card.getKey();
            }
        }

        return null;
    }

    public void logText() {
        System.out.println("File name:");
        textLog.add("File name:\n");

        String fileName = input.nextLine();
        textLog.add(fileName + "\n");

        File logFile = new File(fileName);

        try (PrintWriter writer = new PrintWriter(logFile)) {
            for (String line : textLog) {
                writer.write(line);
            }

            String output = "The log has been saved.\n\n";
            System.out.print(output);
            textLog.add(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLineToLog(String line) {
        textLog.add(line);
    }

    public void printHardestCard() {
        if (cardMap.size() == 0) {
            System.out.println("There are no cards with errors.\n");
        } else {
            int mostMistakes = 0;
            for (Map.Entry<String, Integer> card : mistakesPerCard.entrySet()) {
                if (card.getValue() > mostMistakes) {
                    mostMistakes = card.getValue();
                }
            }

            if (mostMistakes == 0) {
                System.out.println("There are no cards with errors.\n");
            } else {
                ArrayList<String> hardestCards = new ArrayList<>();
                for (Map.Entry<String, Integer> card : mistakesPerCard.entrySet()) {
                    if (mostMistakes == card.getValue()) {
                        hardestCards.add(card.getKey());
                    }
                }

                StringBuilder output;
                if (hardestCards.size() == 1) {
                    output = new StringBuilder("The hardest card is ");
                } else {
                    output = new StringBuilder("The hardest cards are ");
                }

                for (String card : hardestCards) {
                    if (hardestCards.indexOf(card) == hardestCards.size()) {
                        output.append(String.format("\"%s\". ", card));
                    } else {
                        output.append(String.format("\"%s\", ", card));
                    }
                }


                if (hardestCards.size() == 1) {
                    output.append(String.format("You have %d errors answering it. \n", mostMistakes));
                } else {
                    output.append(String.format("You have %d errors answering them. \n", mostMistakes));
                }

                System.out.println(output);
                textLog.add(output.toString());
            }
        }
    }

    public void resetMistakes() {
        for (Map.Entry<String, Integer> card : mistakesPerCard.entrySet()) {
            mistakesPerCard.put(card.getKey(), 0);
        }

        System.out.println("Card statistics has been reset.\n");
        textLog.add("Card statistics has been reset.\n\n");
    }
}
