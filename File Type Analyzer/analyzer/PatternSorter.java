package analyzer;

import java.util.Comparator;

public class PatternSorter {
    private static final Comparator<Pattern> comparator = Comparator.comparingInt(Pattern::getPriority);

    public static void sort(Pattern[] patterns, int left, int right) {
        if (left + 1 < right) {
            int middle = left + (right - left) / 2;

            sort(patterns, left, middle);
            sort(patterns, middle, right);

            Pattern[] temporary = new Pattern[right - left];
            int i = left;
            int j = middle;
            int k = 0;

            while (i < middle && j < right) {
                if (comparator.compare(patterns[i], patterns[j]) > 0) {
                    temporary[k++] = patterns[i++];
                } else {
                    temporary[k++] = patterns[j++];
                }
            }

            while (i < middle) {
                temporary[k++] = patterns[i++];
            }

            while (j < right) {
                temporary[k++] = patterns[j++];
            }

            System.arraycopy(temporary, 0, patterns, left, temporary.length);
        }
    }
}
