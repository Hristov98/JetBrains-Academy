package encryptdecrypt;

public class UnicodeAlgorithm implements Algorithm {
    @Override
    public String encrypt(String text, int key) {
        char[] charArr = text.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = (char) (charArr[i] + key);
        }

        return new String(charArr);
    }

    @Override
    public String decrypt(String text, int key) {
        char[] charArr = text.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = (char) (charArr[i] - key);
        }

        return new String(charArr);
    }
}
