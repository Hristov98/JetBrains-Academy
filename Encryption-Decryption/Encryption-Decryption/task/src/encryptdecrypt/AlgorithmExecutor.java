package encryptdecrypt;

public class AlgorithmExecutor {
    private Algorithm algorithm;

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String executeAlgorithm(String command, String text, int key) {
        if ("enc".equals(command)) {
            return algorithm.encrypt(text, key);
        } else if ("dec".equals(command)) {
            return algorithm.decrypt(text, key);
        }

        return null;
    }
}
