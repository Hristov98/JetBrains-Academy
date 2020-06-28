package readability;

public class ScoreCalculator {
    private int wordCount;
    private int sentenceCount;
    private int characterCount;
    private int syllableCount;
    private int polysyllableCount;

    ScoreCalculator(int words, int sentences, int characters, int syllables,
                    int polysyllables) {
        wordCount = words;
        sentenceCount = sentences;
        characterCount = characters;
        syllableCount = syllables;
        polysyllableCount = polysyllables;
    }

    public void calculateScoreByAllMethods() {
        calculateScoreByARI();
        calculateScoreByFK();
        calculateScoreBySMOG();
        calculateScoreByCL();
    }

    public void calculateScoreByARI() {
        double score = getScoreByARI();
        printScoreByMethod("Automated Readability Index", score);
    }

    private void printScoreByMethod(String method, double score) {
        String ageRange = getAgeRange(score);
        System.out.printf("%s: %f (about %s year olds).\n", method, score, ageRange);
    }

    private String getAgeRange(double score) {
        switch ((int) score + 1) {
            case 1: {
                return "6";
            }
            case 2: {
                return "7";
            }
            case 3: {
                return "9";
            }
            case 4: {
                return "10";
            }
            case 5: {
                return "11";
            }
            case 6: {
                return "12";
            }
            case 7: {
                return "13";
            }
            case 8: {
                return "14";
            }
            case 9: {
                return "15";
            }
            case 10: {
                return "16";
            }
            case 11: {
                return "17";
            }
            case 12: {
                return "18";
            }
            case 13: {
                return "24";
            }
            case 14: {
                return "24+";
            }
            default: {
                return "";
            }
        }
    }

    private double getScoreByARI() {
        return 4.71 * characterCount / wordCount + 0.5 * wordCount / sentenceCount - 21.43;
    }

    public void calculateScoreByFK() {
        double score = getScoreByFK();
        printScoreByMethod("Flesch–Kincaid readability tests", score);
    }

    private double getScoreByFK() {
        return 0.39 * wordCount / sentenceCount + 11.8 * syllableCount / wordCount - 15.59;
    }

    public void calculateScoreBySMOG() {
        double score = getScoreBySMOG();
        printScoreByMethod("Simple Measure of Gobbledygook", score);
    }

    private double getScoreBySMOG() {
        return 1.043 * Math.sqrt(polysyllableCount * 30f / sentenceCount) + 3.1291;
    }

    public void calculateScoreByCL() {
        double averageCharactersPer100Words = getAverageCharacters();
        double averageSentencesPer100Words = getAverageSentences();

        double score = getScoreByCL(averageCharactersPer100Words, averageSentencesPer100Words);
        printScoreByMethod("Coleman–Liau index", score);
    }

    private double getAverageCharacters() {
        return (double) characterCount / wordCount;
    }

    private double getAverageSentences() {
        return (double) sentenceCount / wordCount;
    }

    private double getScoreByCL(double averageCharacters, double averageSentences) {
        return 0.0588 * averageCharacters - 0.296 * averageSentences - 15.8;
    }
}
