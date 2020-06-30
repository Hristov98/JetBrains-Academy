package encryptdecrypt;

public class ShiftingAlgorithm implements Algorithm {
    @Override
    public String encrypt(String text, int key) {
        char[] charArr = text.toLowerCase().toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (isLetter(charArr[i])) {
                if (characterOverflows(charArr[i], key)) {
                    charArr[i] = (char) ('a' + (charArr[i] + key) % 'z' - 1);
                } else {
                    charArr[i] = (char) (charArr[i] + key);
                }
            }
        }

        return new String(charArr);
    }

    private static boolean isLetter(char c) {
        return c != ' ' && c != '!' && c != '.';
    }

    private static boolean characterOverflows(char c, int key) {
        return c + key > 'z';
    }

    @Override
    public String decrypt(String text, int key) {
        char[] charArr = text.toLowerCase().toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (isLetter(charArr[i])) {
                if (characterUnderflows(charArr[i], key)) {
                    charArr[i] = (char) ('z' - ('a' - (charArr[i] - key) - 1));
                } else {
                    charArr[i] = (char) (charArr[i] - key);
                }
            }
        }

        return new String(charArr);
    }

    private static boolean characterUnderflows(char c, int key) {
        return c - key < 'a';
    }
}
