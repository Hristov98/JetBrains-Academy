package algorithms;

public class RabinKarpAlgorithm implements Algorithm {
    private final int A = 53;
    private final int MODULUS = 1_000_000_009;

    @Override
    public boolean search(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return false;
        }

        long patternHash = 0;
        long substringHash = 0;
        long coefficient = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += charToLong(pattern.charAt(i)) * coefficient;
            patternHash %= MODULUS;

            substringHash += charToLong(text.charAt(text.length() - pattern.length() + i))
                    * coefficient;
            substringHash %= MODULUS;

            if (i != pattern.length() - 1) {
                coefficient = coefficient * A % MODULUS;
            }
        }

        for (int i = text.length(); i >= pattern.length(); i--) {
            System.out.println(substringHash + " vs "  +patternHash);
            if (substringHash == patternHash) {
                boolean patternFound = true;

                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
                        patternFound = false;
                        break;
                    }
                }

                if (patternFound) {
                    System.out.println("Returning true!");
                    return true;
                }
            }

            if (i > pattern.length()) {
                substringHash = (substringHash - charToLong(text.charAt(i - 1)) *
                        coefficient % MODULUS + MODULUS) * A % MODULUS;
                substringHash = (substringHash + charToLong(text.charAt(i - pattern.length() - 1)))
                        % MODULUS;
            }
        }

        System.out.println("Returning false!");
        return false;
    }

    private long charToLong(char c) {
        return c - ' ' + 1;
    }
}
