package analyzer;

import algorithms.Algorithm;
import algorithms.RabinKarpAlgorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PatternMatcherRunnable implements Runnable {
    private final String fileName;
    private final Pattern[] patterns;
    private final Algorithm patternSearcher;

    public PatternMatcherRunnable(String fileName, Pattern[] patterns) {
        this.fileName = fileName;
        this.patterns = patterns;
        patternSearcher = new RabinKarpAlgorithm();
    }

    @Override
    public void run() {
        try (InputStream inputStream = new FileInputStream(fileName);) {

            long fileSize = new File(fileName).length();
            byte[] allBytes = new byte[(int) fileSize];
            inputStream.read(allBytes);

            String fileContent = new String(allBytes);
            searchForHighestPriorityPattern(fileContent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void searchForHighestPriorityPattern(String fileContent) {
        boolean patternNotFound = true;

        for (Pattern pattern : patterns) {
            System.out.println(fileContent + " vs " + pattern.getPattern());
            if (patternSearcher.search(fileContent, pattern.getPattern())) {
                patternNotFound = false;
                System.out.println(fileName + ": " + pattern.getFileType());
                break;
            }
        }

        if (patternNotFound) {
            System.out.println(fileName + ": Unknown file type.");
        }
    }
}
