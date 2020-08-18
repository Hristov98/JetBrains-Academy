package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    private static String folderName;
    private static Pattern[] patterns;
    private static Thread[] workers;

    public static void main(String[] args) {
        readCommandLineArguments(args);
        setWorkers();
        startWorkers();
        sleepUntilWorkersFinish();
    }

    private static void readCommandLineArguments(String[] args) {
        setTestFolder(args[0]);
        setPatterns(args[1]);
    }

    private static void setTestFolder(String arg) {
        folderName = arg;
    }

    private static void setPatterns(String arg) {
        try (InputStream inputStream = new FileInputStream(arg);) {
            long fileSize = new File(arg).length();
            byte[] allBytes = new byte[(int) fileSize];
            inputStream.read(allBytes);

            String fileContent = new String(allBytes);
            String[] patternsFromFile = fileContent.split("\n");
            patterns = new Pattern[patternsFromFile.length];
            for (int i = 0; i < patternsFromFile.length; i++) {
                String[] patternInformation = patternsFromFile[i].split(";");

                patterns[i] = new Pattern(Integer.parseInt(patternInformation[0]),
                        patternInformation[1], patternInformation[2]);
            }

            sortPatternsByPriority();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void sortPatternsByPriority() {
        PatternSorter.sort(patterns, 0, patterns.length);
    }

    private static void setWorkers() {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        workers = new Thread[files.length];

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(new PatternMatcherRunnable(files[i].toString(), patterns));
        }
    }

    private static void startWorkers() {
        for (Thread worker : workers) {
            worker.start();
        }
    }

    private static void sleepUntilWorkersFinish() {
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
