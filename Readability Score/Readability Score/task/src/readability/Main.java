package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            Scanner input = new Scanner(file);
            String text = getTextFromInput(input);

            int wordCount = getWordCount(text);
            int sentenceCount = getSentenceCount(text);
            int characterCount = getCharacterCount(text);
            int syllableCount = getSyllableCount(text);
            int polysyllableCount = getPolysyllableCount(text);

            System.out.printf("Words: %d\n", wordCount);
            System.out.printf("Sentences: %d\n", sentenceCount);
            System.out.printf("Characters: %d\n", characterCount);
            System.out.printf("Syllables: %d\n", syllableCount);
            System.out.printf("Polysyllables: %d\n", polysyllableCount);

            ScoreCalculator calculator = new ScoreCalculator(
                    wordCount, sentenceCount, characterCount, syllableCount, polysyllableCount);

            String scoreMethod = getScoreMethodFromInput();
            switch (scoreMethod) {
                case "ARI": {
                    calculator.calculateScoreByARI();
                    break;
                }
                case "FK": {
                    calculator.calculateScoreByFK();
                    break;
                }
                case "SMOG": {
                    calculator.calculateScoreBySMOG();
                    break;
                }
                case "CL": {
                    calculator.calculateScoreByCL();
                    break;
                }
                case "all": {
                    calculator.calculateScoreByAllMethods();
                    break;
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromInput(Scanner file) {
        String text = "";
        while (file.hasNext()) {
            text += file.nextLine();
        }

        return text;
    }

    private static int getWordCount(String text) {
        String[] words = text.split(" ");

        return words.length;
    }

    private static int getSentenceCount(String text) {
        String[] sentences = text.split("[?.!]");

        return sentences.length;
    }

    private static int getCharacterCount(String text) {
        int characterCount = 0;
        for (char c : text.toCharArray()) {
            if (c != ' ' && c != '\n' && c != '\t') {
                characterCount++;
            }
        }

        return characterCount;
    }

    private static int getSyllableCount(String text) {
        String[] words = text.split(" ");

        int syllables = 0;
        for (String word : words) {
            int vowelCount = 0;
            for (int i = 0; i < word.length() - 1; i++) {
                if (isVowel(word.charAt(i))) {
                    if (isVowel(word.charAt(i + 1))) {
                        i++;
                    }
                    vowelCount++;
                }
            }

            if (vowelCount == 0) {
                syllables++;
            } else {
                syllables += vowelCount;
            }
        }

        return syllables;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    private static int getPolysyllableCount(String text) {
        String[] words = text.split(" ");

        int polysyllables = 0;
        for (String word : words) {
            int vowelCount = 0;
            for (int i = 0; i < word.length() - 1; i++) {
                if (isVowel(word.charAt(i))) {
                    if (isVowel(word.charAt(i + 1))) {
                        i++;
                    }
                    vowelCount++;
                }
            }

            if (vowelCount > 2) {
                polysyllables++;
            }
        }

        return polysyllables;
    }

    private static String getScoreMethodFromInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        return input.nextLine();
    }
}
