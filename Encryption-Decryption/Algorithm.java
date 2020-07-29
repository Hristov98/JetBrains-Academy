package encryptdecrypt;

public interface Algorithm {
    String encrypt(String text, int key);

    String decrypt(String text, int key);
}
