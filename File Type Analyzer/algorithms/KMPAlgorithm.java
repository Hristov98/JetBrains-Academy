package algorithms;

public class KMPAlgorithm implements Algorithm {
    @Override
    public boolean search(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return false;
        }

        int[] largestBorder = getLargestBorder(pattern);
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = largestBorder[j - 1];
            }

            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == pattern.length()) {
                return true;
            }
        }

        return false;
    }

    private int[] getLargestBorder(String pattern) {
        int[] largestBorder = new int[pattern.length()];

        for (int i = 1; i < largestBorder.length; i++) {
            int j = largestBorder[i - 1];

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = largestBorder[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            largestBorder[i] = j;
        }

        return largestBorder;
    }
}
